package org.culturetripp.tonactiviter;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/tonactiviter","root","");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection connectDb() {
        return connect();
    }

    public static Connection connectDB() {
        return null;
    }
}
