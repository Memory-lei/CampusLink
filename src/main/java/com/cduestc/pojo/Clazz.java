package com.cduestc.pojo;

import java.util.List;

public class Clazz {
    private int claid;
    private String name;
    private  String code;
    private List<Student> lststu;

    public List<Student> getLststu() {
        return lststu;
    }

    public void setLststu(List<Student> lststu) {
        this.lststu = lststu;
    }

    public int getClaid() {
        return claid;
    }

    public void setClaid(int claid) {
        this.claid = claid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
