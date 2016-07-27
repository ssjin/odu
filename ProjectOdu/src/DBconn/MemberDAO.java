package DBconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements DaoInterface {
	
	private boolean isS = true;
	private static MemberDAO memberDAO;
	
	private MemberDAO(){ //생성자 부분에서 오라클드라이버를 찾는다.
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static MemberDAO getInstance(){ 
		// 어디에서 접근할 수 있게 singleton 형식을 만든다. 
		if(memberDAO == null){
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public static Connection getConnection() throws SQLException{
		// 본격적인 DB Connection부분.
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user= "hr";
		String pw = "hr";
		
		conn = DriverManager.getConnection(url, user, pw);
		
		return conn;
	}

	public static void close(Connection conn, Statement psmt, ResultSet rs){
		// DB Close부분.
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if(psmt != null){
			try {
				psmt.close();
			} catch (SQLException e) {}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public boolean insertMember(MemberDTO dto) {
		String sql = " insert into MemberDTO (id, name, pw, email, auth)"
				+ "values(?, ?, ?, ?, 3)"; // insert sql문.
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			conn = getConnection();
			
			psmt = conn.prepareStatement(sql);
			int i = 1;
			psmt.setString(i++, dto.getId());
			psmt.setString(i++, dto.getName());
			psmt.setString(i++, dto.getPw());
			psmt.setString(i++, dto.getEmail());
			
			count = psmt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			this.close(conn, psmt, rs);
		}
		return count > 0 ? true:false;
	}

	@Override
	public MemberDTO login(MemberDTO dto) {
		String sql = " SELECT id, name, email, auth FROM MemberDTO"
				+ " WHERE id = ? and pw=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MemberDTO mem = null;
		
		try{
			conn=this.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			
			rs=psmt.executeQuery();
			
			while(rs.next()){
				int i = 1;
				String id = rs.getString(i++);
				String name = rs.getString(i++);
				String email = rs.getString(i++);
				int auth = rs.getInt(i++);
				mem = new MemberDTO(id, name, null, email, auth);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			this.close(conn, psmt, rs);
		}
		return mem;
	}
	@Override
	public boolean writeBBS(BbsDTO bbs) {
		// insert
		String sql = " insert into bbsDTO(seq, id, ref, step,"
					+ "depth, title, content, wdate, parent,del,readcount)"
					+ "values(seq_bbs.NEXTVAL, ?, (SELECT NVL(max(ref),0) + 1 from bbsdto),"
					+ " 0, 0, ?, ?, sysdate, 0, 0, 0)";
		log("1/6 Success BBSwrite");
		//connection

		Connection conn = null;
		PreparedStatement psmt = null;
		//resultset
		ResultSet rs = null;
		log("2/6 Success BBSwrite");
		
		int count = 0;
		
		try{
			conn = getConnection();
			log("3/6 Success BBSwrite");
			
			//prepared
			psmt = conn.prepareStatement(sql);
			int i = 1;
			psmt.setString(i++, bbs.getId());
			psmt.setString(i++, bbs.getTitle());
			psmt.setString(i++, bbs.getContent());
			log("4/6 Success BBSwrite");
			//update
			count = psmt.executeUpdate();
			log("5/6 Success BBSwrite");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			//close
			this.close(conn, psmt, rs);
			log("6/6 Success BBSwrite");
		}

		return count > 0 ? true: false;
	}
	
	@Override
	public List<BbsDTO> getBBSList() {
		// sql
		String sql=" SELECT seq, id, ref, step,"
					+ "depth, title, content, wdate, parent, del, readcount from BBSDTO order by ref DESC, step ASC";
		
		log("1/6 Success getbbsList");
		
		// conn, pre, resultset
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDTO> bbsList = new ArrayList<BbsDTO>();
		log("2/6 Success getbbsList");
		
		try{
			conn = this.getConnection();
			
			psmt = conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			log("3/6 Success getbbsList");
			
			while(rs.next()){
				int i = 1;
				BbsDTO bbs = new BbsDTO();
				
				bbs.setSeq(rs.getInt("seq"));
				bbs.setId(rs.getString("id"));
				bbs.setRef(rs.getInt("ref"));
				bbs.setStep(rs.getInt("step"));
				bbs.setDepth(rs.getInt("depth"));
				bbs.setTitle(rs.getString("title"));
				bbs.setContent(rs.getString("content"));
				bbs.setWdate(rs.getString("wdate"));
				bbs.setParent(rs.getInt("parent"));
				bbs.setDel(rs.getInt("del"));
				bbs.setReadcount(rs.getInt("readcount"));
				bbsList.add(bbs);
			}
			log("4/6 Success getbbsList");
			for(int i = 0; i < bbsList.size(); i ++){
				System.out.println(bbsList.get(i).toString());
			}
			log("5/6 Success getbbsList");
		}catch(SQLException e){
			log("Fail getbbsList");
		}finally {
			this.close(conn, psmt, rs);
			log("6/6 Success getbbsList");
		}
		return bbsList;
	}

	
	@Override
	public BbsDTO getBbsDTO(int seq) {
		String sql = " SELECT seq, id, ref, step, depth, "
					+ " title, content, wdate, parent, del, readcount "
					+ " FROM BBSDTO WHERE seq=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDTO bbs = null;
		try{
			conn = this.getConnection();
			log("2/6 Success getBbsDTO");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success getBbsDTO");
			rs=psmt.executeQuery();
			log("4/6 Success getBbsDTO");
			
			while(rs.next()){
				int i = 1;
				bbs = new BbsDTO(
						rs.getInt(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getInt(i++)
						);
			}
			log("5/6 Success getBbsDTO");
		}catch(SQLException e){
			log("Fail getBbsDTO");
		}finally {
			this.close(conn, psmt, rs);
			log("6/6 Success getBbsDTO");
		}
		return bbs;
	}

	@Override
	public void readCount(int seq) {
		String sql=" UPDATE BBSDTO SET readcount=readcount + 1"
				+ " WHERE seq=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try{
			conn = this.getConnection();
			log("2/6 Success readCount");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success readCount");
			psmt.executeUpdate();
			log("4/6 Success readCount");
			
		}catch(SQLException e){
			log("Fail readCount");
		}finally {
			this.close(conn, psmt, rs);
			log("6/6 Success readCount");
		}
	}

	@Override
	public boolean answer(int seq, BbsDTO bbs) {
		String sql1=" UPDATE BBSDTO SET "
				  + " step=step+1 "
				  + " WHERE ref=(select ref from bbsdto where seq=?) "
				  + " and step > (select step from bbsdto where seq=?) ";
		
		String sql2=" INSERT INTO BBSDTO "
				+ " (seq, id, ref, step, depth, title, content, wdate, parent, del, readcount) "
				+ " values(seq_bbs.NEXTVAL, ?, (SELECT ref from BBSDTO where seq=?), "
				+ " (SELECT step from BBSDTO where seq=?)+1, "
				+ " (SELECT depth from BBSDTO where seq=?)+1, "
				+ " ?, ?, sysdate, "
				+ " ?, 0, 0) ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try{
			conn = getConnection();
			conn.setAutoCommit(false); // auto coummit 사용안함.
			log("2/6 Success answer");
			
			psmt = conn.prepareStatement(sql1);
			int i = 1;
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			log("3/6 Success answer");
			
			count = psmt.executeUpdate();
			//////////////////////update
			
			psmt.clearParameters(); // psmt에 정보가 있기때문에 초기화를 해줘야한다.
			
			psmt = conn.prepareStatement(sql2);
			i = 1;
			psmt.setString(i++, bbs.getId());
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setString(i++, bbs.getTitle());
			psmt.setString(i++, bbs.getContent());
			psmt.setInt(i++, seq);
			
			count = psmt.executeUpdate();
			conn.commit(); // DB적용.
			
			log("4/6 Success answer");
		}catch(SQLException e){
			log("Fail answer");
			try {
				conn.rollback(); // 에러시 롤백해주기.
			} catch (SQLException e1) {
				log("rollback");
			}
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {}
			this.close(conn, psmt, rs);
			log("5/6 Success answer");
		}
		
		return count > 0 ? true: false;
	}

	@Override
	public boolean deleteContent(int seq) {
		String sql=" UPDATE BBSDTO SET del=1 "
				+ " where seq=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = this.getConnection();
			conn.setAutoCommit(false);
			log("2/6 Success deleteCountent");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success deleteCountent");
			count = psmt.executeUpdate();
			log("4/6 Success deleteCountent");
			conn.commit(); // DB적용.
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
			this.close(conn, psmt, rs);
			log("6/6 Success deleteCountent");
		}
		return count > 0 ? true: false;
	}

	@Override
	public boolean modifyContent(int seq, String content) {
		String sql=" UPDATE BBSDTO SET content= "
				+ "\'" + content+ "\'" + " where seq=?";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = MemberDAO.getConnection();
			conn.setAutoCommit(false);
			log("2/6 Success modifyContent");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			log("3/6 Success modifyContent");
			count = psmt.executeUpdate();
			log("4/6 Success modifyContent");
			conn.commit();
		}catch(SQLException e){
			log("Fail modifyContent");
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
			log("6/6 Success modifyContent");
		}
		return count > 0 ? true: false;
	}

	@Override
	public List<BbsDTO> searchBBSList(int search_num, String search_data) {
		String sql = "";
	
		if(search_num == 1){
		//제목으로 찾기
		sql = " SELECT SEQ, TITLE, ID, CONTENT, DEL FROM BBSDTO "
				   + " WHERE TITLE LIKE \'%" + search_data + "%\'";
		}else if(search_num == 2){
		//내용으로 찾기
		sql = " SELECT SEQ, TITLE, ID, CONTENT, DEL FROM BBSDTO "
				   + " WHERE content LIKE \'%" + search_data + "%\'";
		}else if(search_num == 3){
		//작성자로 찾기
		sql = " SELECT SEQ, TITLE, ID, CONTENT, DEL FROM BBSDTO "
				   + " WHERE ID LIKE \'%" + search_data + "%\'";
		}

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDTO> s_bbsList = new ArrayList<BbsDTO>();
		log("2/6 Success searchbbsList");
		
		try{
			conn = MemberDAO.getConnection();
			
			psmt = conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			log("3/6 Success searchbbsList");
			
			while(rs.next()){
				BbsDTO bbs = new BbsDTO();
				
				bbs.setSeq(rs.getInt("seq"));
				bbs.setId(rs.getString("id"));
				bbs.setTitle(rs.getString("title"));
				bbs.setContent(rs.getString("content"));
				bbs.setDel(rs.getInt("DEL"));
				if(bbs.getDel() == 0){
				s_bbsList.add(bbs);
				}
			}
			log("4/6 Success searchbbsList");
			for(int i = 0; i < s_bbsList.size(); i ++){
				System.out.println(s_bbsList.get(i).toString());
			}
			log("5/6 Success searchbbsList");
		}catch(SQLException e){
			log("Fail searchbbsList");
		}finally {
			MemberDAO.close(conn, psmt, rs);
			log("6/6 Success searchbbsList");
		}
		return s_bbsList;
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
