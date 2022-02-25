package com.akhilesh.programs.jdbcprograms;

import java.sql.*;
/*
 Problem 1: SQL Exception :Client does not support authentication protocol requested by server; consider upgrading MySQL client

 Solution :
    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
    flush privileges;

 Problem 2 : SQL Exception :Unknown initial character set index '255' received from server. Initial client character set can be forced via the 'characterEncoding' property.

 Solution : Append characterEncoding  as utf8 in the DB_URL " ?characterEncoding=utf8 "

 */

public class ReadArticle {
    static final String DB_URL = "jdbc:mysql://localhost:3306/Dev?characterEncoding=utf8";
    static final String USER_ID = "root";
    static final String PASSWORD = "root";
    static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    static final String SELECT_QUERY = "select articleid , title , category from article";

    public static void main(String args[])  {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             Class.forName(DRIVER_NAME);
             connection = DriverManager.getConnection(DB_URL, USER_ID, PASSWORD);
             statement = connection.createStatement();

             resultSet = statement.executeQuery(SELECT_QUERY);

            while (resultSet.next()) {
                int articleid = resultSet.getInt(1);
                String articleName = resultSet.getString(2);
                String category = resultSet.getString(3);

                System.out.println(articleid + " , " + articleName + " , " + category);

            }
        }
        catch (ClassNotFoundException classNotFoundException){
            System.out.println("Exception occurred during loading the driver : "+ classNotFoundException.getMessage());
        }
        catch (SQLException sqlException){
            System.out.println("SQL Exception :" + sqlException.getMessage());
        }
        finally{
            try {
                if(connection!= null) {
                    connection.close();
                }
            }catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
        }



    }
}
