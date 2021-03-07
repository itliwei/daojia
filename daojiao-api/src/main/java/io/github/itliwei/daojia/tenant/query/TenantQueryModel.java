package io.github.itliwei.daojia.tenant.query;

import java.io.Serializable;

import io.github.itliwei.vboot.vorm.orm.opt.QueryModel;
import io.swagger.annotations.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TenantQueryModel", description = "TenantQueryModel")
public class TenantQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="名称")
	private String nameEQ;
	@ApiModelProperty(value ="用户名称")
	private String codeEQ;
	@ApiModelProperty(value ="过期时间")
	private Date expirationTimeEQ;
	@ApiModelProperty(value ="状态：0：注册 1：试用 2：有效 3：过期",example="0")
	private Integer statusEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}