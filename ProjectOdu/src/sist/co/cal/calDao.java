package sist.co.cal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconn.MemberDAO;

public class calDao implements IcalDao {
	
	private boolean isS = true;
	
	private static calDao caldao;
	
	public static calDao getInstance(){ 
		// 어디에서 접근할 수 있게 singleton 형식을 만든다. 
		if(caldao == null){
			caldao = new calDao();
		}
		return caldao;
	}
	
	@Override
	public boolean addCalendar(CalendarDTO cal) {
		String sql=" INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE) "
				 + " VALUES(SEQ_CALENDAR.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = MemberDAO.getConnection();
			log("2/6 Success addCalendar");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getId());
			psmt.setString(2, cal.getTitle());
			psmt.setString(3, cal.getContent());
			psmt.setString(4, cal.getRdate());
			log("3/6 Success addCalendar");
			count = psmt.executeUpdate();
			log("4/6 Success addCalendar");
		}catch(SQLException e){
			log("Fail addCalendar");
		}finally{
			MemberDAO.close(conn, psmt, rs);
			log("6/6 Success addCalendar");
		}
		return count > 0 ? true:false;
	}
	
	
	@Override
	public List<CalendarDTO> getCalendarList(String id, String yyyyMM) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE "
					+ " FROM( "
					+ " SELECT ROW_NUMBER() OVER(PARTITION BY SUBSTR(RDATE, 1, 8) "
					+ "	ORDER BY RDATE ASC) RN, "
					+ "	SEQ, ID, TITLE, CONTENT, RDATE, WDATE FROM CALENDAR "
					+ "	WHERE ID = ? AND SUBSTR(RDATE, 1, 6) = ? "
					+ " ) "
					+ " WHERE RN BETWEEN 1 AND 5";	
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CalendarDTO> cdtos = new ArrayList<CalendarDTO>();
		
		try{
			conn = MemberDAO.getConnection();
			log("2/6 Success getCalendarList");
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			psmt.setString(2, yyyyMM.trim());
			log("3/6 Success getCalendarList");
			
			rs=psmt.executeQuery();
			log("4/6 Success getCalendarList");
			
			while(rs.next()){
				CalendarDTO cdto = new CalendarDTO();
				int i = 1;
				cdto.setSeq(rs.getInt(i++));
				cdto.setId(rs.getString(i++));
				cdto.setTitle(rs.getString(i++));
				cdto.setContent(rs.getString(i++));
				cdto.setRdate(rs.getString(i++));
				cdto.setWdate(rs.getString(i++));
				
				cdtos.add(cdto);
			}
			log("5/6 Success getCalendarList");
		}catch(SQLException e){
			log("Fail getCalendarList");
		}finally {
			MemberDAO.close(conn, psmt, rs);
			log("6/6 Success getCalendarList");
		}
		
		return cdtos;
	}

	@Override
	public CalendarDTO caldetail(int seq) {
		String sql = " SELECT ID, TITLE, CONTENT, RDATE FROM CALENDAR "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		CalendarDTO cal = null;
		try{
			conn = MemberDAO.getConnection();
			log("2/6 Success caldetail");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success caldetail");
			
			rs=psmt.executeQuery();
			log("4/6 Success caldetail");
			while(rs.next()){
				int i = 1;
				String id = rs.getString(i++);
				String title = rs.getString(i++);
				String content = rs.getString(i++);
				String rdate = rs.getString(i++);
				cal = new CalendarDTO(id, title, content, rdate);
			}
			log("5/6 Success caldetail");
		}catch(SQLException e){
			log("Fail caldetail");
		}finally {
			log("6/6 Success caldetail");
			MemberDAO.close(conn, psmt, rs);
		}
		return cal;
	}
	
	@Override
	public boolean deleteCalendar(int seq) {
		String sql = " DELETE FROM CALENDAR "
				   + " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = MemberDAO.getConnection();
			conn.setAutoCommit(false);
			log("2/6 Success deleteCalendar");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success deleteCalendar");
			count = psmt.executeUpdate();
			conn.commit(); // DB적용.
			log("4/6 Success deleteCalendar");
		}catch(SQLException e){
			log("Fail deleteCalendar");
			try {
				conn.rollback(); // 에러시 롤백해주기.
			} catch (SQLException e1) {
				log("rollback");
			}
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {}
			MemberDAO.close(conn, psmt, rs);
			log("6/6 Success deleteCalendar");
		}
		return count > 0 ? true: false;
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
