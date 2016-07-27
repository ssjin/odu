package sist.co.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sist.co.member.memberDAO;

public class bbsDAO implements IbbsDAO {
	
	private boolean isS=true;
	private static bbsDAO bbsdao;
	@Override
	public boolean writeBBS(bbsDTO bbs) {
	
		String sql=" INSERT INTO BBS "
				+ " (SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, (SELECT NVL(MAX(REF), 0)+1 FROM BBS), 0, 0, ?, ?, SYSDATE, 0, 0, 0) ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		
		int count=0;
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success writeBBS");
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getTitle());
			psmt.setString(3, bbs.getContent());
			log("3/6 Success writeBBS");
			
			count=psmt.executeUpdate();
			log("4/6 Success writeBBS");
			
			
		}catch(SQLException e){
			log("Fail writeBBS", e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success writeBBS");
		}
		
		return count>0?true:false;
	}
	
	
	public static bbsDAO getInstance() {
		if(bbsdao == null){
			bbsdao = new bbsDAO();
		}
		return bbsdao;
	}
	
	

	@Override
	public List<bbsDTO> getBBSList() {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT "
				+ " FROM BBS "
				+ " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		List<bbsDTO> bbslist = new ArrayList<bbsDTO>();
		
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success getBBSList");
			
			psmt=conn.prepareStatement(sql);
			log("3/6 Success getBBSList");
			
			rs=psmt.executeQuery();
			log("4/6 Success getBBSList");
			
			while(rs.next()){
				
				int i=1;
				bbsDTO bb=new bbsDTO(
							rs.getInt(i++),			//seq
							rs.getString(i++),		//id
							rs.getInt(i++),			//ref
							rs.getInt(i++),			//step
							rs.getInt(i++),			//depth
							rs.getString(i++),		//title
							rs.getString(i++),		//content
							rs.getString(i++),		//wdate
							rs.getInt(i++),			//parent
							rs.getInt(i++),			//del
							rs.getInt(i++)			//readcount
						);
				bbslist.add(bb);
				log("5/6 Success getBBSList");
			}
			
		}catch(SQLException e){
			log("Fail getBBSList", e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getBBSList");
			
		}
		return bbslist;
	}



	@Override
	public bbsDTO getBBS(int seq) {

		
		
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT "
				+ " FROM BBS "
				+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		bbsDTO bbs=null;
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success getBBS");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success getBBS");
			
			rs=psmt.executeQuery();
			log("4/6 Success getBBS");
			
			while(rs.next()){
				int i=1;
				bbs = new bbsDTO(
						rs.getInt(i++),			//seq
						rs.getString(i++),		//id
						rs.getInt(i++),			//ref
						rs.getInt(i++),			//step
						rs.getInt(i++),			//depth
						rs.getString(i++),		//title
						rs.getString(i++),		//content
						rs.getString(i++),		//wdate
						rs.getInt(i++),			//parent
						rs.getInt(i++),			//del
						rs.getInt(i++)			//readcount
						);
			}
			log("5/6 Success getBBS");	
		}catch(SQLException e){
			log("Fail getBBS", e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getBBS");
			
		}
		return bbs;
	}


	
	@Override
	public void readCount(int seq) {
		
		String sql=" UPDATE BBS SET "
				+ " READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
				
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success readCount");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success readCount");
			
			psmt.executeUpdate();
			log("4/6 Success readCount");
			
			
		}catch(SQLException e){
			log("Fail readCount", e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success readCount");
		}
	}



	
	@Override
	public boolean answer(int seq, bbsDTO bbs) {
		String sql1=" UPDATE BBS SET "
				+ " STEP=STEP+1 "
				+ " WHERE REF=(SELECT REF FROM BBS WHERE SEQ=?) "
				+ " AND STEP > (SELECT STEP FROM BBS WHERE SEQ=?) ";
		
		String sql2=" INSERT INTO BBS "
				+ " (SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, (SELECT REF FROM BBS WHERE SEQ=?), (SELECT STEP FROM BBS WHERE SEQ=?)+1, (SELECT DEPTH FROM BBS WHERE SEQ=?)+1, ?, ?, SYSDATE, ?, 0, 0)";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		int count=0;
			//////////////////////////////////////// update 부분
		try{
			conn=memberDAO.getConnection();
			conn.setAutoCommit(false);
			log("2/6 Success answer");
			
			psmt=conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			log("3/6 Success answer");
			count=psmt.executeUpdate();
			
			///////////////////////////////////////// update 부분
			
			psmt.clearParameters();
			
			///////////////////////////////////////// insert 부분
			
			psmt=conn.prepareStatement(sql2);
			int i=1;
			psmt.setString(i++, bbs.getId());
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setString(i++, bbs.getTitle());
			psmt.setString(i++, bbs.getContent());
			psmt.setInt(i++, seq);
			
			count=psmt.executeUpdate();
			conn.commit();
			log("4/6 Success answer");
			
			///////////////////////////////////////// insert 부분
			
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException ex){}
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException e){}
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success answer");
			
		}
		
		
		
		
		
		return count>0?true:false;
	}


	
	
	
	
	
	
	
	@Override
	   public boolean deleteContent(int seq) {
	      String sql=" UPDATE BBS SET del=1 "
	            + " where seq=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      int count = 0;
	      
	      try{
	         conn = memberDAO.getConnection();
	         log("2/6 Success deleteCountent");
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, seq);
	         log("3/6 Success deleteCountent");
	         count = psmt.executeUpdate();
	         log("4/6 Success deleteCountent");
	         
	      }catch(SQLException e){
	         log("Fail deleteCountent");
	         try {
	            conn.rollback(); // 에러시 롤백해주기.
	         } catch (SQLException e1) {
	            log("rollback");
	         }
	      }finally {
	         try {
	            conn.setAutoCommit(true);
	         } catch (SQLException e) {}
	         memberDAO.close(conn, psmt, rs);
	         log("6/6 Success deleteCountent");
	      }
	      return count>0?true:false;
	}

	
	
	public void	log(String msg) {
		if(isS){
			System.out.println(getClass() + ": " + msg);
		}		
	}	
	public void	log(String msg, Exception e) {
		if(isS){
			System.out.println(e + ": " + getClass() + ": " + msg);
		}		
	}
	
	
}
