package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS forexample.users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица была успешно создана");
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS forexample.users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица была успешно удалена");
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User: " + name + " был успешно добавлен в базу данных");
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User с указанным id был успешно удален из таблицы");
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            transaction.commit();
            return userList;
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getDbConnectionHibernate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE forexample.users;").executeUpdate();
            transaction.commit();
            System.out.println("Таблица была успешно очищена");
        } catch (HibernateException e) {
            System.out.println("Произошел эксепшен");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}