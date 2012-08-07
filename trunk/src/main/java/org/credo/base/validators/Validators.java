package org.credo.base.validators;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description: 为自定义校验器开发专用标签--自定义校验器类 ,含有非空（排除空格），正则验证！不使用注解，在faces-config中配置</p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
//@FacesValidator("customValidator")
public class Validators implements Validator,Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext context, UIComponent component, Object toValidate) throws ValidatorException {
		System.out.println("进入自定义验证器!");
		//获取组件树上对应属性值,获取"regex"资源文件,获取要验证的数值
		Map<String, Object> attributesMap = component.getAttributes();
		ResourceBundle rb = ResourceBundle.getBundle("regex");
		//get HtmlInputText's label by UIComponent.
		HtmlInputText htmlInputText = (HtmlInputText) component;
		String label=htmlInputText.getLabel();
		
		String required = (String) attributesMap.get("empty");
		String requiredMessage = (String) attributesMap.get("emptyMessage");
		String min = (String) attributesMap.get("min");
		String minMessage = (String) attributesMap.get("minMessage");
		String regex = (String) attributesMap.get("regex");
		String regexMessage = (String) attributesMap.get("regexMessage");
		
		System.out.println("label:"+label);
		System.out.println("required:"+required);
		System.out.println("min:"+min);
		System.out.println("regex:"+regex);
		
		String value = toValidate == null ? "" : toValidate.toString();

		// 进行非空的验证,含空格和null分别判断出来.文本框默认为""的话归到非null错误.
		if (required != null) {
			String rbKey="";
			if (null==value||"".equals(value)) {
				rbKey="NOTNULL";
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, requiredMessage == null?this.disposePlaceholder(rb,rbKey,label):requiredMessage,""));
			}
			else if (value.length() > 0 && value.trim().equals("")) {
				rbKey="NOTSPACE";
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, requiredMessage == null?this.disposePlaceholder(rb,rbKey,label):requiredMessage,""));
			}
		}
		// 进行最小长度的验证,定义的数字是最小的长度
        if (min != null) {
            // 如果没有值就不进行验证,value不能去空格.
            if (value.equals("") || value.length() == 0) { return; }
            int minInt = 0;
            try {
            	minInt = Integer.valueOf(min);
            } catch (NumberFormatException e) {
            	e.printStackTrace();
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "程序员:验证的后台指定长度必须为int类型!", ""));
            }
            String temp=label+","+minInt;
            if (value.length() < minInt) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, minMessage == null ?this.disposePlaceholder(rb,"MINLENGTH",temp): minMessage, ""));
            }
        }
		
		// 如果对应的regex,mark,message不匹配,输出错误信息."请核对多个正则验证属性是否一一对应!(##隔开.)",""));
        if (regex != null) {
            // 如果没有值就不进行验证
            if (value.equals("") || value.trim().length() == 0) { return; }
            String backgroundRegex = ""; 
            String[] regexArray = regex.split("##"); //解析前台regex
            int regexNum = regexArray.length;		 //获取regex数量 
            String[] regexMessageArray;				 //定义一个regex输出信息数组

            if (regexMessage == null) {
                // 处理不带自定义消息的情况
                regexMessageArray = new String[regexNum];
                for (int p = 0; p < regexNum; p++) {
                    regexMessageArray[p] = "none";
                }
            } else {
                regexMessageArray = regexMessage.split("##");
            }

            for (int i = 0; i < regexArray.length; i++) {
                if (regexMessageArray[i].toString().equals("none")) {
                    regexMessageArray[i] = null;
                }
                try {
                    // 如果没有在资源文件中找到所属值,就判定为前台写的是正则表达式,catch捕捉到丢失文件异常,直接抛出验证信息.
                    backgroundRegex = rb.getString(regexArray[i]);
                } catch (MissingResourceException e) {
                	e.printStackTrace();
                	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "程序员:请确定后台regex.properties中有此正则表达式!", ""));
                } catch (Exception e) {
                	e.printStackTrace();
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "系统异常:" + e.getMessage() + "请联系管理员!错误是:" + regexArray[i] + "!"));
                }

                // 下面是正常的处理.即regex资源文件中存在的正则验证.!!!!
				if (!(value).matches(backgroundRegex)) {
					String msg = regexMessageArray[i];
					String rbKey = regexArray[i] + "INFO";
					String errorMessage = "";
					try {
						errorMessage = msg == null ? this.disposePlaceholder(rb, rbKey, label) : msg;
					} catch (MissingResourceException e) {
						e.printStackTrace();
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "程序员:请确定后台regex.properties中有此正则表达式对应错误信息!", ""));
					} catch (Exception e) {
						e.printStackTrace();
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "系统异常:" + e.getMessage() + "请联系管理员!" + regexArray[i]));
					}
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, ""));
				}
            }
        }
	}
	
	// 占位符处理,需要传入资源文件中的name值,资源文件的
	private String disposePlaceholder(ResourceBundle rb, String rbName, String placeholderStr) {
		String errorinfo;
		if (null==placeholderStr||"".equals(placeholderStr)) {
			placeholderStr = "";
			errorinfo = "请设定组件的label值!";
		} else {
			Object[] mark=placeholderStr.split(",");
			errorinfo = MessageFormat.format(rb.getString(rbName), mark);
		}
		return errorinfo;
	}
	
}
