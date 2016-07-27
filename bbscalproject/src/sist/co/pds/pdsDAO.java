package sist.co.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sist.co.cal.calDAO;
import sist.co.member.memberDAO;

public class pdsDAO implements IpdsDAO {

	boolean isS=true;
	
	private static pdsDAO pdsdao;
	
	
	public static pdsDAO getInstance(){
		if(pdsdao == null){
			pdsdao = new pdsDAO();
		}
		return pdsdao;
	}
	

	@Override
	public List<pdsDTO> getPDSList() {
		
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGIDATE, DEL "
				+ " FROM PDS "
				+ " ORDER BY SEQ DESC ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		List<pdsDTO> list = new ArrayList<pdsDTO>(); 
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success getPDSList");
			
			psmt=conn.prepareStatement(sql);
			log("3/6 Success getPDSList");
			
			rs=psmt.executeQuery();
			log("4/6 Success getPDSList");
			
			while(rs.next()){
				int i = 1;
				pdsDTO pd = new pdsDTO(
							rs.getInt(i++),		// seq
							rs.getString(i++),	// id
							rs.getString(i++), 	// title
							rs.getString(i++),	// content
							rs.getString(i++),	// filename
							rs.getInt(i++),		// readcount
							rs.getInt(i++),		// downcount
							rs.getString(i++), 	// regidate
							rs.getInt(i++)		// del
						);
				list.add(pd);
			}
			log("5/6 Success getPDSList");
			
		}catch(SQLException e){
			log("Fail getPDSList", e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getPDSList");
		}
		return list;
	}



	@Override
	public boolean uploadPDS(pdsDTO pds) {
		
		String sql=" INSERT INTO PDS( "
				+ " SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGIDATE, DEL) "
				+ " VALUES(SEQ_PDS.NEXTVAL, ?, ?, ?, ?, 0, 0, SYSDATE, 0) ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count=0;
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success writePDS");
			
			psmt=conn.prepareStatement(sql);
			log("3/6 Success writePDS");
		
			int i=1;
			psmt.setString(i++, pds.getId());
			psmt.setString(i++, pds.getTitle());
			psmt.setString(i++, pds.getContent());
			psmt.setString(i++, pds.getFilename());
			log("4/6 Success writePDS");
			count=psmt.executeUpdate();
			log("5/6 Success writePDS");
			
			
		}catch(SQLException e){
			log("Fail writePDS", e);
		}finally{
			memberDAO.close(conn, psmt, null);
			log("6/6 Success writePDS");
			
		}
		
		return count>0?true:false;
	}

	@Override
	public boolean downloadCount(int pdsid) {
		
		String sql = " UPDATE PDS SET "
				+ " DOWNCOUNT=DOWNCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count=0;
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success downloadCount");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, pdsid);
			log("3/6 Success downloadCount");
			
			count = psmt.executeUpdate();
			log("4/6 Success downloadCount");
			
			
			
		}catch(SQLException e){
			log("Fail downloadCount", e);
				
		}finally{
			memberDAO.close(conn, psmt, null);
			log("5/6 Success downloadCount");
		}
		
		
		return count>0?true:false;
	}
	


	@Override
	public boolean writePDS(pdsDTO pds) {
		
/*		String sql=" INSERT INTO PDS "
				+ " (SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGIDATE) "
				+ " VALUES(SEQ_PDS.NEXTVAL, ?, ?, ?, ?, 0, 0, SYSDATE) ";*/
		
		
		
		return false;
	}




	@Override
	public boolean deletePDS(int seq) {
		
	      String sql=" UPDATE PDS SET del=1 "
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


	@Override
	public pdsDTO getPDS(int seq) {
		
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGIDATE, DEL "
				+ " FROM PDS "
				+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		pdsDTO pds=null;
		
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success getPDS");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success getPDS");
			
			rs=psmt.executeQuery();
			log("4/6 Success getPDS");
			
			
			while(rs.next()){
				int i = 1;
				pds = new pdsDTO(
						rs.getInt(i++),		//seq
						rs.getString(i++),	//id
						rs.getString(i++),	//title
						rs.getString(i++),	//content
						rs.getString(i++),	//filename
						rs.getInt(i++),		//readcount	
						rs.getInt(i++),		//downcount
						rs.getString(i++),	//regidate
						rs.getInt(i++)		//del
						);
				
			}
			log("5/6 Success getPDS");
			
			
		}catch(SQLException e){
			log("Fail getPDS", e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success getPDS");
		}
		return pds;
	}
	
	@Override
	public void readCount(int seq) {
		
		String sql=" UPDATE PDS SET "
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
