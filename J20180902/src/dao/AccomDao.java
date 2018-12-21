package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.*;
import javax.naming.*;

public class AccomDao {
	private static AccomDao instance;
	private AccomDao() {}
	public static AccomDao getInstance() {
		if (instance == null) {	instance = new AccomDao();		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { System.out.println(e.getMessage());	}
		return conn;
	}
	
	public List<Accom> list(int startRow, int endRow,int accom_no) throws SQLException {
		List<Accom> list = new ArrayList<Accom>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		Statement stmt2= null; ResultSet rs2 = null;
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from accom order by accom_no) a ) "+
					" where rn between ? and ?";
		//String sql2 = "select accom_no,round(avg(accom_rat),2) from accom_com where accom_no="+accom_no+"group by (accom_no)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			stmt2 = conn.createStatement();
			//rs2 = stmt2.executeQuery(sql2);
			while (rs.next()) {
				Accom board = new Accom();
				board.setAccom_no(rs.getInt("accom_no"));
				board.setAccom_name(rs.getString("accom_name"));
				board.setAccom_type(rs.getString("accom_type"));
				board.setAccom_addr(rs.getString("accom_addr"));
				board.setAccom_func(rs.getString("accom_func"));
				board.setAccom_map(rs.getString("accom_map"));
				board.setAccom_cont(rs.getString("accom_cont"));
				/*if(rs2.next()){
					board.setAccom_rat(rs2.getFloat("round(avg(accom_rat),2)"));
					System.out.println("***********************여기들어오는지1");
					System.out.println("rs2.getFloat(accom_rat)->"+rs2.getFloat("round(avg(accom_rat),2)"));
				}*/
				board.setAccom_rat(rs.getFloat("accom_rat"));
				board.setInout(rs.getString("inout"));
				board.setArea(rs.getString("area"));
				board.setAccom_photo(rs.getString("accom_photo"));
				list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public List<Accom> list2(int startRow, int endRow) throws SQLException {
		List<Accom> list = new ArrayList<Accom>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from accom order by accom_no) a ) "+
					" where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Accom board = new Accom();
				board.setAccom_no(rs.getInt("accom_no"));
				board.setAccom_name(rs.getString("accom_name"));
				board.setAccom_type(rs.getString("accom_type"));
				board.setAccom_addr(rs.getString("accom_addr"));
				board.setAccom_func(rs.getString("accom_func"));
				board.setAccom_map(rs.getString("accom_map"));
				board.setAccom_cont(rs.getString("accom_cont"));

				board.setAccom_rat(rs.getFloat("avg(accom_rat)"));
				
