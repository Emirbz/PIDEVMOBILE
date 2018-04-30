/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Events;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dahem
 */
public class Eventsservices {

    public ArrayList<Events> getListEvent(String json) throws ParseException {

        ArrayList<Events> ev = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            for (Map<String, Object> obj : list) {
                Events e = new Events();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setName(obj.get("name").toString());
                e.setDevis_name(obj.get("devisName").toString());
                e.setDescription(obj.get("description").toString());
                System.out.println(obj.get("dateEvenement").toString());
                Map<String,Object> d=(Map<String,Object>)obj.get("dateEvenement");
                float t=Float.parseFloat(d.get("timestamp").toString());
                long b=(long)t;
                Date dt=new Date(b*1000);
                e.setDateEvenement(dt);
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'");
//                Date dateEvenement = format.parse(obj.get("dateEvenement").toString());
//                e.setDateEvenement(dateEvenement);
//                System.out.println(dateEvenement);
                ev.add(e);

            }

        } catch (IOException ex) {
        }

        return ev;

    }

    ArrayList<Events> listEvents = new ArrayList<>();

    public ArrayList<Events> getLisEvent1() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/oussama/afficher_events1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Eventsservices ser = new Eventsservices();
                    listEvents = ser.getListEvent(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }

}
