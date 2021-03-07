package io.github.itliwei.daojia.tenant.entity;

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
@Type(label = "租户")
@QueryModel
@Table(value = "t_sys_tenant")
@ServiceClass(name = "TenantService")
@ControllerClass(path = "/sys/tenant",name = "TenantController")
@ElementClass(path = "/sys/tenant",group="sys")
@ViewObject(groups = {Tenant.DTO, Tenant.VO})
public class Tenant extends IdEntity {
    protected static final String VO = "TenantVO";
    protected static final String DTO = "TenantDTO";

    @Field(label = "名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String name;

    @Field(label = "用户名称")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private String code;

    @Field(label = "描述")
    @View(groups = {DTO,VO})
    private String description;

    @Field(label = "用户名称")
    @View(groups = {DTO,VO})
    private String logo;

    @Field(label = "负责人姓名")
    @View(groups = {DTO,VO})
    private String manager;

    @Field(label = "负责人手机号")
    @View(groups = {DTO,VO})
    private String managerPhone;

    @Field(label = "过期时间")
    @Query({Condition.Operator.eq})
    @View(groups = {DTO,VO})
    private Date expirationTime;

    @Field(label = "状态：0：注册 1：试用 2：有效 3：过期")
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
