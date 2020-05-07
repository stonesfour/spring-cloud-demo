package cn.demo.service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 */
@RestController
public class Service0Controller {
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

    /**
     * 用于测试ribbon 重试机制
     */
    int count = 0;

    @GetMapping("user/{userId}/{sleepSec}")
    String user(@PathVariable final String userId, @PathVariable final int sleepSec) {
        try {
            System.out.println("hello:" + userId);
            count++;
            TimeUnit.SECONDS.sleep(sleepSec - count);
            return "hello:" + userId;
        } catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("test")
    String post(@RequestBody final User user) {
        System.out.println(user.toString());
        return user.toString();
    }


    @GetMapping("k8s/{id}/{name}")
    public String k8s(@PathVariable final int id, @PathVariable final String name) {

        return new Config().restTemplate().getForObject("http://provider:8081/setUser/"+ id + "/" + name, String.class);

    }
    @GetMapping("eureka/{id}/{name}")
    public String eureka(@PathVariable final int id, @PathVariable final String name) {

        return new Config().restTemplate().getForObject("http://provider/setUser/"+ id + "/" + name, String.class);

    }
}
