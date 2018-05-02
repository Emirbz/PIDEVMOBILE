/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package PIDEV.GUI;

import PIDEV.Entities.User;
//import PIDEV.Services.UserService;
import com.codename1.components.FloatingHint;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
//import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public static User userCon;

    public SignInForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("logo-v1.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);

        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);

        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        
        
        Container content = BoxLayout.encloseY(
                username,
                createLineSeparator(),
                password,
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                //AddEvents events = new AddEvents();
                System.out.println("Username : " + username.getText() + "Password : " + password.getText());

                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/PIDEV/web/app_dev.php/users/js?username=" + username.getText() + "&password=" + password.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        userCon = getUser(new String(req.getResponseData()));
                        System.out.println("+++++++++++++++++" + userCon);
                        //  EventsList e = new EventsList(theme);
                        //  e.start();
                        //   Profil profil = new Profil(theme);
                        UserProfileForm news = new UserProfileForm(res);
                        news.show();

                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });
    }

    public User getUser(String json) {
        ArrayList<User> listEtudiants = new ArrayList<>();

        try {
            if (!json.equals("null")) {
                JSONParser j = new JSONParser();

                Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

                for (Map<String, Object> obj : list) {
                    User u = new User();
                    u.setId(Integer.parseInt(obj.get("id").toString().substring(0, 1)));
                    u.setName(obj.get("name").toString());
                    u.setSurname(obj.get("surname").toString());
                    u.setEmail(obj.get("email").toString());
                    u.setPassword(obj.get("password").toString());
                    u.setDevis_name(obj.get("devisName").toString());
                    u.setUsername(obj.get("username").toString());
                    
                    listEtudiants.add(u);

                }
            } else {
                Dialog.show("Error", "Wrong pasword / username", "Ok", "Cancel");
            }

        } catch (IOException ex) {
        }

        return listEtudiants.get(0);

    }

}