				board.setInout(rs.getString("inout"));
				board.setArea(rs.getString("area"));
				board.setAccom_photo(rs.getString("accom_photo"));
				list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public Accom select_accom1(int accom_no) throws SQLException {
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		Statement stmt2= null; ResultSet rs2 = null;
		String sql = "select * from accom where accom_no="+accom_no;
		//String sql2 = "select accom_no,round(avg(accom_rat),2) from accom_com where accom_no="+accom_no+"group by (accom_no)";
		Accom board = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		
			//stmt2 = conn.createStatement();
			//rs2 = stmt2.executeQuery(sql2);
			if (rs.next()) {				
				board.setAccom_no(rs.getInt("accom_no"));
				board.setAccom_name(rs.getString("accom_name"));
				board.setAccom_type(rs.getString("accom_type"));
				board.setAccom_addr(rs.getString("accom_addr"));
				board.setAccom_func(rs.getString("accom_func"));
				board.setAccom_map(rs.getString("accom_map"));
				board.setAccom_cont(rs.getString("accom_cont"));
				/*if(rs2.next()){
					board.setAccom_rat(rs2.getFloat("round(avg(accom_rat),2)"));
					System.out.println("***********************여기들어오는지2");
					System.out.println("rs2.getFloat(accom_rat)->"+rs2.getFloat("round(avg(accom_rat),2)"));
				}*/
				board.setAccom_rat(rs.getFloat("accom_rat"));
				board.setInout(rs.getString("inout"));
				board.setArea(rs.getString("area"));
				board.setAccom_photo(rs.getString("accom_photo"));
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	} /* accom 테이블 끌어오기 */
	
	public Accom select_accom2(int accom_no) throws SQLException {
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "SELECT accom.accom_name, accom_info.*, aphoto.* " + 
				"FROM ACCOM, APHOTO, ACCOM_INFO " + 
				"WHERE aphoto.PHO_NO = accom_info.NUM " + 
				"AND accom.ACCOM_NO = aphoto.ACCOM_NO " + 
				"and accom.accom_no = accom_info.accom_no " +
				"AND accom.ACCOM_NO = " + accom_no +
				" and accom_info.num=1";
		Accom board = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			System.out.println("sql->"+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				board.setAccom_no(rs.getInt("accom_no"));
				board.setRoom_no(rs.getString("room_no"));
				board.setNum_people(rs.getInt("num_people"));
				board.setIn_date(rs.getString("in_date"));
				board.setOut_date(rs.getString("out_date"));
				board.setPrice(rs.getInt("price"));
				board.setRes_stat(rs.getString("res_stat"));
				board.setNum(rs.getInt("num"));
				
				board.setPho_no(rs.getInt("pho_no"));
				board.setPhoto(rs.getString("photo"));
			}
		} catch(Exception e) {	System.out.println("Exception->"+e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	} /* accom_info랑 photo 끌어오기 */
	
	public Accom select_accom3(int accom_no) throws SQLException {
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "SELECT accom.accom_name, accom_info.*, aphoto.* " + 
				"FROM ACCOM, APHOTO, ACCOM_INFO " + 
				"WHERE aphoto.PHO_NO = accom_info.NUM " + 
				"AND accom.ACCOM_NO = aphoto.ACCOM_NO " + 
				"and accom.accom_no = accom_info.accom_no " +
				"AND accom.ACCOM_NO = " + accom_no +
				" and accom_info.num=2";
		Accom board = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			System.out.println("sql->"+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				board.setAccom_no(rs.getInt("accom_no"));
				board.setRoom_no(rs.getString("room_no"));
				board.setNum_people(rs.getInt("num_people"));
				board.setIn_date(rs.getString("in_date"));
				board.setOut_date(rs.getString("out_date"));
				board.setPrice(rs.getInt("price"));
				board.setRes_stat(rs.getString("res_stat"));
				board.setNum(rs.getInt("num"));
				
				board.setPho_no(rs.getInt("pho_no"));
				board.setPhoto(rs.getString("photo"));
			}
		} catch(Exception e) {	System.out.println("Exception->"+e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	} /* accom_info랑 photo 끌어오기 */
	
	public Accom select_accom4(int accom_no) throws SQLException {
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "SELECT accom.accom_name, accom_info.*, aphoto.* " + 
				"FROM ACCOM, APHOTO, ACCOM_INFO " + 
				"WHERE aphoto.PHO_NO = accom_info.NUM " + 
				"AND accom.ACCOM_NO = aphoto.ACCOM_NO " + 
				"and accom.accom_no = accom_info.accom_no " +
				"AND accom.ACCOM_NO = " + accom_no +
				" and accom_info.num=3";
		Accom board = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			System.out.println("sql->"+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				board.setAccom_no(rs.getInt("accom_no"));
				board.setRoom_no(rs.getString("room_no"));
				board.setNum_people(rs.getInt("num_people"));
				board.setIn_date(rs.getString("in_date"));
				board.setOut_date(rs.getString("out_date"));
				board.setPrice(rs.getInt("price"));
				board.setRes_stat(rs.getString("res_stat"));
				board.setNum(rs.getInt("num"));
				
				board.setPho_no(rs.getInt("pho_no"));
				board.setPhoto(rs.getString("photo"));
			}
		} catch(Exception e) {	System.out.println("Exception->"+e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	} /* accom_info랑 photo 끌어오기 */

	public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from board";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot = rs.getInt(1);
			System.out.println("9");
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	public int update(Accom_com accom_com) throws SQLException{
		int result = 0;
		Connection conn = null;	
		
		PreparedStatement pstmt= null;

		ResultSet rs = null; 

		float tmp = 0;
		
		String sql = "insert into accom_com values(?,?,?,?,?,?)";
		
		String sql1= "select sum(accom_rat)/count(*) from accom_com where accom_no=?";
		
		String sql2= "update accom set accom_rat = ? where accom_no=?";
		
		try {
			
			System.out.println("accom no                 " + accom_com.getAccom_no());
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_com.getAccom_no());
			pstmt.setString(2, accom_com.getUser_id());
			pstmt.setString(3, accom_com.getReview_cont());
			pstmt.setFloat(4, accom_com.getAccom_rat());
			pstmt.setInt(5, accom_com.getSaving_pnt());
			pstmt.setInt(6, accom_com.getCom_no());
			result = pstmt.executeUpdate();
			pstmt.close();

			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, accom_com.getAccom_no());
			rs = pstmt.executeQuery();
			
			rs.next();
			
			tmp = rs.getFloat(1);

			pstmt.close();
			rs.close();
			
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setFloat(1, tmp);
			pstmt.setInt(2, accom_com.getAccom_no());
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
}