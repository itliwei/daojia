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
@Type(label = "用户角色")
@QueryModel
@Table(value = "t_sys_role_authority")
@ServiceClass(name = "RoleAuthorityService")
@ControllerClass(path = "/sys/role/authority",name = "RoleAuthorityController")
@ElementClass(path = "/sys/role/authority",group="sys")
@ViewObject(groups = {RoleAuthority.DTO,RoleAuthority.VO})
public class RoleAuthority extends IdEntity {
    protected static final String VO = "RoleAuthorityVO";
    protected static final String DTO = "RoleAuthorityDTO";

    @Field(label = "资源ID")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Long authorityId;

    @Field(label = "权限类型：MENU:菜单;RESOURCE:资源;")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String authorityType;

    @Field(label = "角色ID")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Long roleId;

    @Field(label = "是否删除")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer isDel;
}
