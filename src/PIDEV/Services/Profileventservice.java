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
 * @author      dahem
 */
public class Profileventservice {

    public ArrayList<Events> getListTask(String json) throws ParseException {

        ArrayList<Events> listt = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Etab = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Etab.get("root");

            for (Map<String, Object> obj : list) {
                Events e = new Events();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);

                e.setName(obj.get("name").toString());

                e.setAdresse(obj.get("adresse").toString());
                e.setImage(obj.get("image").toString());
                e.setImage1(obj.get("image1").toString());
                e.setImage2(obj.get("image2").toString());
                e.setDevis_name(obj.get("devisName").toString());
                e.setDescription(obj.get("description").toString());
                e.setNbrplace(Integer.parseInt(obj.get("nbrplace").toString().substring(0,obj.get("nbrplace").toString().lastIndexOf("."))));
                //e.setAdressemail(obj.get("adressemail").toString());
                e.setNumTel(Integer.parseInt(obj.get("numTel").toString()));
                e.setAdressefacebook(obj.get("adressefacebook").toString());
                e.setAdressetwitter(obj.get("adressetwitter").toString());
                e.setFumer(Boolean.parseBoolean(obj.get("fumer").toString()));
                
                
                Map<String,Object> d=(Map<String,Object>)obj.get("dateEvenement");
                float t=Float.parseFloat(d.get("timestamp").toString());
                long b=(long)t;
                Date dt=new Date(b*1000);
                e.setDateEvenement(dt);

                listt.add(e);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return listt;

    }

    ArrayList<Events> listEtabs = new ArrayList<>();

    public ArrayList<Events> getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/oussama/event_profile1?id=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Profileventservice ser = new Profileventservice();
                    listEtabs = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtabs;
    }

}
