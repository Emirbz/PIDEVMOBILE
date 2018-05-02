/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Reservation;
import PIDEV.Entities.SousCategorie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Calendar;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author ons
 */
public class ReservationService {

    public void addReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIDEV/web/app_dev.php/slim/addResEtab?idEtab=" + r.getEtablissement().getId() + "&idUser=" + r.getUser().getId() + "&nombre=" + r.getNombre() + "&date=" + r.getDate() + "&description=" + r.getDescription() + "&aunomde=" + r.getDescription();
        con.setUrl(Url);
        System.out.println(Url);
        System.out.println("ID Etab" + r.getEtablissement().getId());
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("str :" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Reservation> getListReservation(String json) {

        ArrayList<Reservation> listRes = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Res = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Res.get("root");

            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();

                float id = Float.parseFloat(obj.get("id").toString());

                r.setId((int) id);
                LinkedHashMap<String, List<Object>> etab = (LinkedHashMap<String, List<Object>>) obj.get("idEtablissement");
                List<List<Object>> l = new ArrayList<List<Object>>(etab.values());
                Etablissement et = new Etablissement();
                et.setDevis_name(String.valueOf(l.get(25)));
//                et.setImg1(String.valueOf(l.get(36)));
//                et.setImg2(String.valueOf(l.get(37)));
//                et.setImg3(String.valueOf(l.get(38)));
//                et.setName(String.valueOf(l.get(39)));
                r.setEtablissement(et);

                r.setAunomde(obj.get("aunomde").toString());

                Map<String, Object> zz = (Map<String, Object>) obj.get("date");
                float ss = Float.parseFloat(zz.get("timestamp").toString());
                r.setDate(convertTime((long)ss*1000));
                r.setDescription(obj.get("description").toString());
                r.setNombre((int) Float.parseFloat(obj.get("nombre").toString()));

                listRes.add(r);

            }

        } catch (IOException ex) {
        }

        return listRes;

    }

    ArrayList<Reservation> listRes = new ArrayList<>();

    public ArrayList<Reservation> getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/slim/listReservationUser?id=" + String.valueOf(id));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReservationService ser = new ReservationService();
                listRes = ser.getListReservation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRes;
    }

    public Date convertTime(long time) {
        Calendar cal = new Calendar(time);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));

        return cal.getDate();

    }

}
