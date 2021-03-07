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

import java.io.Serializable;


/**
 * @author : liwei
 * @date : 2019/08/03 21:38
 * @description :数据字典
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Type(label = "地区")
@QueryModel
@Table(value = "t_sys_area")
@ServiceClass
@ControllerClass(path = "/sys/area",desc = "sys")
@ElementClass(path = "/sys/area",group = "sys")
@ViewObject(groups = {Area.AREA_DTO, Area.AREA_VO})
public class Area extends IdEntity implements Serializable {
    protected static final String AREA_VO = "AreaVO";
    protected static final String AREA_DTO = "AreaDTO";


    @Field(label = "父ID")
    @Query({Condition.Operator.eq,Condition.Operator.ne})
    @View(groups = {AREA_DTO,AREA_VO})
    private Long pid;

    @Field(label = "简称")
    @View(groups = {AREA_DTO,AREA_VO})
    private String shortName;

    @Field(label = "名称")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private String name;

    @Field(label = "类型")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private String mergerName;

    @Field(label = "类型")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private String code;

    @Field(label = "邮政编码")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private String zipCode;

    @Field(label = "类型")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private String first;

    @Field(label = "类型")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private Double lng;

    @Field(label = "类型")
    @View(groups = {AREA_DTO,AREA_VO})
    @Query({Condition.Operator.eq})
    private Double lat;
}
