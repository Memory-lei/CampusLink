package com.cduestc;

import com.cduestc.dao.*;
import com.cduestc.pojo.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        findSubjectWithStudents();
        findStudentWithSubjects();


    }
    public static void findById() {
        PersonDao personDao=new PersonDao();
        Person  ps =  personDao.findOne(3);
        System.out.println(ps.getPid()+" "+ps.getPname()+" "+ps.getPage()+" ");
        Card cd =  ps.getCard();
        System.out.println(cd.getCid()+" "+cd.getCode());

    }
    public static void findCode() {
        CardDao cardDao=new CardDao();
        Card card = cardDao.findByCode("512346798456123987");
        System.out.println(card.getCid()+" "+card.getCode());
        Person p = card.getPerson();
        System.out.println(p.getPname()+" "+p.getPage()+" ");
    }

    public static void findAll() {
        StudentDao studentDao=new StudentDao();
        List<Student> lststu = studentDao.findAll();
        for (int i=0;i<lststu.size();i++){
            Student s =  lststu.get(i);
            System.out.println(s.getSid()+" "+s.getName()+" "+s.getAge()+" "+s.getSex());
            Clazz c = s.getClazz();
            System.out.println(c.getClaid()+" "+c.getCode()+" "+c.getName());
        }
    }
    public static void findByCode() {
        ClazzDao clazzDao=new ClazzDao();
        Clazz c = clazzDao.findByCode("c66");
        System.out.println(c.getClaid()+" "+" "+c.getName()+" "+c.getCode());
        List<Student> lststu= c.getLststu();
        for (int i=0;i<lststu.size();i++){
            Student s = lststu.get(i);
            System.out.println(s.getSid()+" "+s.getName()+" "+s.getAge()+" "+s.getSex());
        }
    }


    public static void findSubjectWithStudents() {
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.findById(1); // 传入课程ID
        System.out.println("课程信息：" + subject.getSubid() + " " + subject.getName() + " " + subject.getNumber());
        System.out.println("选修学生：");
        List<Student> students = subject.getStudents();
        for (Student s : students) {
            System.out.println(s.getSid() + " " + s.getName() + " " + s.getSex() + " " + s.getAge());
        }
    }

    public static void findStudentWithSubjects() {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.findWithSubjects(1); // 传入学生ID
        System.out.println("学生信息：" + student.getSid() + " " + student.getName() + " " + student.getSex() + " " + student.getAge());
        System.out.println("选修课程：");
        List<Subject> subjects = student.getSubjects();
        for (Subject sub : subjects) {
            System.out.println(sub.getSubid() + " " + sub.getName() + " " + sub.getNumber());
        }
    }



}