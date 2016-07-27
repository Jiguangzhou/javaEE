package com.kaishengit.test;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTestCase {

    //最佳实践
    //保存时一方放弃关系维护；

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = new Teacher();
        teacher.setTeachername("T1");

        Teacher teacher1 = new Teacher();
        teacher1.setTeachername("T2");

        Student student = new Student();
        student.setStudentname("S1");

        Student student1 = new Student();
        student1.setStudentname("S2");

        Set<Teacher> teacherSet = new HashSet<>();
        teacherSet.add(teacher);
        teacherSet.add(teacher1);

        student.setTeacherSet(teacherSet);
        student1.setTeacherSet(teacherSet);

        session.save(teacher);
        session.save(teacher1);
        session.save(student);
        session.save(student1);

        session.getTransaction().commit();
    }

    @Test
    public void  testFindTeacher(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class,3);
        System.out.println(teacher.getTeachername());

        Set<Student> studentSet = teacher.getStudentSet();
        for (Student student:studentSet){
            System.out.println(student.getId()+":"+student.getStudentname());
        }

        session.getTransaction().commit();
    }

}
