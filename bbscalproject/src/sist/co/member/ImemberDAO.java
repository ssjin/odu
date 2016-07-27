package sist.co.member;

public interface ImemberDAO {
	boolean addMember(memberDTO dto);
	memberDTO login(memberDTO dto);
	
}
