package org.credo.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description: 实体基类，封装了实体的公共信息基本的抽象实体父类。它实现了公共的主键 Id 、创建时间和更新时间。并自动生成创建时间和更新时间。
 * 同时，它还实现了基于主键 Id 的 hashcode 和 equals 方法，可以用来比较两个对象。</p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "createBy", length = 30, nullable = true)
    private String createBy;
	
	@Column(name = "createTime",nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
	
	@Column(name = "updateBy", length = 30, nullable = true)
    private String updateBy;
	
    @Column(name = "updateTime",nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    public BaseEntity() {
		super();
	}
    
    //before persist entity
 	@PrePersist public void prePersist() {
 		if(createTime == null) {
 			createTime = updateTime = new Date();
 		}
 	}
 	// after persist entity
 	@PostPersist public void postPersist() {}
 	
 	// before update entity
 	@PreUpdate public void preUpdate() {
 		updateTime = new Date();
 	}
 	// after update entity
 	@PostUpdate public void postUpdate() {}
 	// before remove entity
 	@PreRemove public void preRemove() {}
 	// after remove entity
 	@PostRemove public void postRemove() {}
    
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
