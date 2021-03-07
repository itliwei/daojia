package io.github.itliwei.daojia.api.web.system.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.auth.entity.RoleAuthority;
import io.github.itliwei.daojia.system.auth.service.RoleAuthorityService;
import io.github.itliwei.daojia.system.auth.query.RoleAuthorityQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.auth.vo.RoleAuthorityDTO;
import io.github.itliwei.daojia.system.auth.vo.RoleAuthorityVO;

@Slf4j
@RestController
@RequestMapping("/sys/role/authority")
@Api(tags = "api文档",description = "api文档")
public class RoleAuthorityController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<RoleAuthorityVO> getById(@PathVariable long id) {
        RoleAuthority result = roleAuthorityService.findById(id);
        if (result != null){
            RoleAuthorityVO roleAuthorityVo =  RoleAuthorityVO.convert2RoleAuthorityVO(result);
            return Resp.success(roleAuthorityVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<RoleAuthorityVO>> pageQuery(@RequestBody RoleAuthorityQueryModel queryModel) {
        Page<RoleAuthority> result = roleAuthorityService.findPage(queryModel);
        Page<RoleAuthorityVO> voPage = PageBuilder.copyAndConvert(result, RoleAuthorityVO::convert2RoleAuthorityVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<RoleAuthorityVO> save(@Valid @RequestBody RoleAuthorityDTO roleAuthorityDto) {
        RoleAuthority entity = roleAuthorityDto.convert2RoleAuthority();
        int result = roleAuthorityService.save(entity);
        if (result > 0){
            RoleAuthorityVO roleAuthorityVo =  RoleAuthorityVO.convert2RoleAuthorityVO(entity);
            return Resp.success(roleAuthorityVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody RoleAuthorityDTO roleAuthorityDto) {
        RoleAuthority entity = roleAuthorityDto.convert2RoleAuthority();
        int result = roleAuthorityService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = roleAuthorityService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}