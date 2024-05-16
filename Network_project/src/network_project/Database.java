
package network_project;

/**
 * tarik.alrayan
 * 2121221366
 */

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
Database class used to make all connections and data transfer from or to sql database
 */
public abstract class Database {

    static Connection conn;
    static Statement stat;
    static ResultSet rs;

    // starting connection with sql database
    public static void Start_connection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_dp", "root", "");
    }

    // insert sql query to sql database
    public static void Insert(String query) throws SQLException {
        stat = conn.createStatement();
        stat.executeUpdate(query);
        stat.close();
    }

    // used to return the unique stat of the sellected item from the tabel (used for tc in register)
    public static boolean isUnique(String table, String column, String item) throws SQLException {
        stat = conn.createStatement();
        rs = stat.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + column + "='" + item + "'");
        boolean isUnique = !rs.next();
        stat.close();

        return isUnique;
    }

    // find the user from the tabel by the name and when it find it will return it as an opject from User class
    public static User getUserObject(String name) throws SQLException {

        String password = "";

        stat = conn.createStatement();
        rs = stat.executeQuery("SELECT * FROM user WHERE name = '" + name + "'");

        if (rs.next()) {

            password = rs.getString("password");

        } else {
            stat.close();
        }
        return new User(name, password);
    }

    // check if the key is unique 
    public static boolean checkProjectKey(String table, String column, String key) throws SQLException {
        try (Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + column + "='" + key + "'")) {

            return rs.next();
        }
    }

    // get all projects keys
    private static ArrayList<String> getProjectsKeys(String name) throws SQLException {
        ArrayList<String> keys = new ArrayList();
        String key;
        stat = conn.createStatement();
        rs = stat.executeQuery("SELECT project FROM userhasproject WHERE user = '" + name + "'");

        while (rs.next()) {
            key = rs.getString("project");
            keys.add(key);
        }
        stat.close();
        return keys;
    }

    // get project opject from the key
    public static ArrayList<Project> getProjectsopjects(String member_name) throws SQLException {
        ArrayList<String> arr=getProjectsKeys(member_name);
        ArrayList<Project> projects = new ArrayList();
        Project p;
        String name;
        String keyword;
        String admin;        
        for (int i = 0; i < arr.size(); i++) {
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM project WHERE keyword = '" +arr.get(i)+"'");

            while (rs.next()) {
                name = rs.getString("name");
                keyword = rs.getString("keyword");
                admin = rs.getString("admin");
                p=new Project(name, getUserObject(admin));
                p.setKey(keyword);
                projects.add(p);
            }
            stat.close();
        }
      return projects;
    }
    
    // get all the project memebers from the key
     public static ArrayList<String> getMemebersopjects(String key) throws SQLException {
        
        ArrayList<String> members = new ArrayList();
        
        String name;
        String keyword;
        String admin;        
         
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT user FROM userhasproject WHERE project = '" +key+"'");

            while (rs.next()) {
                name = rs.getString("user");
               
                members.add(name);
            }
            stat.close();
        
      return members;
    }


}
