package DBconn;

import java.util.List;

public interface DaoInterface {
	// member 관련
		boolean insertMember(MemberDTO dto);
		MemberDTO login(MemberDTO dto);
		
		// bbs 관련
		boolean writeBBS(BbsDTO bbs);
		List<BbsDTO> getBBSList();
		BbsDTO getBbsDTO(int seq);
		void readCount(int seq);
		boolean answer(int seq, BbsDTO bbs);
		boolean deleteContent(int seq);
		boolean modifyContent(int seq, String content);
		List<BbsDTO> searchBBSList(int search_num, String search_data);
}
