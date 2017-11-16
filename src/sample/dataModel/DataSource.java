package sample.dataModel;

import java.io.FileInputStream;
import java.sql.*;

public class DataSource {
    private static final String DB_NAME = "studentData.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:/D:/Projects/StudentDataSaver/" + DB_NAME;
    private Connection conn;
    private static DataSource dataSource = new DataSource();
    private DataSource() {

    }

    public static DataSource getInstance() {
        return dataSource;
    }

    public void openConnection() {
        try {
            conn = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.out.println("Some errors in Establishing connection");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        try (Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS studentRecord(name TEXT, fname TEXT" +
                    ",age INT,mobile TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveStudentData(String name, String fname, int age, String mobile,String gender) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO studentRecord(name,fname,age,mobile,gender) " +
                    "VALUES('" + name + "','" + fname + "'," + age + ",'" + mobile +"','"+gender+"')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean login(String username, String password, String who) throws SQLException {
        final String TABLE_NAME;
        TABLE_NAME=who.equals("User")?"userData":"adminData";
           PreparedStatement preparedStatement = conn.prepareStatement(
            "SELECT username,password FROM "+TABLE_NAME+" WHERE username= ? AND password= ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String findUsername = resultSet.getString("username");
                String findPassword = resultSet.getString("password");
                if (findUsername.equals(username) && findPassword.equals(password)) {
                        resultSet.close();
                        preparedStatement.close();
                    return true;
                }
            }
        resultSet.close();
        preparedStatement.close();
        return false;
    }
    public boolean saveNewUser(String name,String email,String mobile,String dob,String password,String registerWho,FileInputStream image,int i) {
        String TABLE_NAME =registerWho.equals("User")?"userData":"adminData";
        try(PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "+TABLE_NAME+"(username,email,mobile,dob,password,photo) VALUES (?,?,?,?,?,?)")) {
           pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.setString(3,mobile);
            pstmt.setString(4,dob);
            pstmt.setString(5,password);
            pstmt.setBinaryStream(6,image,i);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
