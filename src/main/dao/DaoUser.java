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
            String query = "select * from demo_users where id='"
                    + id + "' and password = '" + pw + "'";
            pstmt = conn.prepareStatement(query); //prepareStatement가 쿼리문을 이해할수 있게만듬
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
}


