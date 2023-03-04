import blogic.HibernateUtil;
import model.UserL;
import service.UserLService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) throws IOException, SQLException {


        UserLService userService = new UserLService();
        List<UserL> users = userService.getAll();
        System.out.println(users);

        HibernateUtil.shutdown();

    }
}