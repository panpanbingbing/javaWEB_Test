package com.obj.Test;



import com.obj.Dao.StudentDao;
import com.obj.domain.Student;
import com.obj.util.SqlSessionUtil;
import java.util.List;
public class Test {
    public static void main(String[] args) {
        StudentDao ss=SqlSessionUtil.getSession().getMapper(StudentDao.class);


//           List<Student> s= ss.getAll();
//          System.out.println(s);

          Student s1= ss.getID(2);
        System.out.println(s1);
    }
}
