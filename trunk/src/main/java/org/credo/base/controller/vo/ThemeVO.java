package org.credo.base.controller.vo;

import java.io.Serializable;

public class ThemeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String image;
	
	public ThemeVO(){}
		
	public ThemeVO(String name,String image){
		this.name=name;
		this.image=image;
	}
	
	@Override
    public String toString() {
        return name;
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
