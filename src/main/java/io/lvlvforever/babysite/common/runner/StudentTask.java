package io.lvlvforever.babysite.common.runner;

import io.lvlvforever.babysite.blog.dao.ClassroomRepo;
import io.lvlvforever.babysite.blog.dao.StudentRepo;
import io.lvlvforever.babysite.blog.model.Classroom;
import io.lvlvforever.babysite.blog.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Component
public class StudentTask implements CommandLineRunner {
    @Autowired
    private StudentRepo repo;
    @Autowired
    ClassroomRepo repo2;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void run(String... args) throws Exception {

        Student student = new Student();
        student.setName("123");
        Student student1 = new Student();
        student1.setName("代鹏");
        repo.save(student);
//        repo.save(student1);
//
//
//        List<Student> list = new ArrayList<>();
//        list.add(student);
//        list.add(student1);
        Classroom classroom = repo2.findById("5e10e3bb037d192a943df0d0").get();


        repo.list();


        System.err.println(classroom);
    }
}
