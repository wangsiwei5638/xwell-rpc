package com.wsw.bean;

import java.io.Serializable;

/**
 * @author wsw
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = -6434713198472756379L;
	public User(Integer id, String name, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
	}
	private Integer id;
	private String name;
	private String addr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addr=" + addr + "]";
	}
	
}
