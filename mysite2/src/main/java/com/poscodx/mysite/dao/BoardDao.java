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
	        		  "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
	        		  + "values(null, ?, ?, ?, now(), ?, ?, ?, ?)");
	    	PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {

	    	pstmt.setString(1, vo.getTitle());
	    	pstmt.setString(2, vo.getContents());
	    	pstmt.setInt(3, vo.getHit());
	    	pstmt.setLong(4, vo.getGroupNo());
	    	pstmt.setLong(5, vo.getOrderNo());
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
		    		"select b.no, b.title, b.contents, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s'), b.user_no, b.g_no, b.o_no, b.depth "
		    		+ "from user a, board b " 
		    		+ "where a.no=b.user_no "
		    		+ "order by b.g_no desc, b.o_no asc");
		    ResultSet rs = pstmt.executeQuery();) {

		    while (rs.next()) {
		       Long no = rs.getLong(1);
		       String title = rs.getString(2);
		       String contents = rs.getString(3);
		       String userName = rs.getString(4);
		       Integer hit = rs.getInt(5);
		       String regDate = rs.getString(6);
		       Long userNo = rs.getLong(7);
		       Long groupNo = rs.getLong(8);
		       Long orderNo = rs.getLong(9);
		       Integer depth = rs.getInt(10);

		       BoardVo vo = new BoardVo();
		       vo.setNo(no);
		       vo.setTitle(title);
		       vo.setUserName(userName);
		       vo.setContents(contents);
		       vo.setHit(hit);
		       vo.setRegDate(regDate);
		       vo.setUserNo(userNo);
		       vo.setGroupNo(groupNo);
		       vo.setOrderNo(orderNo);
		       vo.setDepth(depth);
		       
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
	    		Integer hit = rs.getInt(5);
	    		String regDate = rs.getString(6);
	    		Long groupNo = rs.getLong(7);
	    		Long orderNo = rs.getLong(8);
	    		Integer depth = rs.getInt(9);
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

	public int deleteByNo(Long boardNo) {
	    int result = 0;

	    try (Connection conn = getConnection();
	        PreparedStatement pstmt =
	            conn.prepareStatement("delete from board where no = ?");) {
	      pstmt.setLong(1, boardNo);
	      result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	    	System.out.println("Error:" + e);
	    }
	    return result;
	  }

	public int update(BoardVo vo) {
		int result = 0;
	    try (Connection conn = getConnection();
	        PreparedStatement pstmt =
	        	conn.prepareStatement("update board set title = ?, contents = ? where no = ? and user_no = ?");) {
	    	
	    	pstmt.setString(1, vo.getTitle());
	    	pstmt.setString(2, vo.getContents());
	    	pstmt.setLong(3, vo.getNo());
	    	pstmt.setLong(4, vo.getUserNo());
	    	
	    	result = pstmt.executeUpdate();
	    	
	    } catch (SQLException e) {
	       System.out.println("error:" + e);
	    }
	    return result;
	}

	public int insertReply(BoardVo boardVo, BoardVo parentVo) {
		int result = 0;
	    try (Connection conn = getConnection();
		    PreparedStatement pstmt1 =
		    	conn.prepareStatement("update board set o_no = o_no + 1 where g_no = ? and o_no >= ?");
	    	PreparedStatement pstmt2 = 
	    		conn.prepareStatement( "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
	        		  + "values(null, ?, ?, ?, now(), ?, ?, ?, ?)");) {
		    	
	    	pstmt1.setLong(1, parentVo.getGroupNo());
	    	pstmt1.setLong(2, boardVo.getOrderNo());
	    	result = pstmt1.executeUpdate();
	    	
	    	pstmt2.setString(1, boardVo.getTitle());
	    	pstmt2.setString(2, boardVo.getContents());
	    	pstmt2.setInt(3, boardVo.getHit());
	    	pstmt2.setLong(4, boardVo.getGroupNo());
	    	pstmt2.setLong(5, boardVo.getOrderNo());
	    	pstmt2.setInt(6, boardVo.getDepth());
	    	pstmt2.setLong(7, boardVo.getUserNo());
	    	result += pstmt2.executeUpdate();
	    	
		    } catch (SQLException e) {
		       System.out.println("error:" + e);
		    }
		    return result;
	}

	public Long getNextGroupNo() {
		Long updateGroupNo = 1L;
	    try (Connection conn = getConnection();
		     PreparedStatement pstmt =
		        conn.prepareStatement("select ifnull(max(g_no), 0)+1 from board");) {
		    	
	    	 ResultSet rs = pstmt.executeQuery();
	    	 
	    	 if(rs.next()) {
	    		 updateGroupNo = rs.getLong(1);
	    	 }

		} catch (SQLException e) {
			 System.out.println("error:" + e);
		}
	    return updateGroupNo;
	}

	public int updateHit(Long no) {
		int result = 0;
	    try (Connection conn = getConnection();
	        PreparedStatement pstmt =
	        	conn.prepareStatement("update board set hit=hit+1 where no = ?");) {
	    	
	    	pstmt.setLong(1, no);
	    	result = pstmt.executeUpdate();
	    	
	    } catch (SQLException e) {
	       System.out.println("error:" + e);
	    }
	    return result;
	}
	
	public int getTotalRowCnt() {
		int count = 0;
	    try (Connection conn = getConnection();
	        PreparedStatement pstmt =
	        	conn.prepareStatement("select count(*) from board");) {
	    	 
	    	ResultSet rs = pstmt.executeQuery();
	    	 
	    	 if(rs.next()) {
	    		 count = rs.getInt(1);
	    	 }
	    	
	    } catch (SQLException e) {
	       System.out.println("error:" + e);
	    }
	    return count;
	}
	
	public List<BoardVo> findByPage(int page, int displayRow) {
	    List<BoardVo> result = new ArrayList<>();
	    int startRow = (page - 1) * displayRow;

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(
	                 "select b.no, b.title, b.contents, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s'), b.user_no, b.g_no, b.o_no, b.depth "
	                 + "from user a, board b "
	                 + "where a.no = b.user_no "
	                 + "order by b.g_no desc, b.o_no asc "
	                 + "limit ?, ?")) {
	    	
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, displayRow);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Long no = rs.getLong(1);
	                String title = rs.getString(2);
	                String contents = rs.getString(3);
	                String userName = rs.getString(4);
	                Integer hit = rs.getInt(5);
	                String regDate = rs.getString(6);
	                Long userNo = rs.getLong(7);
	                Long groupNo = rs.getLong(8);
	                Long orderNo = rs.getLong(9);
	                Integer depth = rs.getInt(10);

	                BoardVo vo = new BoardVo();
	                vo.setNo(no);
	                vo.setTitle(title);
	                vo.setUserName(userName);
	                vo.setContents(contents);
	                vo.setHit(hit);
	                vo.setRegDate(regDate);
	                vo.setUserNo(userNo);
	                vo.setGroupNo(groupNo);
	                vo.setOrderNo(orderNo);
	                vo.setDepth(depth);

	                result.add(vo);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error:" + e);
	    }
	    return result;
	}
}

