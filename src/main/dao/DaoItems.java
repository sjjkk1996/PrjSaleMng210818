package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoItems extends DaoSet{
    public Object[] getItem() {
        Object[] result = null;
        String sql = "select product_id, product_name from demo_product_info order by product_id";
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1)+"/"+rs.getString(2));
            }
            result = list.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

