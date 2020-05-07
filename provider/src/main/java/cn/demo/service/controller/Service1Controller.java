package cn.demo.service.controller;

import cn.demo.AbstractController;
import cn.demo.service.client.Service0Client;

import java.net.URLDecoder;

import com.fasterxml.jackson.core.sym.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
public class Service1Controller extends AbstractController {
    public static class User {
        private int id;
        private String name;

        private Student student;

        public User() {
        }

        public int getId() {
            return id;
        }

        public User setId(final int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public User setName(final String name) {
            this.name = name;
            return this;
        }

        public Student getStudent() {
            return student;
        }

        public User setStudent(final Student student) {
            this.student = student;
            return this;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + ", student=" + student + '}';
        }
    }

    public static class Student {
        private String stuName;

        public Student() {
        }

        public String getStuName() {
            return stuName;
        }

        public Student setStuName(final String stuName) {
            this.stuName = stuName;
            return this;
        }

        @Override
        public String toString() {
            return "Student{" + "stuName='" + stuName + '\'' + '}';
        }
    }

    @Autowired
    Service0Client service0Client;

    @GetMapping("/test/{sleepSec}")
    public String test(@PathVariable final int sleepSec) {
        // if (1 == 1) {
        // System.out.println(333);
        // throw new RuntimeException("111111111111");
        // }
        return service0Client.test("leo", sleepSec);
    }

    @GetMapping("user")
    public String test() {
        // if (1 == 1) {
        // System.out.println(333);
        // throw new RuntimeException("111111111111");
        // }
        final Student stuName = new Student().setStuName("provider");
        final User asd = new User().setId(0).setName("hello").setStudent(stuName);

        System.out.println(asd.toString());
        return service0Client.user(asd);
    }

    @GetMapping("setUser/{id}/{name}")
    public String setUser(@PathVariable final int id, @PathVariable final String name) {

        // name = URLDecoder.decode(name,"utf-8");
        System.out.println(name);
        final Student stuName = new Student().setStuName(name);
        final User asd = new User()
                .setId(id)
                .setName("-------provider-------")
                .setStudent(stuName);

        System.out.println(asd.toString());
        return service0Client.user(asd);
    }



}
