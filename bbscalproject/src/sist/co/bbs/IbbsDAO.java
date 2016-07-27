package sist.co.bbs;

import java.util.List;

public interface IbbsDAO {

	boolean writeBBS(bbsDTO bbs);
	List<bbsDTO> getBBSList();
	bbsDTO getBBS(int seq);
	void readCount(int seq);
	boolean answer(int seq, bbsDTO bbs);
	boolean deleteContent(int seq);
}
