package sist.co.bbs;
import java.io.Serializable;

/*

drop table bbs
cascade constraint;

drop sequence seq_bbs;

create table bbs(
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

alter table bbs 
add constraint fk_bbs_id foreign key(id)
references member(id);

select * from bbs;
	
*/

public class bbsDTO {

	private int seq;	//증가되는 기본넘버
	private String id;
	
	private int ref;	//부모글
	private int step;	//답글의 순서
	private int depth;	//답글의 깊이
	
	private String title;
	private String content;
	private String wdate;
	private int parent;
	
	private int del;
	private int readcount;
	
	public bbsDTO(){}
	
	public bbsDTO(int seq, String id, int ref, int step, int depth, String title, String content, String wdate, int parent, int del, int readcount){
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
	public bbsDTO(String id, String title, String content){
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	@Override
	public String toString(){
		return "BBS[seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", parent=" + parent + ", del=" + del + ", readcount=" + readcount + "]";  
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
	
}
