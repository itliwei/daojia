package io.github.itliwei.daojia.api.web.system.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.auth.entity.Resource;
import io.github.itliwei.daojia.system.auth.service.ResourceService;
import io.github.itliwei.daojia.system.auth.query.ResourceQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.auth.vo.ResourceDTO;
import io.github.itliwei.daojia.system.auth.vo.ResourceVO;

@Slf4j
@RestController
@RequestMapping("/sys/resource")
@Api(tags = "api文档",description = "api文档")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<ResourceVO> getById(@PathVariable long id) {
        Resource result = resourceService.findById(id);
        if (result != null){
            ResourceVO resourceVo =  ResourceVO.convert2ResourceVO(result);
            return Resp.success(resourceVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<ResourceVO>> pageQuery(@RequestBody ResourceQueryModel queryModel) {
        Page<Resource> result = resourceService.findPage(queryModel);
        Page<ResourceVO> voPage = PageBuilder.copyAndConvert(result, ResourceVO::convert2ResourceVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<ResourceVO> save(@Valid @RequestBody ResourceDTO resourceDto) {
        Resource entity = resourceDto.convert2Resource();
        int result = resourceService.save(entity);
        if (result > 0){
            ResourceVO resourceVo =  ResourceVO.convert2ResourceVO(entity);
            return Resp.success(resourceVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody ResourceDTO resourceDto) {
        Resource entity = resourceDto.convert2Resource();
        int result = resourceService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = resourceService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}