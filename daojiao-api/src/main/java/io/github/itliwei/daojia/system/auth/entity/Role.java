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
@Type(label = "角色")
@QueryModel
@Table(value = "t_sys_role")
@ServiceClass(name = "RoleService")
@ControllerClass(path = "/sys/role",name = "RoleController")
@ElementClass(path = "/sys/role",group="sys")
@ViewObject(groups = {Role.DTO,Role.VO})
public class Role extends IdEntity {
    protected static final String VO = "SysUserVO";
    protected static final String DTO = "SysUserDTO";

    @Field(label = "名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String name;

    @Field(label = "编码")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String code;

    @Field(label = "数据权限：ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String dataType;

    @Field(label = "是否有效")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer status;

    @Field(label = "是否删除")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer isDel;
}
