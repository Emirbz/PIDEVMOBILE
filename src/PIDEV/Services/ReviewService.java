/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Review;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
      
public ArrayList<Review> getListTask(String json) {

        ArrayList<Review> listrev = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Etab = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Etab.get("root");

            for (Map<String, Object> obj : list) {
                Review e = new Review();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);

                e.setCommentaire(obj.get("commentaire").toString());

                e.setTitre(obj.get("titre").toString());
                LinkedHashMap<String, List<Object>> cc = (LinkedHashMap<String, List<Object>>) obj.get("iduser");

                List<List<Object>> l = new ArrayList<>(cc.values());
 ;
                System.out.println("7:"+String.valueOf(l.get(7)));
          
                User sc = new User();
                sc.setName(String.valueOf(l.get(0)));
                sc.setSurname(String.valueOf(l.get(1)));
                sc.setDevis_name(String.valueOf(l.get(5)));
         
                e.setIduser(sc);
                
                e.setService(Double.parseDouble(obj.get("service").toString()));
                e.setQualite(Double.parseDouble(obj.get("qualite").toString()));

              
                
              
                listrev.add(e);

            }

        } catch (IOException ex) {
        }

        return listrev;

    }

    ArrayList<Review> ListReview = new ArrayList<>();

    public ArrayList<Review> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/list_review_json");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               ReviewService ser = new ReviewService();
                ListReview = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListReview;
    }
    public void deleterev(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/delete_review_json?id="+id);
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
    
   
    
}
