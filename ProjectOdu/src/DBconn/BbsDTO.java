package DBconn;

import java.io.Serializable;

/*
DROP TABLE bbsDTO
CASCADE CONSTRAINT;

DROP sequence seq_bbs;



 create table bbsDTO(
	seq number(8) primary key,
	id varchar2(50) not null,
	ref number(8) not null,
	step number(8) not null,
	depth number(8) not null,
	title varchar2(200) not null,
	content varchar2(4000) not null,
	wdate date not null,
	parent number(8) not null,
	del number(1) not null,
	readcount number(8) not null
);

create sequence seq_bbs
start with 1 increment by 1;

alter table bbsDTO 
add constraint fk_bbs_id foreign key(id)
references memberdto(id);

select * from bbsDTO; 

delete 
*/

public class BbsDTO {
	
	private int seq; 		// 글의 순서
	private String id;
	
	private int ref; 		// 부모 글의 번호
	private int step;		// 답글의 순서(답글의 시퀀스)
	private int depth;		// 글의 깊이 번호
	
	private String title;
	private String content;
	private String wdate; 	// 작성날짜 to_date
	private int parent;
	
	private int del;		// 삭제용도
	private int readcount;	// 조회수
	
	public BbsDTO(){}
	
	public BbsDTO(int seq, String id, int ref, int step, int depth, 
			String title, String content, String wdate, int parent){ // 글쓰기 용도
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
	}
	public BbsDTO(int seq, String id, int ref, int step, int depth, 
			String title, String content, String wdate, int parent, int del, int readcount){ 
		
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
	}
	public BbsDTO(String id, String title, String content){			// 글읽기 용도
		this.id = id;
		this.title = title;
		this.content = content;
		
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		
		return "BBS[seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth +
				", title=" + title + ", content=" + content + ", wdate=" + wdate + ", parent=" + parent + 
				", del=" + del + ", readcount=" + readcount + "]";
	}
	
	
}
