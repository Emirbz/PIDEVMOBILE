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
public class ProfilRestaurantService {
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
               e.setDevis_name(obj.get("devisName").toString());
                
                e.setAddress(obj.get("address").toString());
                  e.setImg1(obj.get("img1").toString());
                e.setImg2(obj.get("img2").toString());
                e.setImg3(obj.get("img3").toString());
                 e.setFumer(Boolean.parseBoolean(obj.get("fumer").toString()));
                 e.setParking(Boolean.parseBoolean(obj.get("parking").toString()));
                 e.setCartecredit(Boolean.parseBoolean(obj.get("cartecredit").toString()));
                 e.setChaiseroulante(Boolean.parseBoolean(obj.get("chaiseroulante").toString()));
                 e.setTerasse(Boolean.parseBoolean(obj.get("terasse").toString()));
                 e.setWifi(Boolean.parseBoolean(obj.get("wifi").toString()));
                 e.setReservations(Boolean.parseBoolean(obj.get("reservations").toString()));
                 e.setLivraison(Boolean.parseBoolean(obj.get("livraison").toString()));
                 e.setClimatisation(Boolean.parseBoolean(obj.get("climatisation").toString()));
                 e.setLundisamedio(obj.get("lundisamedio").toString());
                 e.setLundisamedif(obj.get("lundisamedif").toString());
                 e.setDimancheo(obj.get("dimancheo").toString());
                 e.setDimanchef(obj.get("dimanchef").toString());
                 
                listEtabs.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listEtabs;

    }
    
    
    ArrayList<Etablissement> listEtabs = new ArrayList<>();
    
    public ArrayList<Etablissement> getList2(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/profil_restaurant_json/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProfilRestaurantService ser = new ProfilRestaurantService();
                listEtabs = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtabs;
    }
    
}
