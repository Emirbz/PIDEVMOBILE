/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Review;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author Emir
 */
public class ReviewService {
      public void AddReview(Review ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIDEV/web/app_dev.php/amir/ajout_review_json?qualite=" + ta.getQualite()+ "&service=" + ta.getService()
                + "&commentaire=" + ta.getCommentaire() + "&titre=" + ta.getTitre() + "&id=" + ta.getIdetab().getId();
        con.setUrl(Url);
        System.out.println(Url);
          System.out.println("ID ETAB"+ta.getIdetab().getId());
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("str hhhhhhhh"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

   
    
}
