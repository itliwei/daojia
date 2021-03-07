package io.github.itliwei.daojia.system.base.query;

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
@ApiModel(value = "AreaQueryModel", description = "AreaQueryModel")
public class AreaQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="父ID",example="0")
	private Long pidEQ;
	@ApiModelProperty(value ="父ID",example="0")
	private Long pidNE;
	@ApiModelProperty(value ="名称")
	private String nameEQ;
	@ApiModelProperty(value ="类型")
	private String mergerNameEQ;
	@ApiModelProperty(value ="类型")
	private String codeEQ;
	@ApiModelProperty(value ="邮政编码")
	private String zipCodeEQ;
	@ApiModelProperty(value ="类型")
	private String firstEQ;
	@ApiModelProperty(value ="类型")
	private Double lngEQ;
	@ApiModelProperty(value ="类型")
	private Double latEQ;


}