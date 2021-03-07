package io.github.itliwei.daojia.system.user.entity;

import io.github.itliwei.daojia.tenant.base.TenantBaseEntity;
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

import java.util.Date;


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
@Type(label = "系统用户")
@QueryModel
@Table(value = "t_sys_user")
@ServiceClass(name = "SysUserService")
@ControllerClass(path = "/sys/user",name = "SysUserController")
@ElementClass(path = "/sys/user",group="sys")
@ViewObject(groups = {SysUser.DTO,SysUser.VO})
public class SysUser extends TenantBaseEntity {
    protected static final String VO = "SysUserVO";
    protected static final String DTO = "SysUserDTO";

    @Field(label = "名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String name;

    @Field(label = "用户名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String username;

    @Field(label = "密码")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String password;

    @Field(label = "部门")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Long orgId;

    @Field(label = "岗位")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Long stationId;

    @Field(label = "手机号")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String phone;

    @Field(label = "邮箱")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String email;

    @Field(label = "性别")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String sex;

    @Field(label = "头像")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String avatar;

    @Field(label = "角色")
    @View(groups = {DTO,VO})
    private String roles;

    @Field(label = "是否有效")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer status;

    @Field(label = "是否删除")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Integer isDel;

    @Field(label = "创建时间")
    @View(groups = {DTO,VO})
    private Date createTime;
}
