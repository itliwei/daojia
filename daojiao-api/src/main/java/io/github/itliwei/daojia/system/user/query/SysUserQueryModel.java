package io.github.itliwei.daojia.system.user.query;

import java.io.Serializable;

import io.github.itliwei.daojia.tenant.base.TenantBaseQueryModel;
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
@ApiModel(value = "SysUserQueryModel", description = "SysUserQueryModel")
public class SysUserQueryModel extends TenantBaseQueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="名称")
	private String nameEQ;
	@ApiModelProperty(value ="用户名称")
	private String usernameEQ;
	@ApiModelProperty(value ="密码")
	private String passwordEQ;
	@ApiModelProperty(value ="部门",example="0")
	private Long orgIdEQ;
	@ApiModelProperty(value ="岗位",example="0")
	private Long stationIdEQ;
	@ApiModelProperty(value ="手机号")
	private String phoneEQ;
	@ApiModelProperty(value ="邮箱")
	private String emailEQ;
	@ApiModelProperty(value ="性别")
	private String sexEQ;
	@ApiModelProperty(value ="头像")
	private String avatarEQ;
	@ApiModelProperty(value ="是否有效",example="0")
	private Integer statusEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}