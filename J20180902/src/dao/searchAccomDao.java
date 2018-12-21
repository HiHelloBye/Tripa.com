package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class searchAccomDao {
	private static searchAccomDao instance;

	private searchAccomDao() {
	}

	public static searchAccomDao getInstance() {
		if (instance == null) {
			instance = new searchAccomDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int getTotalCnt(String keywordAccom) throws SQLException {
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs    = null;
		PreparedStatement pstmt = null;
		int tot = 0;
		String sql = "select count(*) from Accom where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordAccom);
			pstmt.setString(2, keywordAccom);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return tot;
	}

	
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from Accom";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}

	public List<Accom> listAccom(String keywordAccom, int startRow, int totCntAccom, String ckeckin,
			String ckeckout, String room) throws SQLException{
		List<Accom> list 		= new ArrayList<Accom>();		
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
	
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 			= null;
		
		System.out.println("listAccom Start...");
		//String sql1 = "select * from accom where inout in (select bcd from common where mnm='일본') and area in (select mcd from common where mnm='일본')";
		
		String sql2 = "SELECT * FROM (select rownum rn ,a.*, AP.PHOTO from  (select * from accom where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?)) a ,aphoto  ap where a.accom_no = ap.accom_no and ap.pho_no = (SELECT MAX(PHO_NO) FROM aphoto  WHERE accom_no = a.accom_no) ) aa where rn between ? and ? and accom_no in (select accom_no from reservation where (? not between in_date and out_date) or (? not between in_date and out_date) or accom_no in (select accom_no from reservation where room_no != ?)) or accom_no not in (select accom_no from reservation) and rn between ? and ?";
		try {
			
			conn = getConnection();		
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, keywordAccom);
			pstmt2.setString(2, keywordAccom);
			pstmt2.setInt(3, startRow);
			pstmt2.setInt(4, totCntAccom);
			pstmt2.setString(5, ckeckin);
			pstmt2.setString(6, ckeckout);
			pstmt2.setString(7, room);
			pstmt2.setInt(8, startRow);
			pstmt2.setInt(9, totCntAccom);
			
			rs2 = pstmt2.executeQuery();
			
			while (rs2.next()) {				
				Accom accom = new Accom();
				accom.setAccom_no(rs2.getInt("accom_no"));
				accom.setAccom_name(rs2.getString("accom_name"));
				accom.setAccom_func(rs2.getString("accom_func"));	
				accom.setAccom_rat(rs2.getInt("accom_rat"));	
				accom.setPhoto(rs2.getString("photo"));	
				list.add(accom);		
				System.out.println("**************accom_no:" + rs2.getInt("accom_no"));
				System.out.println("Accom_name->"+rs2.getString("accom_name"));
			}
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs2 	  !=null)  rs2.close();
			if (pstmt2 != null) pstmt2.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

	public List<Accom> listAccomLowPrice(String keywordAccom,int startRow, int totCntAccom,  String ckeckin,
			String ckeckout, String room) throws SQLException{
		List<Accom> list 		= new ArrayList<Accom>();		
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql				= null;
		System.out.println("listAccom Start...");

		sql = "select * from accom_info acif,(select rownum rn ,a.*, AP.PHOTO from  (select * from accom) a ,aphoto  ap where a.accom_no = ap.accom_no and ap.pho_no = (SELECT MAX(PHO_NO)  FROM aphoto WHERE accom_no = a.accom_no) and inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?)) aa where acif.accom_no = aa.accom_no and rn between ? and ? and aa.accom_no in (select accom_no from reservation where (? not between in_date and out_date) or (? not between in_date and out_date)) and room_no =? and aa.accom_no in (select accom_no from reservation where room_no != ?) order by price";		
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordAccom);
			pstmt.setString(2, keywordAccom);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, totCntAccom);
			pstmt.setString(5, ckeckin);
			pstmt.setString(6, ckeckout);
			pstmt.setString(7, room);
			pstmt.setString(8, room);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				System.out.println("***************");
				System.out.println(rs.getInt("accom_no"));
				System.out.println(rs.getString("accom_name"));
				System.out.println(rs.getString("accom_func"));
				System.out.println(rs.getInt("accom_rat"));
				System.out.println(rs.getString("photo"));
				
				Accom accom = new Accom();
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_func(rs.getString("accom_func"));	
				accom.setAccom_rat(rs.getInt("accom_rat"));	
				accom.setPhoto(rs.getString("photo"));	

				list.add(accom);		
			}
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

	public List<Accom> listAccomRat(String keywordAccom,int start, int end, String ckeckin,
			String ckeckout, String room) throws SQLException{
		
		List<Accom> list 		= new ArrayList<Accom>();		
		
		Connection conn			= null; 
		
		PreparedStatement pstmt = null;
		
		ResultSet rs 			= null;

		System.out.println("listAccom Start...");

//		sql = "SELECT * FROM (select rownum rn ,a.*, AP.PHOTO from  (select * from accom) a ,"
//				+ "aphoto  ap where a.accom_no = ap.accom_no and ap.pho_no = (SELECT MAX(PHO_NO) "
//				+ "FROM aphoto  WHERE accom_no = a.accom_no) ) aa	where rn between ? and ? and inout in (select replace(bcd,'_') from common where mnm=?) "
//				+ "and area  in (select mcd from common where mnm=?) and accom_no in (select accom_no from reservation "
//				+ "where (? not between in_date and out_date) or (? not between in_date and out_date) "
//				+ "or accom_no in (select accom_no from reservation where room_no != ?))"
//				+ "order by accom_rat desc";		
		
		String sql = "select *" 
				+ "from accom_info acif, (select rownum rn ,a.*, AP.PHOTO  "
				+ "               from  (select * from accom order by accom_rat desc) a ,aphoto ap "
				+ "               where a.accom_no = ap.accom_no and ap.pho_no = ( SELECT MAX(PHO_NO) "
                + "                                                        FROM aphoto "
				+ "                                                                WHERE accom_no = a.accom_no) and inout in ( select replace(bcd,'_')"
				+ "                                                                                                            from common "
				+ "                                                                                                            where mnm=?) "
                + "                                                                                     and area in ( select mcd "
				+ "                                                                                                           from common "
				+ "                                                                                                           where mnm=?) )  aa "                                                               
				+ "where acif.accom_no = aa.accom_no and rn between ? and ? and aa.accom_no in ( select accom_no "
				+ "                                                                              from reservation "
				+ "				                                                                      where (? not between in_date and out_date) or (? not between in_date and out_date)) and room_no =? and aa.accom_no in ( select accom_no from reservation where room_no != ?)  order by accom_rat desc" ;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordAccom);
			pstmt.setString(2, keywordAccom);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			pstmt.setString(5, ckeckin);
			pstmt.setString(6, ckeckout);
			pstmt.setString(7, room);
			pstmt.setString(8, room);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {				
				Accom accom = new Accom();
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_func(rs.getString("accom_func"));	
				accom.setAccom_rat(rs.getInt("accom_rat"));	
				accom.setPhoto(rs.getString("photo"));	
				list.add(accom);		
			}
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

	public List<Accom> listAccomComm(String keywordAccom,int startRow, int totCntAccom, String ckeckin,
			String ckeckout, String room) throws SQLException{
		List<Accom> list 		= new ArrayList<Accom>();		
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 			= null;
		String sql				= null;
		String sql2				= null;

		
		ArrayList<Integer> tmp1 = new ArrayList<Integer>();
		ArrayList<Integer> tmp2 = new ArrayList<Integer>();
		ArrayList<String> tmp3 = new ArrayList<String>();
		System.out.println("listAccomComm Start...");

		sql = "select aa.accom_no,aa.accom_name, count(review_cont) review_cnt from accom_com acccom,(select rownum rn ,a.* from  (select * from accom) a where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) ) aa where acccom.accom_no(+) = aa.accom_no and rn between ? and ? and aa.accom_no in (select accom_no from reservation  where (? not between in_date and out_date) or (? not between in_date and out_date)) and aa.accom_no in (select accom_no from reservation where room_no != ?) group by aa.accom_no,aa.accom_name order by count(review_cont) desc";	
		sql2 = "select a.*,AP.PHOTO from  (select * from accom) a ,aphoto  ap where a.accom_no = ap.accom_no and ap.pho_no = (SELECT MAX(PHO_NO) FROM aphoto WHERE accom_no = a.accom_no) and inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) and a.accom_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordAccom);
			pstmt.setString(2, keywordAccom);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, totCntAccom);
			pstmt.setString(5, ckeckin);
			pstmt.setString(6, ckeckout);
			pstmt.setString(7, room);
			rs = pstmt.executeQuery();
			
			int i = 0;

			
			while(rs.next()){
				tmp1.add(rs.getInt("accom_no"));
				tmp3.add(rs.getString("accom_name"));
				tmp2.add(rs.getInt("review_cnt"));
				
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, keywordAccom);
				pstmt2.setString(2, keywordAccom);
				pstmt2.setInt(3, rs.getInt("accom_no"));
				rs2 = pstmt2.executeQuery();
			


				while (rs2.next()) {
				
					System.out.println(tmp1.get(i) + "        " + tmp3.get(i));
					System.out.println(rs2.getString("accom_func") + "        " + rs2.getInt("accom_rat"));
					System.out.println(rs2.getString("photo") + "        " + tmp2.get(i));

					Accom accom = new Accom();

					accom.setAccom_no(tmp1.get(i));
					accom.setAccom_name(tmp3.get(i));
					accom.setAccom_func(rs2.getString("accom_func"));	
					accom.setAccom_rat(rs2.getInt("accom_rat"));	
					accom.setPhoto(rs2.getString("photo"));	
					accom.setReview_cnt(tmp2.get(i));
					list.add(accom);
					
					i++;
				}
			
			
			}
			
			


			i = 0;
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (rs2 	  !=null)  rs2.close();
			if (pstmt2 != null) pstmt2.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

	public List<String> getInoutArea(String keywordAccom)throws SQLException {
		List<String> list 		= new ArrayList<String>();
		String[] Chungcheong = {"천안","충청도","충청"};
		String[] japan = {"오사카","도쿄"};
		String resultArea=null;
		String resultInout=null;
		try{
			for(int i=0;i<15;i++){
				if((Chungcheong[i]).contains(keywordAccom)||(japan[i]).contains(keywordAccom)){
					resultInout="K";
					resultArea="01";
					list.add(resultInout);
					list.add(resultArea);
				}
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return list;
	}
}
