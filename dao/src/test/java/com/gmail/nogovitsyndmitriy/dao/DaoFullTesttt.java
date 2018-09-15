package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import com.gmail.nogovitsyndmitriy.dao.preparation.Student;
import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.StudentDao;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl.StudentDaoImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class DaoFullTesttt {
    @Test
    public void fullTest(){
        StudentDao studentDao = new StudentDaoImpl(Student.class);
        Session session = studentDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Student student = new Student();
        student.setName("Vasiliy");
        RecordBook recordBook = new RecordBook();
        recordBook.setMark(3.5);
        Student student1 = new Student();
        student1.setName("asdsf");
        student1.setSurname("dgsgsdgs");
        recordBook.setStudent(student1);
        student1.setRecordBook(recordBook);
        studentDao.save(student);
        studentDao.save(student1);
        transaction.commit();
    }
    @Test
    public void findAllStudents(){
        StudentDao studentDao = new StudentDaoImpl(Student.class);
        Session session = studentDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<Student> students = new LinkedList<>();
        students = ((StudentDaoImpl) studentDao).findAll();
        students.stream().forEach(System.out::println);
        transaction.commit();
    }
    @Test
    public void findSubjectForStudentByName(){
        StudentDao studentDao = new StudentDaoImpl(Student.class);
        Session session = studentDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<Subject> subjects = new LinkedList<>();
        subjects = ((StudentDaoImpl) studentDao).findSubjects("John");
        subjects.stream().forEach(System.out::println);

        transaction.commit();
    }

}
