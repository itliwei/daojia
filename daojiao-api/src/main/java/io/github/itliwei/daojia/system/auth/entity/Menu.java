package io.github.itliwei.daojia.system.auth.entity;

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
@Type(label = "资源")
@QueryModel
@Table(value = "t_sys_menu")
@ServiceClass(name = "MenuService")
@ControllerClass(path = "/sys/menu",name = "MenuController")
@ElementClass(path = "/sys/menu",group="sys")
@ViewObject(groups = {Menu.DTO,Menu.VO})
public class Menu extends IdEntity {
    protected static final String VO = "MenuVO";
    protected static final String DTO = "MenuDTO";

    @Field(label = "父ID")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Long parentId;

    @Field(label = "名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String name;

    @Field(label = "路径")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String path;

    @Field(label = "分组")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String groupName;

    @Field(label = "排序")
    @View(groups = {DTO,VO})
    @Query({Condition.Operator.eq})
    private Integer sortValue;

    @Field(label = "是否有效")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer status;

    @Field(label = "是否删除")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer isDel;
}
