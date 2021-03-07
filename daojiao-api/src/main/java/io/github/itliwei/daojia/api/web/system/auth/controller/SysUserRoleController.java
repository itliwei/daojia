package io.github.itliwei.daojia.api.web.system.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.auth.entity.SysUserRole;
import io.github.itliwei.daojia.system.auth.service.SysUserRoleService;
import io.github.itliwei.daojia.system.auth.query.SysUserRoleQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.auth.vo.SysUserRoleDTO;
import io.github.itliwei.daojia.system.auth.vo.SysUserRoleVO;

@Slf4j
@RestController
@RequestMapping("/sys/user/role")
@Api(tags = "api文档",description = "api文档")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<SysUserRoleVO> getById(@PathVariable long id) {
        SysUserRole result = sysUserRoleService.findById(id);
        if (result != null){
            SysUserRoleVO sysUserRoleVo =  SysUserRoleVO.convert2SysUserRoleVO(result);
            return Resp.success(sysUserRoleVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<SysUserRoleVO>> pageQuery(@RequestBody SysUserRoleQueryModel queryModel) {
        Page<SysUserRole> result = sysUserRoleService.findPage(queryModel);
        Page<SysUserRoleVO> voPage = PageBuilder.copyAndConvert(result, SysUserRoleVO::convert2SysUserRoleVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<SysUserRoleVO> save(@Valid @RequestBody SysUserRoleDTO sysUserRoleDto) {
        SysUserRole entity = sysUserRoleDto.convert2SysUserRole();
        int result = sysUserRoleService.save(entity);
        if (result > 0){
            SysUserRoleVO sysUserRoleVo =  SysUserRoleVO.convert2SysUserRoleVO(entity);
            return Resp.success(sysUserRoleVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody SysUserRoleDTO sysUserRoleDto) {
        SysUserRole entity = sysUserRoleDto.convert2SysUserRole();
        int result = sysUserRoleService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = sysUserRoleService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}