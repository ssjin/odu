package sist.co.poll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sist.co.member.memberDAO;

public class pollDAO implements IpollDAO {

	boolean isS=true;
	private static pollDAO polldao;

	public static pollDAO getInstance() {
		if(polldao == null){
			polldao = new pollDAO();
		}
		return polldao;
	}
		
	@Override
	public List<pollDTO> getPollAllList() {
		
		String sql=" SELECT POLLID, ID, QUESTION, "
				+ " SDATE, EDATE, ITEMCOUNT, "
				+ " POLLTOTAL, REGDATE "
				+ " FROM SIST_POLL "
				+ " ORDER BY SDATE DESC ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		List<pollDTO> pLists = new ArrayList<pollDTO>(); 
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success getPollAllList");
			
			psmt=conn.prepareStatement(sql);
			log("3/6 Success getPollAllList");
			
			rs=psmt.executeQuery();
			log("4/6 Success getPollAllList");
			
			while(rs.next()){
				int i=1;
				pollDTO dto=new pollDTO(
							rs.getInt(i++),		// POLLID
							rs.getString(i++), 	// ID
							rs.getString(i++), 	// QUESTION
							rs.getDate(i++), 	// SDATE
							rs.getDate(i++), 	// EDATE
							rs.getInt(i++), 	// ITEMCOUNT
							rs.getInt(i++),		// POLLTOTAL
							rs.getDate(i++)		// REGDATE
						);
				pLists.add(dto);
			}
			log("5/6 Success getPollAllList");
			
		}catch(SQLException e){
			log("Fail getPollAllList", e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getPollAllList");
		}		
		
		return pLists;
	}

	@Override
	public boolean isVote(voterDTO dto) {	// 투표를 했는지 안했는지
		
		String sql=" SELECT VOTERID, POLLID, POLLSUBID, ID, REGDATE "
				+ " FROM SIST_VOTER "
				+ " WHERE POLLID=? AND ID=? ";
		//				투표 질문 번호             member ID
				
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		boolean isS = false;
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success isVote");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, dto.getPollid());
			psmt.setString(2, dto.getId());
			log("3/6 Success isVote");
			
			rs=psmt.executeQuery();
			log("4/6 Success isVote");
			
			if(rs.next()){
				isS = true;		// 투표를 했다
			}
			log("5/6 Success isVote");
		}catch(SQLException e){
			log("Fail isVote", e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success isVote");
		}		
		
		return isS;
	}	

	@Override
	public boolean makePoll(PollBean dto) {
		
		// 문제
		String sql1 = " INSERT INTO SIST_POLL "
				+ " (POLLID, ID, QUESTION, SDATE, EDATE, "
				+ " ITEMCOUNT, POLLTOTAL, REGDATE) "
				+ " VALUES(SIST_POLL_SEQ.NEXTVAL, "
				+ "		?, ?, ?, ?, ?, "
				+ "		0, SYSDATE) ";
		
		String sql2 = " SELECT NVL(MAX(POLLID), 0) FROM SIST_POLL ";
		
		// 보기
		String sql3 = " INSERT INTO SIST_POLLSUB "
				+ "	(POLLSUBID, POLLID, ANSWER, ACOUNT ) "
				+ " VALUES(SIST_POLLSUB_SEQ.NEXTVAL, "
				+ "	?, ?, 0) ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		int pollsubid = -1;
		int count = 0;
		
		try{
			conn=memberDAO.getConnection();
			conn.setAutoCommit(false);			
			log("2/6 Success makePoll");
			
			psmt=conn.prepareStatement(sql1);
			int i=1;
			psmt.setString(i++, dto.getId());
			psmt.setString(i++, dto.getQuestion());
			psmt.setDate(i++, 
					toDate(dto.getSyear(), dto.getSmonth(), dto.getSday()));
			psmt.setDate(i++, 
					toDate(dto.getEyear(), dto.getEmonth(), dto.getEday()));
			psmt.setInt(i++, dto.getItemcount());			
			count=psmt.executeUpdate();
			
			psmt.clearParameters();
			
			psmt=conn.prepareStatement(sql2);
			rs=psmt.executeQuery();
			if(rs.next()){
				pollsubid=rs.getInt(1);
			}
			
			psmt.clearParameters();
			
			psmt=conn.prepareStatement(sql3);
			
			String[] pollnums=dto.getPollnum();
			
			for(int j = 0;j < dto.getItemcount(); j++){
				i = 1;
				psmt.setInt(i++, pollsubid);
				psmt.setString(i++, pollnums[j]);
				psmt.addBatch();
			}
			log("3/6 Success makePoll");
			psmt.executeBatch();
			
			log("4/6 Success makePoll");
			conn.commit();
		}catch(SQLException e){
			log("Fail makePoll", e);
			try{
				conn.rollback();
			}catch(SQLException ex){}
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException e){}
			memberDAO.close(conn, psmt, rs);			
		}		
		
