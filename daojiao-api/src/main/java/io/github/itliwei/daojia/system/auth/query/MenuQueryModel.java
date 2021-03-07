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
@ApiModel(value = "MenuQueryModel", description = "MenuQueryModel")
public class MenuQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="父ID",example="0")
	private Long parentIdEQ;
	@ApiModelProperty(value ="名称")
	private String nameEQ;
	@ApiModelProperty(value ="路径")
	private String pathEQ;
	@ApiModelProperty(value ="分组")
	private String groupNameEQ;
	@ApiModelProperty(value ="排序",example="0")
	private Integer sortValueEQ;
	@ApiModelProperty(value ="是否有效",example="0")
	private Integer statusEQ;
	@ApiModelProperty(value ="是否删除",example="0")
	private Integer isDelEQ;


}