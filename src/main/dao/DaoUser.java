package dao;

import controller.MainController;
import vo.UserVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
            if(duplicateId(vo.getId())){
                return false;
            }
            String sql = "insert into demo_users (user_id, user_name," +
                    "password, created_on, quota, products, expires_on, " +
                    "admin_user, id)values(demo_users_seq.nextval,?,?," +
                    "sysdate,null,'Y',null,'N',?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getUserName());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getId());
            int cnt = pstmt.executeUpdate();
            result = cnt > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //리턴타입 매개변수 다넣고 스트링가서 물어보고
    public boolean duplicateId(String id) {
        boolean result = false;
        try {
            conn = connDB();
            String sql = "Select * from demo_users where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
                result = true;
//            {
//            JOptionPane.showMessageDialog(null,"중복된 아이디가 있습니다.");
//                result = true;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public DefaultTableModel getUserList(DefaultTableModel model, String srch) {
        String query = "";
        model = new DefaultTableModel(new String[] {
                "USER_ID","ID","이름","입사일","QUOTA","상품유무",
                                "퇴사일","관리자여부"},0);
        try {
            conn = connDB();
            //쿼리를 짜려면 String 타입의 query가 필요하다
            if (srch.equals("")){
                query = "select * from demo_users";
            }else {
                query = "select * from demo_users where user_name=?";
            }
            pstmt = conn.prepareStatement(query); //prepareStatement가 쿼리문을 이해할수 있게만듬
            if(!srch.equals(""))pstmt.setString(1, srch);
            rs = pstmt.executeQuery(); //rs = result set
            while (rs.next()) {
                String[] tmpArr = {
                    rs.getInt(1)+"",rs.getString(9), rs.getString(2)
                            , rs.getDate(4)+"", rs.getInt(5)+"",
                            rs.getString(6), String.valueOf(rs.getDate(7)), rs.getString(8),

                };
                model.addRow(tmpArr);
                //기본생성자를 못만듬
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return model;
    }
}