		return count>0?true:false;
	}
		
	@Override
	public pollDTO getPoll(int pollid) {
	
		
		String sql=" SELECT POLLID, ID, QUESTION, SDATE, EDATE, ITEMCOUNT, POLLTOTAL, REGDATE "
				+ " FROM SIST_POLL "
				+ " WHERE POLLID=? ";
				
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		pollDTO poll = null;
		
		
		
		try{
			conn=memberDAO.getConnection();		
			log("2/6 Success getPoll");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, pollid);
			log("3/6 Success getPoll");
			
			rs=psmt.executeQuery();
			log("4/6 Success getPoll");
			
			while(rs.next()){
				int i=1;
				poll = new pollDTO(
						rs.getInt(i++),		// POLLID
						rs.getString(i++), 	// ID
						rs.getString(i++), 	// QUESTION
						rs.getDate(i++), 	// SDATE
						rs.getDate(i++), 	// EDATE
						rs.getInt(i++), 	// ITEMCOUNT
						rs.getInt(i++),		// POLLTOTAL
						rs.getDate(i++)		// REGDATE
					);
			}
			

			log("5/6 Success getPoll");
		}catch(SQLException e){
			log("Fail getPoll",e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getPoll");
		}
		
		return poll;
	}

	
	
	
	@Override
	public List<pollsubDTO> getPollSubList(int pollid) {
	
		
		String sql=" SELECT POLLSUBID, POLLID, ANSWER, ACOUNT "
				+ " FROM SIST_POLLSUB "
				+ " WHERE POLLID=?";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		List<pollsubDTO> pLists = new ArrayList<pollsubDTO>();
		
		try{
			conn=memberDAO.getConnection();		
			log("2/6 Success getPollSubList");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, pollid);
			log("3/6 Success getPollSubList");
			
			rs=psmt.executeQuery();
			log("4/6 Success getPollSubList");
			
			while(rs.next()){
				int i = 1;
				pollsubDTO pollsub = new pollsubDTO(
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getString(i++),
							rs.getInt(i++)
						
						);
				pLists.add(pollsub);
						
			}
			log("5/6 Success getPollSubList");
			
		}catch(SQLException e){
			log("Fail getPollSubList",e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getPollSubList");
			
		}
		
	
		return pLists;
	}

	@Override
	public boolean polling(voterDTO dto) {
		
		
		// 투표한 사람의 정보 저장
		String sql1=" INSERT INTO SIST_VOTER( "
				+ " VOTERID, POLLID, POLLSUBID, ID, REGDATE) "
				+ " VALUES(SIST_VOTER_SEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
					
		
		// 보기들을 가지고 있는 pollsubDTO에 카운터를 올려준다.
		String sql2=" UPDATE SIST_POLLSUB SET "
				+ " ACOUNT=ACOUNT+1 "
				+ " WHERE POLLID=? AND POLLSUBID=? ";
				
		
		// pollDTO 투표할 주제로 그 해당 주제에 투표를 몇명이 했는지
		String sql3=" UPDATE SIST_POLL SET "
				+ " POLLTOTAL=POLLTOTAL+1 "
				+ " WHERE POLLID=? ";
		
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		int count=0;
		
		try{
			conn=memberDAO.getConnection();		
			conn.setAutoCommit(false);
			log("2/6 Success polling");
			
			psmt=conn.prepareStatement(sql1);
			int i=1;
			psmt.setInt(i++, dto.getPollid());		// 문제번호
			psmt.setInt(i++, dto.getPollsubid());	// 선택한 보기
			psmt.setString(i++, dto.getId());		// 투표한 사람
			log("3/6 Success polling - 1");
			
			count=psmt.executeUpdate();
			log("4/6 Success polling - 1");
			// ------------------------------------------------ sql1 작업
			
			psmt.clearParameters();
			
			psmt=conn.prepareStatement(sql2);
			i=1;
			psmt.setInt(i++, dto.getPollid());
			psmt.setInt(i++, dto.getPollsubid());
			log("3/6 Success polling - 2");
			count=count*psmt.executeUpdate();
			log("4/6 Success polling - 2");
			// ------------------------------------------------- sql2 작업
			
			psmt.clearParameters();
			
			psmt=conn.prepareStatement(sql3);
			i=1;
			psmt.setInt(i, dto.getPollid());
			log("3/6 Success polling - 3");
			count=count*psmt.executeUpdate();
			log("4/6 Success polling - 3");
			// ------------------------------------------------- sql3 작업
			
			conn.commit();
				
		}catch(SQLException e){
			log("Fail polling", e);
			try{
				conn.rollback();
			}catch(SQLException ex){}
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException e){}
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success polling");
			
		}
		return count>0?true:false;
	}

	public void	log(String msg) {		
		if(isS){
			System.out.println(getClass() + ": " + msg);
		}		
	}	
	public void	log(String msg, Exception e) {
		boolean isS=true;
		if(isS){
			System.out.println(e + ": " + getClass() + ": " + msg);
		}		
	}
	
	public static String str(String msg){
		return msg==null?"":msg.trim();
	}
	
	public Date toDate(int year, int month, int day){
		String s = year + "-" + two(month + "") + "-" + two(day+"");
		Date d = Date.valueOf(s);
		return d;
	}
	public String two(String msg){		// 2  -> 02
		return msg.trim().length()<2 ? "0"+msg : msg.trim();	
	}
}






