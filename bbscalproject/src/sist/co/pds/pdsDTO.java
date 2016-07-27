package sist.co.pds;

import java.io.Serializable;

/*

DROP TABLE PDS
cascade constraint

DROP SEQUENCE SEQ_PDS
 
CREATE TABLE PDS(
		SEQ NUMBER(8) PRIMARY KEY,
		ID VARCHAR2(50) NOT NULL,
		TITLE VARCHAR2(200) NOT NULL,
		CONTENT VARCHAR2(4000) NOT NULL,
		FILENAME VARCHAR2(50) NOT NULL,
		READCOUNT NUMBER(8) NOT NULL,
		DOWNCOUNT NUMBER(8) NOT NULL,
		REGIDATE DATE NOT NULL,
		DEL NUMBER(1) NOT NULL
	)

	CREATE SEQUENCE SEQ_PDS
	START WITH 1 INCREMENT BY 1

	ALTER TABLE PDS
	ADD CONSTRAINT FK_SIST_ID FOREIGN KEY(ID)
	REFERENCES MEMBER(ID)
	
*/


public class pdsDTO implements Serializable {
   private int seq;
   private String id;
   private String title;
   private String content;
   private String filename;   //파일 경로
   private int readcount;
   private int downcount;
   private String regidate;   //파일 올린 날짜 
   private int del;
   
   public pdsDTO(){
      
   }
   
   public pdsDTO(int seq, String id, String title, String content, String filename, int readcount, int downcount,
         String regidate, int del) {
      super();
      this.seq = seq;
      this.id = id;
      this.title = title;
      this.content = content;
      this.filename = filename;
      this.readcount = readcount;
      this.downcount = downcount;
      this.regidate = regidate;
      this.del = del;
   }
   public pdsDTO(String id, String title, String content, String filename) {
	      super();
	      this.id = id;
	      this.title = title;
	      this.content = content;
	      this.filename = filename;
	   }

   
   
    public int getDel() {
    	return del;
    }

	public void setDel(int del) {
		this.del = del;
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

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public int getReadcount() {
      return readcount;
   }

   public void setReadcount(int readcount) {
      this.readcount = readcount;
   }

   public int getDowncount() {
      return downcount;
   }

   public void setDowncount(int downcount) {
      this.downcount = downcount;
   }

   public String getRegidate() {
      return regidate;
   }

   public void setRegidate(String regidate) {
      this.regidate = regidate;
   }
   
   @Override
   public String toString() {
		return "PDS[seq=" + seq + ", id=" + id + ",title=" + title + ", content=" + content + ",filename=" + filename + ", readcount=" + readcount + ", downcount=" + downcount + ", regidate=" + regidate + ", del=" + del +  "]"; 
   }
	
   
}