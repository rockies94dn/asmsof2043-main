/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.cafe.entity.Category;

/**
 *
 * @author dtoan //Test các truy vấn trên databse với XJDBC
 */
public class Test {

    public static void main(String[] args) {
//        insertData();
        searchData();
    }

    public static void insertData() {
        //Thêm mới:
        String sql = "INSERT INTO Categories (id, name) VALUES (?, ?);";
        XJdbc.executeUpdate(sql, "C01", "Cà Phê");
        XJdbc.executeUpdate(sql, "C02", "Trà Sữa");
        XJdbc.executeUpdate(sql, "C03", "Nước Ép");
        XJdbc.executeUpdate(sql, "C04", "Nước Đóng Chai");
    }

    public static void searchData() {
        //Truy vấn nhiều bản ghi:
        List<Category> list = new ArrayList();
        String sql = "SELECT * FROM Categories WHERE NAME LIKE ?;";
        ResultSet rs = XJdbc.executeQuery(sql, "%Nước%");
        try {
            while (rs.next()) {
                Category cat = new Category(
                        rs.getString("id"),
                        rs.getString("name")
                );
                list.add(cat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Category c : list) {
            System.out.println(c.toString());
        }
    }

    public static void searchARecord() {
        //Truy vấn một giá trị:
        String sql = "SELECT max(Id) FROM Categories WHERE Name LIKE ?";
        String id = XJdbc.getValue(sql, "%Nước%");
    }

}
