package io.github.itliwei.daojia.api.web.tenant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.tenant.entity.Tenant;
import io.github.itliwei.daojia.tenant.service.TenantService;
import io.github.itliwei.daojia.tenant.query.TenantQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.tenant.vo.TenantDTO;
import io.github.itliwei.daojia.tenant.vo.TenantVO;

@Slf4j
@RestController
@RequestMapping("/sys/tenant")
@Api(tags = "api文档",description = "api文档")
public class TenantController {

    @Autowired
    private TenantService tenantService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<TenantVO> getById(@PathVariable long id) {
        Tenant result = tenantService.findById(id);
        if (result != null){
            TenantVO tenantVo =  TenantVO.convert2TenantVO(result);
            return Resp.success(tenantVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<TenantVO>> pageQuery(@RequestBody TenantQueryModel queryModel) {
        Page<Tenant> result = tenantService.findPage(queryModel);
        Page<TenantVO> voPage = PageBuilder.copyAndConvert(result, TenantVO::convert2TenantVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<TenantVO> save(@Valid @RequestBody TenantDTO tenantDto) {
        Tenant entity = tenantDto.convert2Tenant();
        int result = tenantService.save(entity);
        if (result > 0){
            TenantVO tenantVo =  TenantVO.convert2TenantVO(entity);
            return Resp.success(tenantVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody TenantDTO tenantDto) {
        Tenant entity = tenantDto.convert2Tenant();
        int result = tenantService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = tenantService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}