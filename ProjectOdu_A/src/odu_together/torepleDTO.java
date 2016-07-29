package odu_together;
/*
CREATE TABLE BBSREPLE(
   SEQ NUMBER(8) PRIMARY KEY,
   BBS_NUM NUMBER(1) NOT NULL,            -- 게시판 고유번호: 1로 지정
   ID VARCHAR2(50) NOT NULL,
   REF NUMBER(8) NOT NULL,
   STEP NUMBER(8) NOT NULL,
   CONTENT VARCHAR2(4000) NOT NULL,
   WDATE DATE NOT NULL,
   DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE SEQ_BBSREPLE
START WITH 1 INCREMENT BY 1;


ALTER TABLE BBSREPLE ADD CONSTRAINT BBSREPLE_FK1
FOREIGN KEY (ID)
REFERENCES MEMBER(ID); 

*/
import java.io.Serializable;

public class torepleDTO implements Serializable {
	
	private int seq;
	private int bbs_num;
	private String id;
	private int ref;
	private int step;
	private String content;
	private String wdate;
	private int del;
	
	
	
	public torepleDTO() {}

	public torepleDTO(int seq, int bbs_num, String id, int ref, int step, String content, String wdate, int del) {
		// 댓글 생성.
		this.seq = seq;
		this.bbs_num = bbs_num;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
	}
	
	public torepleDTO(String id, int ref, String content) {
		this.id = id;
		this.ref = ref;
		this.content = content;
	}

	@Override
	public String toString() {
		return "torepleDTO [seq=" + seq + ", bbs_num=" + bbs_num + ", id=" + id + ", ref=" + ref + ", step=" + step
				+ ", content=" + content + ", wdate=" + wdate + ", del=" + del + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getBbs_num() {
		return bbs_num;
	}

	public void setBbs_num(int bbs_num) {
		this.bbs_num = bbs_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
}
