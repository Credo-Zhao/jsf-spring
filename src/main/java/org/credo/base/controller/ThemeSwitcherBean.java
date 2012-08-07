package org.credo.base.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.credo.base.controller.vo.ThemeVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:样式选择的JavaBean </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@Controller
@Scope("request")
public class ThemeSwitcherBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private GuestPreferences gp; //only set
	
	private Map<String,String> themes; //only get
	private List<ThemeVO> advancedThemes; //only get
	private String theme;	//get & set
	
	public void saveTheme(){
		if(null!=gp){
			gp.setTheme(theme);
		}
	}
	
	@PostConstruct
	public void init(){
		if(null!=gp){
			theme=gp.getTheme();
		}else{
			theme="excite-bike";
		}
		advancedThemes = new ArrayList<ThemeVO>();
        advancedThemes.add(new ThemeVO("afterdark", "afterdark.png")); //黑色+深绿
        advancedThemes.add(new ThemeVO("afternoon", "afternoon.png"));  //淡蓝色
        advancedThemes.add(new ThemeVO("afterwork", "afterwork.png")); //深白
        advancedThemes.add(new ThemeVO("aristo", "aristo.png"));   //灰色
        advancedThemes.add(new ThemeVO("black-tie", "black-tie.png"));  //黑领带色
        advancedThemes.add(new ThemeVO("blitzer", "blitzer.png"));	//红色
        advancedThemes.add(new ThemeVO("bluesky", "bluesky.png"));	//天蓝色
        advancedThemes.add(new ThemeVO("casablanca", "casablanca.png")); //棕色
        advancedThemes.add(new ThemeVO("cruze", "cruze.png"));	//黑色
        advancedThemes.add(new ThemeVO("cupertino", "cupertino.png")); //浅蓝色
        advancedThemes.add(new ThemeVO("dark-hive", "dark-hive.png")); //重黑
        advancedThemes.add(new ThemeVO("dot-luv", "dot-luv.png")); //条纹深蓝+黑色
        advancedThemes.add(new ThemeVO("eggplant", "eggplant.png")); //深紫色的
        advancedThemes.add(new ThemeVO("excite-bike", "excite-bike.png")); //按钮蓝,其他白
        advancedThemes.add(new ThemeVO("flick", "flick.png")); //背景白,按钮粉红
        advancedThemes.add(new ThemeVO("glass-x", "glass-x.png")); //玻璃,不易分辨是否选择,pass
        advancedThemes.add(new ThemeVO("home", "home.png"));//灰黑
        advancedThemes.add(new ThemeVO("hot-sneaks", "hot-sneaks.png"));
        advancedThemes.add(new ThemeVO("humanity", "humanity.png")); //桔色
        advancedThemes.add(new ThemeVO("le-frog", "le-frog.png")); //重绿
        advancedThemes.add(new ThemeVO("midnight", "midnight.png"));
        advancedThemes.add(new ThemeVO("mint-choc", "mint-choc.png"));
        advancedThemes.add(new ThemeVO("overcast", "overcast.png"));
        advancedThemes.add(new ThemeVO("pepper-grinder", "pepper-grinder.png"));
        advancedThemes.add(new ThemeVO("redmond", "redmond.png"));//不积极蓝
        advancedThemes.add(new ThemeVO("rocket", "rocket.png"));
        advancedThemes.add(new ThemeVO("sam", "sam.png"));
        advancedThemes.add(new ThemeVO("smoothness", "smoothness.png"));
        advancedThemes.add(new ThemeVO("south-street", "south-street.png"));
        advancedThemes.add(new ThemeVO("start", "start.png"));
        advancedThemes.add(new ThemeVO("sunny", "sunny.png"));
        advancedThemes.add(new ThemeVO("swanky-purse", "swanky-purse.png"));
        advancedThemes.add(new ThemeVO("trontastic", "trontastic.png"));
        advancedThemes.add(new ThemeVO("ui-darkness", "ui-darkness.png"));
        advancedThemes.add(new ThemeVO("ui-lightness", "ui-lightness.png"));
        advancedThemes.add(new ThemeVO("vader", "vader.png"));
        
        themes = new TreeMap<String, String>();
        themes.put("Afterdark", "afterdark");
        themes.put("Afternoon", "afternoon");
        themes.put("Afterwork", "afterwork");
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Cruze", "cruze");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Home", "home");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
	}
	
	public void setGp(GuestPreferences gp) {
		this.gp = gp;
	}
	public Map<String,String> getThemes() {
		return themes;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public List<ThemeVO> getAdvancedThemes() {
		return advancedThemes;
	}
	
}
