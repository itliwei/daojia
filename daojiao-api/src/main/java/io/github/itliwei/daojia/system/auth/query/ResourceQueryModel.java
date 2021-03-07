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
@ApiModel(value = "ResourceQueryModel", description = "ResourceQueryModel")
public class ResourceQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="名称")
	private String nameEQ;
	@ApiModelProperty(value ="编码")
	private String codeEQ;
	@ApiModelProperty(value ="菜单ID",example="0")
	private Long menuIdEQ;
	@ApiModelProperty(value ="是否有效",example="0")
	private Integer statusEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}