package org.example;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) throws SQLException, IOException {

        try {

            File file = new File("src\\sql\\init_db.sql"); //
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Statement statement = Database.getInstance().getH2Connection().createStatement();

            String line;
            String allLine = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                allLine +=line + " ";
            }

            statement.execute(allLine);
            reader.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}