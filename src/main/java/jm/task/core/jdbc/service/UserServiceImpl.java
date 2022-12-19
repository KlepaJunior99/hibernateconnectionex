package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl usdji = new UserDaoHibernateImpl();
    public void createUsersTable() {
        usdji.createUsersTable();

    }

    public void dropUsersTable() {
        usdji.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usdji.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usdji.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return usdji.getAllUsers();
    }

    public void cleanUsersTable() {
        usdji.cleanUsersTable();
    }
}
