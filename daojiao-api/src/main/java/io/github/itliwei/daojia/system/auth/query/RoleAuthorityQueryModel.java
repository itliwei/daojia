package io.github.itliwei.daojia.system.auth.query;

import java.io.Serializable;

import io.github.itliwei.vboot.vorm.orm.opt.QueryModel;
import io.swagger.annotations.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RoleAuthorityQueryModel", description = "RoleAuthorityQueryModel")
public class RoleAuthorityQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="资源ID",example="0")
	private Long authorityIdEQ;
	@ApiModelProperty(value ="权限类型：MENU:菜单;RESOURCE:资源;")
	private String authorityTypeEQ;
	@ApiModelProperty(value ="角色ID",example="0")
	private Long roleIdEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}