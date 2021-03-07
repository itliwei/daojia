package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.Menu;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MenuVO", description = "MenuVO")
public class MenuVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "父ID")
	private Long parentId;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "路径")
	private String path;
	@ApiModelProperty(value = "分组")
	private String groupName;
	@ApiModelProperty(value = "排序")
	private Integer sortValue;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */

	public static MenuVO convert2MenuVO(Menu source){
		MenuVOConvert convert = new MenuVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class MenuVOConvert extends Converter<MenuVO, Menu> {
		@Override
		protected Menu doForward(MenuVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected MenuVO doBackward(Menu source) {
		MenuVO target = new MenuVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}