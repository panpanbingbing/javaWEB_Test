package com.obj.Dao;

import com.obj.domain.Student;

import java.util.List;

public interface StudentDao {


    List<Student> getAll();

    Student getID(int i);

}
