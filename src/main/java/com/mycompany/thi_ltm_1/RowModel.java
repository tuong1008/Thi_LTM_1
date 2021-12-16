/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thi_ltm_1;

import java.io.Serializable;

public class RowModel implements Serializable{
    int stt;
        String user;
        String pass;

        public RowModel(int stt, String user, String pass) {
            this.stt = stt;
            this.user = user;
            this.pass = pass;
        }
}
