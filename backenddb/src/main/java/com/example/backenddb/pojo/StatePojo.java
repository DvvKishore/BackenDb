package com.example.backenddb.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="state")
public class StatePojo {
	@Id
	@GeneratedValue
	private Integer sid;
	private String scode;
	private String sname;
	@ManyToOne
	@JoinColumn(name="fcid")
	private CountryPojo country;
	public Integer getSid() {
		return sid; 
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public CountryPojo getCountry() {
		return country;
	}
	public void setCountry(CountryPojo country) {
		this.country = country;
	}
	

}
