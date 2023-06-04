package org.example;

import java.sql.*;

public class Database {

    private static final Database INSTANCE = new Database();  // єдиний екземпляр бази данних
    private Connection H2Connection; // приватна змінна для підключення


    Database()  {

        try {
            String h2ConnectionUrl = "jdbc:h2:./test";
            this.H2Connection = DriverManager.getConnection(h2ConnectionUrl);
            } catch (SQLException ex) {
              throw new RuntimeException();
            }
    }
        public static Database getInstance() {
            return INSTANCE;  // екземпляр чогось, мабудь підключення до БД
        }

        public Connection getH2Connection() {
            return  H2Connection;
        }

        public int executeUpdate(String query) throws SQLException {
            try (Statement statement = INSTANCE.getH2Connection().createStatement()){
                return statement.executeUpdate(query);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


        public void closeConnection() {
            try {
                H2Connection.close();
            }   catch (SQLException ex) {
                throw new RuntimeException();
            }
        }

    }



