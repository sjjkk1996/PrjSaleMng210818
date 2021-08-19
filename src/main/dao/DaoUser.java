package dao;

import controller.MainController;
import vo.UserVO;

import java.sql.SQLException;

public class DaoUser extends DaoSet {
    //다른패키지에서도 쓰이기 때문에 public이 붙었다 void는 리턴타입
    public UserVO checkLogin(String id, String pw) {
        UserVO user = null;
        try {
            conn = connDB();
            //쿼리를 짜려면 String 타입의 query가 필요하다
            String query = "select * from demo_users where id=? and password = ?";
            pstmt = conn.prepareStatement(query); //prepareStatement가 쿼리문을 이해할수 있게만듬
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery(); //rs = result set
            if (rs.next()) {
                user = new UserVO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getInt(5),
                        rs.getString(6), rs.getDate(7), rs.getString(8),
                        rs.getString(9)
                );
                MainController.getInstance().setSession(user);
                //기본생성자를 못만듬
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean registUser(UserVO vo) {
        boolean result = false;
        try {
            conn = connDB();
            String sql = "insert into demo_users (user_id, user_name," +
                    "password, created_on, quota, products, expires_on, " +
                    "admin_user, id)values(demo_users_seq.nextval,?,?," +
                    "sysdate,null,'Y',null,'N',?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getUserId());
            pstmt.setString(2, vo.getUserName());
            pstmt.setString(3, vo.getId());
            int cnt = pstmt.executeUpdate();
            result = cnt > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //리턴타입 매개변수 다넣고 스트링가서 물어보고
    public boolean duplicateId(UserVO vo) {
        boolean result = false;
        try {
            conn = connDB();
            String sql = "Select user_id from demo_users where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}


