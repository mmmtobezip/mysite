package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.mysite.vo.UserVo;

public class BoardDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

		    String url = "jdbc:mariadb://192.168.64.2:3306/webdb?charset=utf8";
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
	
	public int insert(BoardVo vo) {
		int result = 0;
	    try (Connection conn = getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(
	        		  "insert into board(title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
	        		  + "values(?, ?, ?, now(), ?, ?, ?, ?)");
	    	PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {

	    	pstmt.setString(1, vo.getTitle());
	    	pstmt.setString(2,  vo.getContents());
	    	pstmt.setInt(3, vo.getHit());
	    	pstmt.setInt(4, vo.getGroupNo());
	    	pstmt.setInt(5, vo.getOrderNo());
	    	pstmt.setInt(6, vo.getDepth());
	    	pstmt.setLong(7, vo.getUserNo());
	    	  
	        result = pstmt.executeUpdate();
	        
	        ResultSet rs = pstmt2.executeQuery();
	        vo.setNo(rs.next() ? rs.getLong(1) : null);
	        rs.close();
	    } catch (SQLException e) {
	    	System.out.println("error:" + e);
	    }
	    return result;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(
		    		"select b.no, b.title, b.contents, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') "
		    		+ "from user a, board b " 
		    		+ "where a.no = b.user_no "
		    		+ "order by g_no desc, o_no asc");
		    ResultSet rs = pstmt.executeQuery();) {

		    while (rs.next()) {
		       Long no = rs.getLong(1);
		       String title = rs.getString(2);
		       String contents = rs.getString(3);
		       String userName = rs.getString(4);
		       Integer hit = rs.getInt(5);
		       String regDate = rs.getString(6);

		       BoardVo vo = new BoardVo();
		       vo.setNo(no);
		       vo.setTitle(title);
		       vo.setUserName(userName);
		       vo.setContents(contents);
		       vo.setHit(hit);
		       vo.setRegDate(regDate);
		       
		       result.add(vo);
		      }
		    rs.close();
		    } catch (SQLException e) {
		      System.out.println("Error:" + e);
		    }
		    return result;
	}

	public BoardVo findByNo(Long boardNo) {
		BoardVo result = null;
		
	    try (Connection conn = getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(
			    		"select b.no, b.title, b.contents, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s'), b.g_no, b.o_no, b.depth, b.user_no "
			    		+ "from user a, board b " 
			    		+ "where a.no = b.user_no "
			    		+ "and b.no = ?");) {
	    	pstmt.setLong(1, boardNo);
	        
	    	ResultSet rs = pstmt.executeQuery();
	    	if(rs.next()) {
	    		Long no = rs.getLong(1);
	    		String title = rs.getString(2);
	    		String contents = rs.getString(3);
	    		String userName = rs.getString(4);
	    		int hit = rs.getInt(5);
	    		String regDate = rs.getString(6);
	    		int groupNo = rs.getInt(7);
	    		int orderNo = rs.getInt(8);
	    		int depth = rs.getInt(9);
	    		Long userNo = rs.getLong(10);
	    		
	    		BoardVo boardVo = new BoardVo();
	    		boardVo.setNo(no);
	    		boardVo.setTitle(title);
	    		boardVo.setContents(contents);
	    		boardVo.setUserName(userName);
	    		boardVo.setHit(hit);
	    		boardVo.setRegDate(regDate);
	    		boardVo.setGroupNo(groupNo);
	    		boardVo.setOrderNo(orderNo);
	    		boardVo.setDepth(depth);
	    		boardVo.setUserNo(userNo);
	    		
	    		result = boardVo;
	    		
	    	}
	    	rs.close();
	    } catch (SQLException e) {
	    	System.out.println("error:" + e);
	    }
	    return result;
	    }
	}

