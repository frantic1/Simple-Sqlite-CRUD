package com.frantic.simplecrud;

/**
 * Created by Frantic on 6/15/2016.
 */
public class Student {
    int id;
    String name;
    String address;
    String faculty;
    String phone;
    String email;

    public Student() {
    }

    public Student(String name, String address, String faculty, String phone, String email) {
        this.name = name;
        this.address = address;
        this.faculty = faculty;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
