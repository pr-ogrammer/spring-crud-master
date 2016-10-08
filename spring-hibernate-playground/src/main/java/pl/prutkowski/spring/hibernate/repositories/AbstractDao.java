package pl.prutkowski.spring.hibernate.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by programmer on 10/8/16.
 */
public abstract class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object entry) {
        getSession().persist(entry);
    }

    public void delete(Object entry) {
        getSession().delete(entry);
    }
}
