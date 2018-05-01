/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.User;
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
 * @author ons
 */
public class UserService {
        
    
    public User login(String json){
        ArrayList<User> listUser = new ArrayList<>();
       
          try {
              System.out.println("1");
          System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> userLogged = j.parseJSON(new CharArrayReader(json.toCharArray()));
              
           System.out.println("2  "+ userLogged);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) userLogged.get("User");
              
            for (Map<String, Object> obj : list) {
                User user = new User();

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("id "+ id);
                user.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                user.setName(obj.get("name").toString());
                user.setSurname(obj.get("surname").toString());
                System.out.println(user.toString());
                //Main.user= user;
                listUser.add(user);
               }
            
          }catch (IOException ex) {
   
          }
          return listUser.get(0);
          
          
    }
    User users;
    public User loginUser(String pseudo, String password){
      
        
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/users/js?username="+pseudo+"&password=" +password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                byte[] data = (byte[]) evt.getMetaData();
//                String s = new String(data);
                UserService ser = new UserService();
                users = ser.login(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }
    
    
    
}
