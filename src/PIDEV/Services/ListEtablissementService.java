/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;


import PIDEV.Entities.Etablissement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class ListEtablissementService {
  
    
    public ArrayList<Etablissement> getListTask(String json) {

        ArrayList<Etablissement> listEtabs = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Etab = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Etab.get("root");

            for (Map<String, Object> obj : list) {
                Etablissement e = new Etablissement();

                                float id = Float.parseFloat(obj.get("id").toString());
      
                e.setId((int) id);
                
                e.setName(obj.get("name").toString());
               
                
                e.setAddress(obj.get("address").toString());
                e.setDevis_name(obj.get("devisName").toString());
                e.setImg1(obj.get("img1").toString());
                e.setImg2(obj.get("img2").toString());
                e.setImg3(obj.get("img3").toString());
               
                listEtabs.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listEtabs;

    }
    
    
    ArrayList<Etablissement> listEtabs = new ArrayList<>();
    
    public ArrayList<Etablissement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/list_restaurant_json");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ListEtablissementService ser = new ListEtablissementService();
                listEtabs = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtabs;
    }
}
