package com.example.demojavafx;

import java.util.List;

public interface IStudent {
    int add(Student s);
    int update(Student s);
    int delete(int id);
    List<Student> list();
    Student get(int id);
}
