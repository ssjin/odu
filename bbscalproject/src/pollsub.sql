CREATE TABLE SIST_POLLSUB(
	POLLSUBID NUMBER NOT NULL,
	POLLID NUMBER NOT NULL,
	ANSWER VARCHAR2(1000) NOT NULL,
	ACOUNT NUMBER NOT NULL,
	CONSTRAINT SIST_POLLSUB_PK PRIMARY KEY("POLLSUBID")
);

CREATE SEQUENCE SIST_POLLSUB_SEQ 
START WITH 1 INCREMENT BY 1;

ALTER TABLE SIST_POLLSUB ADD CONSTRAINT SIST_POLLSUB_FK
FOREIGN KEY("POLLID")
REFERENCES SIST_POLL ("POLLID")