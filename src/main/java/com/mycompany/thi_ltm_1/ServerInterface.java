/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Tuong
 */
public interface ServerInterface extends Remote{
    void register(ClientInterface ci) throws RemoteException;
    void sendMessageToAll(String message) throws RemoteException;
}
