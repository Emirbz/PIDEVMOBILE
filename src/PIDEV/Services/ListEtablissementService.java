/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.SousCategorie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
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
                LinkedHashMap<String, List<Object>> cc = (LinkedHashMap<String, List<Object>>) obj.get("souscat");
                List<List<Object>> l = new ArrayList<List<Object>>(cc.values());
                SousCategorie sc = new SousCategorie();
                sc.setNom(String.valueOf(l.get(1)));
         
                e.setSouscat(sc);
                e.setDevis_name(obj.get("devisName").toString());
                e.setImg1(obj.get("img1").toString());
                e.setImg2(obj.get("img2").toString());
                e.setImg3(obj.get("img3").toString());
                e.setMoyqualite(Double.parseDouble(obj.get("moyqualite").toString()));
                e.setMoyservice(Double.parseDouble(obj.get("moyservice").toString()));
                e.setLatitude(Double.parseDouble(obj.get("latitude").toString()));
                e.setLongitude(Double.parseDouble(obj.get("longitude").toString()));
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

    public ArrayList<Etablissement> getList2() {
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
    /////////////////
    

    ArrayList<Etablissement> listEtabsCat = new ArrayList<>();

    public ArrayList<Etablissement> getList2Cat(String souscat) {
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/list_restaurantcat_json?souscat="+souscat);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ListEtablissementService ser = new ListEtablissementService();
               listEtabsCat = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtabsCat;
    }
    public ArrayList<SousCategorie> getListSouscat(String json) {

        ArrayList<SousCategorie> listSousCategorie = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Etab = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Etab.get("root");

            for (Map<String, Object> obj : list) {
                SousCategorie e= new SousCategorie();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);

                e.setNom(obj.get("nom").toString());

              
                
              
              listSousCategorie.add(e);

            }

        } catch (IOException ex) {
        }

        return  listSousCategorie;

    }
     ArrayList<SousCategorie> listSousCat = new ArrayList<>();
    public ArrayList<SousCategorie> getListsouscat() {
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/amir/list_cat_json");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ListEtablissementService ser = new ListEtablissementService();
              listSousCat = ser.getListSouscat(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSousCat;
    }
}
