/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Deal;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author Skan
 */
public class DeleteDeal {

    public void DeleteDeal(Deal ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/deals/djs/" + ta.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
