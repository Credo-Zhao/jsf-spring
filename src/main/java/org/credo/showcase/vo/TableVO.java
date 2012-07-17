package org.credo.showcase.vo;

public class TableVO {
	private long id;
	private String name;
	private String age;

	public TableVO() {

	}

	public TableVO(long id,String name,String age) {
		this.id=id;
		this.name=name;
		this.age=age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
