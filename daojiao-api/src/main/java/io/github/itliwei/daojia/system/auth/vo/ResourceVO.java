package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.Resource;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResourceVO", description = "ResourceVO")
public class ResourceVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "编码")
	private String code;
	@ApiModelProperty(value = "菜单ID")
	private Long menuId;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */

	public static ResourceVO convert2ResourceVO(Resource source){
		ResourceVOConvert convert = new ResourceVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class ResourceVOConvert extends Converter<ResourceVO, Resource> {
		@Override
		protected Resource doForward(ResourceVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected ResourceVO doBackward(Resource source) {
		ResourceVO target = new ResourceVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}