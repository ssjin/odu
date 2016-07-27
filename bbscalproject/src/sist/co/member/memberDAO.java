package sist.co.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class memberDAO implements ImemberDAO {
	
	private boolean isS=true;
	private static memberDAO memDAO;
	
	
	private memberDAO() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			log("1/6 Success");
		}catch(ClassNotFoundException ex){
			log("1/6 Fail", ex);
			System.out.println(ex.getMessage());
		}
	}
	
	public static memberDAO getInstance() {
		if(memDAO == null){
			memDAO = new memberDAO();
		}
		return memDAO;
	}
	
	public static Connection getConnection()throws SQLException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		String user = "hr";
		String passwd = "hr";
		
		conn=DriverManager.getConnection(url, user, passwd);
		return conn;
	}
	
	public static void close(Connection conn, Statement psmt, ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){}
		}
		if(psmt != null){
			try{
				psmt.close();
			}catch(SQLException e){}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){}
		}
	}
	
	@Override
	public boolean addMember(memberDTO dto) {
		
		
		String sql = " INSERT INTO MEMBER "
				+ " (ID, PWD, NAME, EMAIL, AUTH) "
				+ " VALUES(?, ?, ?, ?, 3) ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		int count=0;
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success addMember");
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			log("3/6 Success addMember");
			
			count=psmt.executeUpdate();
			log("4/6 Success addMember");
			
		}catch(SQLException e){
			log("Fail addMember", e);
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("5/6 Success addMember");
			
		}
		
		return count>0?true:false;
		
	}
	
	

	@Override
	public memberDTO login(memberDTO dto) {
		
		String sql=" SELECT ID, NAME, EMAIL, AUTH FROM MEMBER"
				+ " WHERE ID=? AND PWD=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		memberDTO mem=null;
		
		
		try{
			conn=memberDAO.getConnection();
			log("2/6 Success login");
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			log("3/6 Success login");
			
			rs=psmt.executeQuery();
			log("4/6 Success login");
			while(rs.next()){
				String id=rs.getString(1);
				String name=rs.getString(2);
				String email=rs.getString(3);
				int auth=rs.getInt(4);
				mem=new memberDTO(id, null, name, email, auth);
			}
			log("5/6 Success login");
			
			
		}catch(SQLException e){
			log("Fail login", e);
			
		}finally{
			memberDAO.close(conn, psmt, rs);
			log("6/6 Success login");	
		}
		
		return mem;
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
