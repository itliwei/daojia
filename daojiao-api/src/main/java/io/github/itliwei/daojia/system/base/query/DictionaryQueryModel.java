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
@ApiModel(value = "DictionaryQueryModel", description = "DictionaryQueryModel")
public class DictionaryQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="父ID",example="0")
	private Integer parentIdEQ;
	@ApiModelProperty(value ="编码")
	private String dicCodeEQ;
	@ApiModelProperty(value ="类型")
	private String dicTypeEQ;
	@ApiModelProperty(value ="排序",example="0")
	private Integer sortValueEQ;
	@ApiModelProperty(value ="是否有效",example="0")
	private Integer statusEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}