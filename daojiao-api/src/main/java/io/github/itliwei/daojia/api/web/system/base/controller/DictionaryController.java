package io.github.itliwei.daojia.api.web.system.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.daojia.system.base.entity.Dictionary;
import io.github.itliwei.daojia.system.base.service.DictionaryService;
import io.github.itliwei.daojia.system.base.query.DictionaryQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.daojia.system.base.vo.DictionaryDTO;
import io.github.itliwei.daojia.system.base.vo.DictionaryVO;

@Slf4j
@RestController
@RequestMapping("/sys/dictionary")
@Api(tags = "基础数据",description = "api文档")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<DictionaryVO> getById(@PathVariable long id) {
        Dictionary result = dictionaryService.findById(id);
        if (result != null){
            DictionaryVO dictionaryVo =  DictionaryVO.convert2DictionaryVO(result);
            return Resp.success(dictionaryVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<DictionaryVO>> pageQuery(@RequestBody DictionaryQueryModel queryModel) {
        Page<Dictionary> result = dictionaryService.findPage(queryModel);
        Page<DictionaryVO> voPage = PageBuilder.copyAndConvert(result, DictionaryVO::convert2DictionaryVO);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<DictionaryVO> save(@Valid @RequestBody DictionaryDTO dictionaryDto) {
        Dictionary entity = dictionaryDto.convert2Dictionary();
        int result = dictionaryService.save(entity);
        if (result > 0){
            DictionaryVO dictionaryVo =  DictionaryVO.convert2DictionaryVO(entity);
            return Resp.success(dictionaryVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody DictionaryDTO dictionaryDto) {
        Dictionary entity = dictionaryDto.convert2Dictionary();
        int result = dictionaryService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = dictionaryService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}