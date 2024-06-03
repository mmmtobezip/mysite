package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import com.poscodx.mysite.vo.UserVo;

@Repository
public class UserRepository {
  private Connection getConnection() throws SQLException {
    Connection conn = null;

    try {
      Class.forName("org.mariadb.jdbc.Driver");

      // String url = "jdbc:mariadb://192.168.64.2:3306/webdb?charset=utf8";
      String url = "jdbc:mariadb://192.168.0.191:3306/webdb?charset=utf8";
      conn = DriverManager.getConnection(url, "webdb", "webdb");
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 로딩 실패:" + e);
    }

    return conn;
  }

  public int insert(UserVo vo) {
    int result = 0;

    try (Connection conn = getConnection();
        PreparedStatement pstmt1 = conn.prepareStatement(
            "insert into user values(null, ?, ?, password(?), ?, current_date())");
        PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {

      pstmt1.setString(1, vo.getName());
      pstmt1.setString(2, vo.getEmail());
      pstmt1.setString(3, vo.getPassword());
      pstmt1.setString(4, vo.getGender());
      result = pstmt1.executeUpdate();

      ResultSet rs = pstmt2.executeQuery();

      vo.setNo(rs.next() ? rs.getLong(1) : null);
      rs.close();
    } catch (SQLException e) {
      System.out.println("error:" + e);
    }

    return result;
  }

  public UserVo findByNoAndPassword(String email, String password) {
    UserVo result = null;

    try (Connection conn = getConnection();
        PreparedStatement pstmt1 = conn.prepareStatement(
            "select no, name from user where email = ? and password=password(?)");) {

      pstmt1.setString(1, email);
      pstmt1.setString(2, password);

      ResultSet rs = pstmt1.executeQuery();
      if (rs.next()) { // 나오는 row(유저)는 한개뿐 -> while문 안써도 되는 이유
        Long no = rs.getLong(1); // select절에 해당하는 부분 가져오기
        String name = rs.getString(2);

        result = new UserVo();
        result.setNo(no);
        result.setName(name);
      }
      rs.close();
    } catch (SQLException e) {
      System.out.println("error:" + e);
    }
    return result;
  }

  public UserVo findByNo(Long userNo) {
    UserVo result = null;

    try (Connection conn = getConnection();
        PreparedStatement pstmt1 =
            conn.prepareStatement("select no, name, email, gender from user where no = ?");) {
      pstmt1.setLong(1, userNo);

      ResultSet rs = pstmt1.executeQuery();
      if (rs.next()) {
        Long no = rs.getLong(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        String gender = rs.getString(4);

        result = new UserVo();
        result.setNo(no);
        result.setName(name);
        result.setEmail(email);
        result.setGender(gender);

      }
      rs.close();
    } catch (

    SQLException e) {
      System.out.println("error:" + e);
    }
    return result;
  }

  public int update(UserVo userVo) {
    int result = 0;

    try (Connection conn = getConnection();
        PreparedStatement pstmt1 =
            conn.prepareStatement("update user set name = ?, gender = ? where no = ?");
        PreparedStatement pstmt2 = conn.prepareStatement(
            "update user set name = ?, password=password(?), gender= ? where no = ?");

    ) {
      if (userVo.getPassword() != null) {
        pstmt2.setString(1, userVo.getName());
        pstmt2.setString(2, userVo.getPassword());
        pstmt2.setString(3, userVo.getGender());
        pstmt2.setLong(4, userVo.getNo());
        result = pstmt2.executeUpdate();
      } else {
        pstmt1.setString(1, userVo.getName());
        pstmt1.setString(2, userVo.getGender());
        pstmt1.setLong(3, userVo.getNo());
        result = pstmt1.executeUpdate();
      }
    } catch (SQLException e) {
      System.out.println("error:" + e);
    }
    return result;
  }
}
