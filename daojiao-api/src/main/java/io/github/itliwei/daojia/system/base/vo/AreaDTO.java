package io.github.itliwei.daojia.system.base.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.base.entity.Area;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AreaDTO", description = "AreaDTO")
public class AreaDTO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "父ID")
	private Long pid;
	@ApiModelProperty(value = "简称")
	private String shortName;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "类型")
	private String mergerName;
	@ApiModelProperty(value = "类型")
	private String code;
	@ApiModelProperty(value = "邮政编码")
	private String zipCode;
	@ApiModelProperty(value = "类型")
	private String first;
	@ApiModelProperty(value = "类型")
	private Double lng;
	@ApiModelProperty(value = "类型")
	private Double lat;
	/* 扩展 */

	/* 转换 */
	public Area convert2Area(){
		AreaDTOConvert convert = new AreaDTOConvert();
		Area target = convert.convert(this);
		return target;
	}


	private static class AreaDTOConvert extends Converter<AreaDTO, Area> {
		@Override
		protected Area doForward(AreaDTO source) {
			Area target = new Area();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected AreaDTO doBackward(Area source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}