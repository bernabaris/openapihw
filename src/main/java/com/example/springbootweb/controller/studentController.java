package com.example.springbootweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.student;

@RestController
public class studentController {

    private List<student> students= createList();

    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = "application/json")
    public List<student> firstPage() {
        return students;
    }

    @DeleteMapping(path = { "/{id}" })
    public student delete(@PathVariable("id") int id) {
        student deletedStu = null;
        for (student stu : students) {
            if (stu.getStudentId().equals(id)) {
                students.remove(stu);
                deletedStu = stu;
                break;
            }
        }
        return deletedStu;
    }

    @PostMapping
    public student create(@RequestBody student user) {
        students.add(user);
        System.out.println(students);
        return user;
    }

    private static List<student> createList() {
        List<student> tempStudents = new ArrayList<>();
        student stu1 = new student();
        stu1.setName("emp1");
        stu1.setField("fullstack");
        stu1.setStudentId("1");
        stu1.setGpa(3.0);

        student stu2 = new student();
        stu2.setName("stu2");
        stu2.setField("devops");
        stu2.setStudentId("2");
        stu2.setGpa(3.2);
        tempStudents.add(stu1);
        tempStudents.add(stu2);
        return tempStudents;
    }
}
