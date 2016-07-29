package odu_together;

import java.util.List;

public interface TOGETHER_INTERFACE {
	//글
	boolean writeTogether(togetherDTO dto);	//글쓰기
	List<togetherDTO> getTogether();	//글 리스트 게시판에 불러오기
	togetherDTO getToDTO(int seq);		//글 상세보기
	void readCount(int seq);			//글 조회수
	boolean deleteContent(int seq);		//글 삭제
	boolean modifyContent(int seq, String content); //글 수정
	boolean DeadContent(int seq); 			//글 마감
	
	//채택
	boolean likeContent(int seq);
	boolean unlikeContent(int seq);
	
	//댓글
	boolean writeToreple(torepleDTO dto); 	//댓글쓰기
	List<torepleDTO> getToreple();
}
