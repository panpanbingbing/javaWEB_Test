package com.baen;

import java.util.Objects;

public class student {
    public  String name;
    public int age;
    public  String addr;

    public student() {
    }

    public student(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "student{" +
                "name:'" + name + '\'' +
                ", age:" + age +
                ", addr:'" + addr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        student student = (student) o;
        return  Objects.equals(name, student.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, addr);
    }
}
