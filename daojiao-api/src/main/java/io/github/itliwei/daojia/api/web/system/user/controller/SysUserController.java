package io.github.itliwei.daojia.api.web.system.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.user.entity.SysUser;
import io.github.itliwei.daojia.system.user.service.SysUserService;
import io.github.itliwei.daojia.system.user.query.SysUserQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.user.vo.SysUserDTO;
import io.github.itliwei.daojia.system.user.vo.SysUserVO;

@Slf4j
@RestController
@RequestMapping("/sys/user")
@Api(tags = "api文档",description = "api文档")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<SysUserVO> getById(@PathVariable long id) {
        SysUser result = sysUserService.findById(id);
        if (result != null){
            SysUserVO sysUserVo =  SysUserVO.convert2SysUserVO(result);
            return Resp.success(sysUserVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<SysUserVO>> pageQuery(@RequestBody SysUserQueryModel queryModel) {
        Page<SysUser> result = sysUserService.findPage(queryModel);
        Page<SysUserVO> voPage = PageBuilder.copyAndConvert(result, SysUserVO::convert2SysUserVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<SysUserVO> save(@Valid @RequestBody SysUserDTO sysUserDto) {
        SysUser entity = sysUserDto.convert2SysUser();
        int result = sysUserService.save(entity);
        if (result > 0){
            SysUserVO sysUserVo =  SysUserVO.convert2SysUserVO(entity);
            return Resp.success(sysUserVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody SysUserDTO sysUserDto) {
        SysUser entity = sysUserDto.convert2SysUser();
        int result = sysUserService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = sysUserService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}