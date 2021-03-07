package io.github.itliwei.daojia.api.web.system.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.auth.entity.Role;
import io.github.itliwei.daojia.system.auth.service.RoleService;
import io.github.itliwei.daojia.system.auth.query.RoleQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.auth.vo.SysUserDTO;
import io.github.itliwei.daojia.system.auth.vo.SysUserVO;

@Slf4j
@RestController
@RequestMapping("/sys/role")
@Api(tags = "api文档",description = "api文档")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<SysUserVO> getById(@PathVariable long id) {
        Role result = roleService.findById(id);
        if (result != null){
            SysUserVO roleVo =  SysUserVO.convert2SysUserVO(result);
            return Resp.success(roleVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<SysUserVO>> pageQuery(@RequestBody RoleQueryModel queryModel) {
        Page<Role> result = roleService.findPage(queryModel);
        Page<SysUserVO> voPage = PageBuilder.copyAndConvert(result, SysUserVO::convert2SysUserVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<SysUserVO> save(@Valid @RequestBody SysUserDTO roleDto) {
        Role entity = roleDto.convert2Role();
        int result = roleService.save(entity);
        if (result > 0){
            SysUserVO roleVo =  SysUserVO.convert2SysUserVO(entity);
            return Resp.success(roleVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody SysUserDTO roleDto) {
        Role entity = roleDto.convert2Role();
        int result = roleService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = roleService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}