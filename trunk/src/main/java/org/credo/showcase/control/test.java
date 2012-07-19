package org.credo.showcase.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		int yearTime=calendar.get(Calendar.YEAR);
		int monthTime=calendar.get(Calendar.MONTH)+1;
		System.out.println(yearTime+"-"+monthTime);
		SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy-MM");
		if(monthTime<10){
			System.out.println(yearTime+"-0"+monthTime);
		}else{
			System.out.println(yearTime+"-"+monthTime);
		}
		try {
			Date dd=sdf11.parse("2009-9");
			System.out.println(dd);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Date date1;
		
		String t1="2007-11";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		try {
			Date dd=sdf1.parse(t1);
			System.out.println(dd);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    		try {
				date1 = sdf.parse("2007-7");
				System.out.println("DATE1:"+date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
