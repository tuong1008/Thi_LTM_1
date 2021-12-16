/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.io.Serializable;

/**
 *
 * @author Tuong
 */
public class UserAndPassModel implements Serializable{
    String user;
    String pass;

    public UserAndPassModel(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
}
