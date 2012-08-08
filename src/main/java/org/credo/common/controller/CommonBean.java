package org.credo.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("globalSession")
public class CommonBean {
	/**
	 * <p>Description: 将日期转化成24小时的格式</p>
	 * @param date
	 * @return
	 */
	public String formatDate(Date date){
	    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    return sdf.format(date);
	}
}
