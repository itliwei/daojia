package io.github.itliwei.daojia.system.base.entity;

import io.github.itliwei.vboot.vorm.annotation.Entity;
import io.github.itliwei.vboot.vorm.annotation.Field;
import io.github.itliwei.vboot.vorm.annotation.Type;
import io.github.itliwei.vboot.vorm.annotation.controller.ControllerClass;
import io.github.itliwei.vboot.vorm.annotation.elementui.ElementClass;
import io.github.itliwei.vboot.vorm.annotation.query.Query;
import io.github.itliwei.vboot.vorm.annotation.query.QueryModel;
import io.github.itliwei.vboot.vorm.annotation.service.ServiceClass;
import io.github.itliwei.vboot.vorm.annotation.view.View;
import io.github.itliwei.vboot.vorm.annotation.view.ViewObject;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.annotation.Table;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import lombok.*;



/**
 * @author : liwei
 * @date : 2019/08/03 21:38
 * @description :数据字典
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Type(label = "数据字典")
@QueryModel
@Table(value = "t_sys_dictionary")
@ServiceClass(name = "DictionaryService")
@ControllerClass(path = "/sys/dictionary",name = "DictionaryController")
@ElementClass(path = "/sys/dictionary",group="sys")
@ViewObject(groups = {Dictionary.DICTIONARY_DTO,Dictionary.DICTIONARY_VO})
public class Dictionary extends IdEntity {
    protected static final String DICTIONARY_VO = "DictionaryVO";
    protected static final String DICTIONARY_DTO = "DictionaryDTO";

    @Field(label = "父ID")
    @Query({Condition.Operator.eq})
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    private Integer parentId;

    @Field(label = "编码")
    @Query({Condition.Operator.eq})
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    private String dicCode;

    @Field(label = "数值")
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    private String dicValue;

    @Field(label = "类型")
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    @Query({Condition.Operator.eq})
    private String dicType;

    @Field(label = "排序")
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    @Query({Condition.Operator.eq})
    private Integer sortValue;

    @Field(label = "是否有效")
    @Query({Condition.Operator.eq})
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    private Integer status;

    @Field(label = "是否删除")
    @Query({Condition.Operator.eq})
    @View(groups = {DICTIONARY_DTO,DICTIONARY_VO})
    private Integer isDel;
}
