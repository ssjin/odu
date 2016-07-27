package sist.co.member;

import java.io.Serializable;

/*
drop table Member
cascade constraint;

create table Member(
	id varchar2(50) primary key,
	pwd varchar2(50) not null,
	name varchar2(50) not null,
	email varchar2(50) unique,
	auth number(1) not null
);


select * from Member;
*/


public class memberDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private int auth;	// = 3:User 1:Admin
	
	public memberDTO(){}

	public memberDTO(String id, String pwd, String name, String email, int auth){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.auth = auth;		
	}
	
	public memberDTO(String pwd, String name, String email){
		this(null, pwd, name, email, 3);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	public String toString(){
		return "MemberDTO[id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", auth=" + auth + "]";
		
	}
	
}
