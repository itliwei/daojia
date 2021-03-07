package io.github.itliwei.daojia.system.base.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.base.entity.Dictionary;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DictionaryVO", description = "DictionaryVO")
public class DictionaryVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "父ID")
	private Integer parentId;
	@ApiModelProperty(value = "编码")
	private String dicCode;
	@ApiModelProperty(value = "数值")
	private String dicValue;
	@ApiModelProperty(value = "类型")
	private String dicType;
	@ApiModelProperty(value = "排序")
	private Integer sortValue;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */

	public static DictionaryVO convert2DictionaryVO(Dictionary source){
		DictionaryVOConvert convert = new DictionaryVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class DictionaryVOConvert extends Converter<DictionaryVO, Dictionary> {
		@Override
		protected Dictionary doForward(DictionaryVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected DictionaryVO doBackward(Dictionary source) {
		DictionaryVO target = new DictionaryVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}