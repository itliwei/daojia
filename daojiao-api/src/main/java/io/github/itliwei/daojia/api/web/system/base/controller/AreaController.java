package io.github.itliwei.daojia.api.web.system.base.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.base.entity.Area;
import io.github.itliwei.daojia.system.base.service.AreaService;
import io.github.itliwei.daojia.system.base.query.AreaQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.base.vo.AreaVO;
import io.github.itliwei.daojia.system.base.vo.AreaDTO;

@Slf4j
@RestController
@RequestMapping("/sys/area")
@Api(tags = "基础数据",description = "sys")
public class AreaController {

    @Autowired
    private AreaService areaService;


    @GetMapping("/info/{id}")
    @RequiresRoles("user")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<AreaVO> getById(@PathVariable long id) {
        Area result = areaService.findById(id);
        if (result != null){
            AreaVO areaVo =  AreaVO.convert2AreaVO(result);
            return Resp.success(areaVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<AreaVO>> pageQuery(@RequestBody AreaQueryModel queryModel) {
        Page<Area> result = areaService.findPage(queryModel);
        Page<AreaVO> voPage = PageBuilder.copyAndConvert(result, AreaVO::convert2AreaVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<AreaVO> save(@Valid @RequestBody AreaDTO areaDto) {
        Area entity = areaDto.convert2Area();
        int result = areaService.save(entity);
        if (result > 0){
            AreaVO areaVo =  AreaVO.convert2AreaVO(entity);
            return Resp.success(areaVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody AreaDTO areaDto) {
        Area entity = areaDto.convert2Area();
        int result = areaService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = areaService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}