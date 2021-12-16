/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tuong
 */
public class Server4 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://";
        String serverName = "TUONG-PC";
        String databaseName = ";databaseName=LTM";
        
        Connection conn = DriverManager.getConnection(url + serverName + databaseName, "sa", "123");
        ServerSocket socket = new ServerSocket(5004);
        while (true){
            Socket connection = socket.accept();

            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());

            UserAndPassModel thongTinDangNhap = (UserAndPassModel) ois.readObject();

            String sql = "select * from TaiKhoan where [user]=? and pass=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, thongTinDangNhap.user);
            statement.setString(2, thongTinDangNhap.pass);

            ResultSet rs = statement.executeQuery();
            List<RowModel> listRow = new ArrayList<RowModel>();
            boolean isEmpty = true;
            while (rs.next()) {
                RowModel row = new RowModel(rs.getInt(1), rs.getString(2), rs.getString(3));
                listRow.add(row);
                isEmpty=false;
            }
            if (isEmpty==true) {
                oos.writeObject("sai tai khoan hoac mat khau");
            } else {
                oos.writeObject("dang nhap thanh cong");
                oos.writeObject(listRow);
            }
        }
    }
}
