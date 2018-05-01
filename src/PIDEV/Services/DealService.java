/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Deal;
import PIDEV.Entities.Catdeal;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Skander
 */
public class DealService {

    public ArrayList<Deal> getListDeals(String json) {
        ArrayList<Deal> deals = new ArrayList<>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> dealss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(dealss);
            List<Map<String, Object>> list = (List<Map<String, Object>>) dealss.get("deals");
            for (Map<String, Object> obj : list) {
                Deal e = new Deal();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println((int) id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                e.setDevisName(obj.get("devisName").toString());
                e.setAdresse(obj.get("adresse").toString());
                Map<String, Object> dd = (Map<String, Object>) obj.get("datefin");
                float time=Float.parseFloat(dd.get("timestamp").toString());
                e.setDatefin(new Date((long)time*1000));
                e.setRating(Integer.parseInt(obj.get("rating").toString().substring(0, 1)));
                e.setPromotion(Integer.parseInt(obj.get("promotion").toString().substring(0, 1)));
                e.setOldprix(Double.parseDouble(obj.get("oldprix").toString()));
                e.setNewprix(Double.parseDouble(obj.get("newprix").toString()));
                e.setPlacesdispo(Integer.parseInt(obj.get("placesdispo").toString().substring(0, 1)));
                e.setRegion(obj.get("region").toString());
                LinkedHashMap<String, List<Object>> cd = (LinkedHashMap<String, List<Object>>) obj.get("cat");
                List<List<Object>> l = new ArrayList<>(cd.values());
                Catdeal c = new Catdeal();
                c.setId(Integer.parseInt(String.valueOf(l.get(0)).substring(0, 1)));
                c.setNom(String.valueOf(l.get(1)));
                c.setDevisName(String.valueOf(l.get(2)));
                e.setCat(c);
                deals.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return deals;
    }
    ArrayList<Deal> listDeals = new ArrayList<>();
    ArrayList<Catdeal> listcats = new ArrayList<>();

    public ArrayList<Deal> getListDeals2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/web/app_dev.php/deals/json");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DealService ser = new DealService();
                listDeals = ser.getListDeals(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDeals;
    }

    public ArrayList<Catdeal> getListcats2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/web/app_dev.php/deals/json");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DealService ser = new DealService();
                listcats = ser.getListcats(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcats;
    }

    public ArrayList<Catdeal> getListcats(String json) {
        ArrayList<Catdeal> cats = new ArrayList<>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> dealss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(dealss);
            List<Map<String, Object>> list = (List<Map<String, Object>>) dealss.get("cats");
            for (Map<String, Object> obj : list) {

                Catdeal c = new Catdeal();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println((int) id);
                c.setId((int) id);
                c.setNom(obj.get("nom").toString());
                c.setDevisName(obj.get("devisName").toString());
                cats.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return cats;
    }

}
