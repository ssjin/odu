package sist.co.poll;

import java.io.Serializable;
import java.util.Date;


/*
CREATE TABLE SIST_VOTER(
		VOTERID NUMBER NOT NULL,
		POLLID NUMBER,
		POLLSUBID NUMBER NOT NULL,
		ID VARCHAR2(50) NOT NULL,
		REGDATE DATE NOT NULL,
		CONSTRAINT SIST_VOTER_PK PRIMARY KEY("VOTERID")
	);


	CREATE SEQUENCE SIST_VOTER_SEQ 
	START WITH 1 INCREMENT BY 1;

	ALTER TABLE SIST_VOTER ADD CONSTRAINT SIST_VOTER_FK
	FOREIGN KEY ("POLLID")
	REFERENCES SIST_POLL("POLLID")

	ALTER TABLE SIST_VOTER ADD CONSTRAINT SIST_VOTER_FK2
	FOREIGN KEY ("POLLSUBID")
	REFERENCES SIST_POLLSUB("POLLSUBID")

	ALTER TABLE SIST_VOTER ADD CONSTRAINT SIST_VOTER_FK3
	FOREIGN KEY ("ID")
	REFERENCES MEMBER("ID")
*/

// 응답자: 누가 투표했는지 어느 문제에 투표했는지
public class voterDTO implements Serializable {
	private int voterid;	// 답한 사람을 카운터
	private int pollid;		// 질문 번호
	private int pollsubid;	// 보기 나온 순서의 번호
	private String id;		// 답한 사람의 ID
	private Date regdate;	// 답한 날짜와 시간
	
	public voterDTO() {}

	public voterDTO(int voterid, int pollid, int pollsubid, String id, Date regdate) {
		super();
		this.voterid = voterid;
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
		this.regdate = regdate;
	}
	
	public voterDTO(int pollid, int pollsubid, String id) {
		super();
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
	}

	@Override
	public String toString() {
		return "voterDTO [voterid=" + voterid + ", pollid=" + pollid + ", pollsubid=" + pollsubid + ", id=" + id
				+ ", regdate=" + regdate + "]";
	}

	public int getVoterid() {
		return voterid;
	}

	public void setVoterid(int voterid) {
		this.voterid = voterid;
	}

	public int getPollid() {
		return pollid;
	}

	public void setPollid(int pollid) {
		this.pollid = pollid;
	}

	public int getPollsubid() {
		return pollsubid;
	}

	public void setPollsubid(int pollsubid) {
		this.pollsubid = pollsubid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	

}
