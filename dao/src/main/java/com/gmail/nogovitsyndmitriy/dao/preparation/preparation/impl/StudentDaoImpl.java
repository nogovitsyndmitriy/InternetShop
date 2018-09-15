package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl;

import com.gmail.nogovitsyndmitriy.dao.preparation.Student;
import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.StudentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl extends PreparationDaoImpl<Student> implements StudentDao {

    private final static Logger log = LogManager.getLogger(StudentDaoImpl.class);

    public StudentDaoImpl(Class<Student> clazz) {
        super(clazz);
    }

    public Student findByName(String name) {
        String hql = "from Student AS S WHERE S.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Student) query.getSingleResult();
    }
    public List<Student> findAll() {
        String hql = "from Student AS S order by S.university DESC";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }
    public List<Student> findAllByName(String name) {
        String hql = "from Student AS S WHERE S.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return query.list();
    }
    public List<Subject> findSubjects(String name) {
        String hql = "select S.subjects from Student as S where S.name<:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return query.list();
    }

}
