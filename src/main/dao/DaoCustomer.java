package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCustomer extends DaoSet{
    public Object[] getCustAll(){
        Object[] result =null;
        String sql = "select CUSTOMER_ID, CUST_FIRST_NAME,CUST_LAST_NAME from demo_customers order by cust_first_name";
        ArrayList list = new ArrayList();
        //어레이 리스트는 크기가 가변적인 성질을 가지고 있다 그래서 얼만큼 더
        // 정보들이 들어올지 모를때는 arraylist 써주는게 바람직하다
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                list.add(rs.getInt(1)+"/"+rs.getString(2)+""+rs.getString(3));
            }
            result= list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
