package pro_together;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBconn.MemberDAO;

public class togerherDAO implements TOGETHER_INTERFACE {

private boolean isS = true;
	
	private static togerherDAO togerDAO;
	
	public static togerherDAO getInstance(){ 
		// 어디에서 접근할 수 있게 singleton 형식을 만든다. 
		if(togerDAO == null){
			togerDAO = new togerherDAO();
		}
		return togerDAO;
	}
	@Override
	public boolean writeTogether(togetherDTO dto) {
		String sql = " INSERT INTO TOGETHER(SEQ, BBS_NUM, ID, CATE, TITLE, "
				   + " CONTENT, F_NAME, M_DATE,WDATE, DEAD, T_NUM, PARENT, READCOUNT, DEL) "
				   + " VALUES(SEQ_TOGETHER.NEXTVAL, 3, ?, ?, ?, "
				   + " ?, ?, ?, SYSDATE, 0, ?, 0, 0, 0) ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = MemberDAO.getConnection();
			log("2/6 Success writeTogether");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getCate());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getF_name());
			psmt.setString(6, dto.getWdate());
			psmt.setInt(7, dto.getT_num());
			log("3/6 Success writeTogether");
			System.out.println(sql);
			count = psmt.executeUpdate();
			log("4/6 Success writeTogether");
		}catch(SQLException e){
			log("Fail writeTogether");
		}finally{
			MemberDAO.close(conn, psmt, rs);
			log("6/6 Success writeTogether");
		}
				
		return count > 0 ? true:false;
	}
	
	public void log(String msg){
		if(isS){
			System.out.println(getClass() + ": " + msg);
		}
	}

	public void log(String msg, Exception e){
		if(isS){
			System.out.println(e + ": " + getClass() + ": " + msg);
		}
	}
}
