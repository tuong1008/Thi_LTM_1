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

/**
 *
 * @author Tuong
 */
public class Server1 {
    public static boolean isSoNguyenTo(int x){
        if (x==1) return true;
        else if (x==2) return false;
        else if (x==3) return true;
        for (int i=2; i<=x/2 ; i++){
            if (x%i==0) return false;
        }
        return true;
    }
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        ServerSocket socket = new ServerSocket(5000);
        while (true){
            System.out.println("Waiting");
            Socket connection = socket.accept();
            
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            
            String message = (String) ois.readObject();
            
            int input = Integer.valueOf(message);
            message="";
            for (int i=1; i<=input; i++){
                if (Server1.isSoNguyenTo(i)){
                    message= message +" " + String.valueOf(i);
                }
            }
            
            oos.writeObject(message);
        }
    }
}
