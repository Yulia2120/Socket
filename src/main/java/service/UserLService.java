package service;

import blogic.SessionUtil;

import dao.UserLDao;
import model.UserL;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserLService extends SessionUtil implements UserLDao {
    @Override
    public void add(UserL user) throws SQLException {
        //open session with a transaction
        openTransactionSession();
        Session session = getSession();
        session.save(user);
        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public String getLogin(String login) {
        openTransactionSession();
        String sql = "SELECT * FROM usersl WHERE login = :login";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(UserL.class);
        query.setParameter("login", login);
        UserL user = (UserL) query.getSingleResult();
        closeTransactionSession();
        return user.getLogin();
    }

    @Override
    public String getPass(String pass) {
        openTransactionSession();
        String sql = "SELECT * FROM usersl WHERE password = :password";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(UserL.class);
        query.setParameter("password", pass);
        UserL user = (UserL) query.getSingleResult();
        closeTransactionSession();
        return user.getPassword();
    }

    @Override
    public List<UserL> getAll() throws SQLException {

        openTransactionSession();
        String sql = "SELECT * FROM usersl";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(UserL.class);
        List<UserL> userList = query.list();


        closeTransactionSession();
        return userList;
    }
}
