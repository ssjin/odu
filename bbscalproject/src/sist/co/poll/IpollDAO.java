package sist.co.poll;

import java.util.List;

public interface IpollDAO {

	List<pollDTO> getPollAllList();
	boolean isVote(voterDTO dto);
	boolean makePoll(PollBean sto);
	pollDTO getPoll(int pollid);
	List<pollsubDTO> getPollSubList(int pollid);
	
	boolean polling(voterDTO dto);

}
