package com.cduestc.pojo;

import java.util.List;

public class Subject {
    private int subid;       // 课程ID（对应subject表的subid）
    private String name;     // 课程名称（对应name字段）
    private String number;   // 课程编号（对应number字段）

    // 多对多：一门课程被多个学生选修，关联学生列表
    private List<Student> students;

    // getter和setter
    public int getSubid() { return subid; }
    public void setSubid(int subid) { this.subid = subid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}