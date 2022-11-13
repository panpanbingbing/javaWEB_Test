import com.Mysql_Util.MysqlUtil_Test;
import com.baen.student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
//        MysqlUtil_Test mu=new MysqlUtil_Test();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection conn=null;
////        System.out.println();
//
////            System.out.println(conn);
//            try {
//                String sql = "select deptno,dname from dept; ";
//                conn = mu.deit();
//                ps = conn.prepareCall(sql);
//                rs = ps.executeQuery();
//                int i = 0;
//                while (rs.next()) {
//                    String deptno = rs.getString("deptno");
//                    String dname = rs.getString("dname");
//                    System.out.println(deptno+" "+dname);
//                }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            mu.close(conn,ps,rs);
        String resource = "mybatis001.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session=sqlSessionFactory.openSession();
        System.out.println(session);
        student student =session.selectOne("student.getstudentID","ximing");
        System.out.println(student);
        session.close();

    }
}
