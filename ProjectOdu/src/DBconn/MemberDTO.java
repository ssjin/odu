package DBconn;

import java.io.Serializable;

public class MemberDTO {
	
	private String id;
	private String name;
	private String pw;
	private String email;
	private int auth;
	
	public MemberDTO() {}
	
	public MemberDTO(String id, String name, String pw, String email, int auth){
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.auth = auth;
	}
	public MemberDTO(String name, String pw, String email){
		this(null, name, pw, email, 3); // 회원가입 정보를 확인하기 위한 생성자
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	@Override
	public String toString() {
		return "ID: " + id + " PW: " + pw + " Name: " + name + " Email: " + email + " auth: " + auth;
	}
	
	
}
