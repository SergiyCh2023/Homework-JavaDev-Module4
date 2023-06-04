package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private  Statement statement;

    private static final Database INSTANCE = new Database();
    private Connection H2Connection;

    private DatabaseQueryService() throws SQLException {
        try {
               String h2ConnectionUrl = "jdbc:h2:./test";
               this.H2Connection = DriverManager.getConnection(h2ConnectionUrl);
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    public static void main(String[] args) throws SQLException {

        DatabaseQueryService dataQuery =  new DatabaseQueryService();

        List<MaxProjectCountClient> maxProjectCountClients = dataQuery.findMaxProjectsClient();
        List<LongestProject> longestprojectList = dataQuery.longestProjectsMethod();
        List<MaxSalaryWorker> maxSalaryWorkerList = dataQuery.maxSalaryWorkerMethod();
        List<YangestOldestWorker> yangestOldestWorkerList = dataQuery.yangestOldestWorkerMethod();
        List<ProjectPrices> projectPricesList = dataQuery.projectPricesMethod();


        for (var a: projectPricesList) {
            System.out.println(a);
        }

    }

    private List<ProjectPrices> projectPricesMethod() {

        List<ProjectPrices> projectPricesList = new ArrayList<>();

        try {
            String sql  =  String.join (
                    "\n",
                    Files.readAllLines(Paths.get("src\\sql\\print_project_prices.sql"))
            );
            statement = INSTANCE.getH2Connection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                ProjectPrices pp =new ProjectPrices( result.getInt("project_id"), result.getInt("price"));
                projectPricesList.add(pp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectPricesList;


    }

    private List<YangestOldestWorker> yangestOldestWorkerMethod() {

       List<YangestOldestWorker> yangestOldestWorkerList =new ArrayList<>();
        try {
            String sql  =  String.join (
                    "\n",
                    Files.readAllLines(Paths.get("src\\sql\\find_youngest_eldest_workers.sql"))
            );
            statement = INSTANCE.getH2Connection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                YangestOldestWorker yow =new YangestOldestWorker(result.getString("type"), result.getString(2), result.getDate(3) );
                yangestOldestWorkerList.add(yow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yangestOldestWorkerList;
    }


    private List<MaxSalaryWorker> maxSalaryWorkerMethod() {

        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();
        try {
            String sql  =  String.join (
                    "\n",
                    Files.readAllLines(Paths.get("src\\sql\\find_max_salary_worker.sql"))
            );
            statement = INSTANCE.getH2Connection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                MaxSalaryWorker msw =new MaxSalaryWorker( result.getString(1), result.getInt(2) );
                maxSalaryWorkerList.add(msw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkerList;

    }


    private List<LongestProject> longestProjectsMethod() {
        List<LongestProject> longestprojectList = new ArrayList<>();
        try {
            String sql  =  String.join (
                    "\n",
                    Files.readAllLines(Paths.get("src\\sql\\find_longest_project.sql"))
            );
            statement = INSTANCE.getH2Connection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                LongestProject lp =new LongestProject(result.getInt(1), result.getInt(2));
                longestprojectList.add(lp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return longestprojectList;
    }


    List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {

        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();
        try {
            String sql  =  String.join (
                    "\n",
                    Files.readAllLines(Paths.get("src\\sql\\find_max_projects_client.sql"))
            );
            statement = INSTANCE.getH2Connection().createStatement();
            ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

    MaxProjectCountClient mpcc =new MaxProjectCountClient(result.getString(1), result.getInt(2));
    maxProjectCountClientList.add(mpcc);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return maxProjectCountClientList;
    }

}
