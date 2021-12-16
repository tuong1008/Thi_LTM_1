/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tuong
 */
public class Server3 {
    public static void main(String[] args) throws RemoteException{
        ServerInterface server = new Server();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Server", server);
    }
}
class Server implements ServerInterface{
        
        List<ClientInterface> listClient = new ArrayList<ClientInterface>();

        public Server() throws RemoteException {
            UnicastRemoteObject.exportObject(this, 0);
        }
        
        
        
        @Override
        public void sendMessageToAll(String message) throws RemoteException{
            for (ClientInterface i : listClient){
                i.receiveMessage(message);
            }
        }

        @Override
        public void register(ClientInterface ci) throws RemoteException {
            listClient.add(ci);
        }
        
    }
