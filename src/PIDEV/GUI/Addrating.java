/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import PIDEV.Entities.Note;
/**
 *
 * @author Skan
 */
public class Addrating {

    public void ajoutTask(Note ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/deals/ajs/" ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
