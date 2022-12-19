package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Vladimir", "Klepikov", (byte) 23);
        us.saveUser("Ivan", "Petrov", (byte) 27);
        us.saveUser("Anna", "Vershinina", (byte) 22);
        us.saveUser("Sergey", "Orehov", (byte) 18);
        System.out.println(us.getAllUsers());
        us.removeUserById(4);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();

    }
}
