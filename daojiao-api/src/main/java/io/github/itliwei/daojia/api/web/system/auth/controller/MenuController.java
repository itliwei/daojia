package io.github.itliwei.daojia.api.web.system.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.auth.entity.Menu;
import io.github.itliwei.daojia.system.auth.service.MenuService;
import io.github.itliwei.daojia.system.auth.query.MenuQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.auth.vo.MenuVO;
import io.github.itliwei.daojia.system.auth.vo.MenuDTO;

@Slf4j
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "api文档",description = "api文档")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<MenuVO> getById(@PathVariable long id) {
        Menu result = menuService.findById(id);
        if (result != null){
            MenuVO menuVo =  MenuVO.convert2MenuVO(result);
            return Resp.success(menuVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<MenuVO>> pageQuery(@RequestBody MenuQueryModel queryModel) {
        Page<Menu> result = menuService.findPage(queryModel);
        Page<MenuVO> voPage = PageBuilder.copyAndConvert(result, MenuVO::convert2MenuVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<MenuVO> save(@Valid @RequestBody MenuDTO menuDto) {
        Menu entity = menuDto.convert2Menu();
        int result = menuService.save(entity);
        if (result > 0){
            MenuVO menuVo =  MenuVO.convert2MenuVO(entity);
            return Resp.success(menuVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody MenuDTO menuDto) {
        Menu entity = menuDto.convert2Menu();
        int result = menuService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = menuService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}