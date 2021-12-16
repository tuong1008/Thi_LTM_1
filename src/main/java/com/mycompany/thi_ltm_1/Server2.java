/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Tuong
 */
public class Server2 {
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException{
        byte[] buf = new byte[256];
        DatagramSocket socket = new DatagramSocket(5001);
        while (true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            byte[] data = packet.getData();
            
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bis);
            List<String> allLines = (List<String>) ois.readObject();
            List<String> after = new ArrayList<String>();
            for (String str:allLines){
                String[] arr = str.split(" ");
                Arrays.sort(arr, Collections.reverseOrder());
                String temp = "";
                for (String i:arr){
                    temp = temp + i+" ";
                }
                temp= temp.trim();
                after.add(temp);
            }
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(after);
            
            packet = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, ip, port);
            socket.send(packet);
        }
    }
}
