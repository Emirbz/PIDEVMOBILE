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
import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
//import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
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
    
    public SignInForm(Resources res) throws IOException {
        
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
        Button facebook = new Button("Facebook");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        facebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String clientId = "327713687757081";
                String redirectURI = "https://www.google.com/";
                String clientSecret = "e9457ed6c94a31675d4156a37c3fc090";
                Login fb = FacebookConnect.getInstance();
                
                fb.setClientId(clientId);
                
                fb.setRedirectURI(redirectURI);
                
                fb.setClientSecret(clientSecret);
                fb.setCallback(new LoginCallback() {
                    
                    @Override
                    
                    public void loginFailed(String errorMessage) {
                        
                        System.out.println("failed");
                        Storage.getInstance().writeObject("token", "EAACEdEose0cBABdnMw57cIm4BZBfj49JT45uAfshPJD3l4inlOWzyQuq9GfreuIEYxiZB4POSvyIO4qftdCHObikqCunMTPslzyhjuC4aT4NpWUatrw1QjLPY9AqSb1wjDvVZARDWTzrNXLCvYEuohvL69GYK9sy5pyvd7cObQSFWGFmj1Y80UGYccPMfkRHJZAZBhEBOkAZDZD");
                        
                    }
                    
                    @Override
                    public void loginSuccessful() {
                        System.out.println("Done");
                        String token = fb.getAccessToken().getToken();
                        Storage.getInstance().writeObject("token", token);
                        
                        NewsfeedForm a = new NewsfeedForm(res);
                        a.show();
                    }
                    
                });
                if (!fb.isUserLoggedIn()) {
                    
                    fb.doLogin();
                    /*       */
                } else {
                    try {
                        //get the token and now you can query the facebook API
                        //      String token = fb.getAccessToken().getToken();
                        //     Storage.getInstance().writeObject("token", token);
                        String token = (String) Storage.getInstance().readObject("token");
                        FaceBookAccess.setToken(token);
                        final com.codename1.facebook.User me = new com.codename1.facebook.User();
                        FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                String miNombre = me.getName();
                                String add = me.getEmail();
                                System.out.println(add);
                                getContentPane().removeAll();
                                
                                add(new Label(miNombre));
                                
                                Button buttonLogout = new Button("Logout");
                                buttonLogout.addActionListener((e) -> {
                                    fb.doLogout();
                                    //showIfNotLoggedIn(form);
                                    
                                });
                                
                                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                                URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg",
                                        "https://graph.facebook.com/v2.11/me/picture?access_token=" + token);
                                background.fetch();
                                ScaleImageLabel myPic = new ScaleImageLabel();
                                myPic.setIcon(background);
                                
                                add(myPic);
                                add(buttonLogout);
                                
                                revalidate();
                                //form.show();
                            }
                            
                        }); // showIfNotLoggedIn(form);
                        NewsfeedForm a = new NewsfeedForm(res);
                        a.show();
                    } catch (IOException ex) {
                    
                    }
                    
                }
            }
        });
        Container content = BoxLayout.encloseY(
                username,
                createLineSeparator(),
                password,
                createLineSeparator(),
                facebook,
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
                req.setUrl("http://localhost/PIDEV/web/app_dev.php/a/js?username=" + username.getText() + "&password=" + password.getText());
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        userCon = getUser(new String(req.getResponseData()));
                        System.out.println("+++++++++++++++++" + userCon);
                        //  EventsList e = new EventsList(theme);
                        //  e.start();
                        //   Profil profil = new Profil(theme);
                        new NewsfeedForm(res).show();
                        
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
                    u.setDevisName(obj.get("devisName").toString());
                    u.setUsername(obj.get("username").toString());
                    u.setUsernameCanonical(obj.get("usernameCanonical").toString());
                    u.setEmailCanonical(obj.get("emailCanonical").toString());
                    u.setAddress(obj.get("address").toString());
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
