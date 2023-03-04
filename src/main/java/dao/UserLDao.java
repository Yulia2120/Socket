package dao;

import model.UserL;

import java.sql.SQLException;
import java.util.List;

public interface UserLDao {
    void add(UserL user) throws SQLException;
    String getLogin(String login);
    String getPass(String pass);
    List<UserL> getAll() throws SQLException;

}
