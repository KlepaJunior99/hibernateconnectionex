package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        String insert = "CREATE TABLE" + " " + "IF NOT EXISTS" + " " + "users" +
                "(" +
                "id" + " " + "INT AUTO_INCREMENT PRIMARY KEY NOT NULL" + "," +
                "firstname" + " " + "VARCHAR(45)" + "," +
                "lastName" + " " + "VARCHAR(45)" + "," +
                "age" + " " + "TINYINT" + ")";
        try (PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert)) {
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }

    }

    public void dropUsersTable() {
        String insert = "DROP TABLE IF EXISTS users;";
        try (PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert)) {
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO " + "users" + "("
                + "firstname" + "," + "lastName" + "," +
                "age" + ")" + "VALUES(?,?,?)";
        try (PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert)) {
            prSt.setString(1, name);
            prSt.setString(2, lastName);
            prSt.setInt(3, age);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public void removeUserById(long id) {
        String insert = "DELETE from users where id=" + id;
        try (PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert)) {
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String insert = "select id, firstname, lastName, age from users";
        try (PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert);
             ResultSet rs = prSt.executeQuery(insert)) {
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), (byte) rs.getByte(4));
                list.add(user);
            }
        }catch (SQLException | ClassNotFoundException e) {
        }
        return list;
    }

    public void cleanUsersTable() {
        String insert = "TRUNCATE users;";
        try(PreparedStatement prSt = Util.getDbConnectionJDBC().prepareStatement(insert)) {
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }
    }
}
