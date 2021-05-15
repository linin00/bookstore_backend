package xyz.linin.bookstore_backend.controller;
import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test01")
    public  String test01(){
        return "Hello world";
    }

    @RequestMapping("/test02")
    public  String test02(){
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/movie?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


        final String USER = "root";
        final String PASS = "202252LIANjie*";
        Connection conn = null;
        Statement stmt = null;
        String name = "";
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT AID, name FROM actors";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("AID");
                name = name + rs.getString("name");

                System.out.print("AID: " + id);
                System.out.print(", name: " + name);
                System.out.print("\n");
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return name;
    }
}