/*
 * Copyright (c) 2014, Codename One LTD. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Services.ListEtablissementService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class ProfilMap extends BaseForm {

    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    MapObject sydney;
    public ProfilMap(Etablissement ex,Resources res) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Adresse de "+ex.getName());
        getContentPane().setScrollVisible(false);

       
           super.addSideMenu(res);
         Form previous = getCurrentForm();
        tb.setBackCommand("", (e) -> {
            previous.showBack();
        });
        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form();
        
        hi.setLayout(new BorderLayout());
        hi.setUIID("Button");
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        
       
        cnt.setCameraPosition(new Coord(-26.1486233, 28.67401229999996));//this breaks the code //because the Google map is not loaded yet
        
        int maxZoom = cnt.getMaxZoom();
        
        System.out.println("Max zoom is "+maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(36.800399 ,10.186619500000006));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        

        

        

        
        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_PLACE);
        nextForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
 
         cnt.zoom(new Coord(ex.getLatitude() ,ex.getLongitude()),14);
         
            }
        });
          FloatingActionButton nextForm1= FloatingActionButton.createFAB(FontImage.MATERIAL_PLACE);
        nextForm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
 
         cnt.zoom(new Coord(ex.getLatitude() ,ex.getLongitude()),14);
         
            }
        });

       
        
        
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.north(nextForm.bindFabToContainer(cnt))
                
        );
        
//        hi.add(BorderLayout.CENTER, root);
  

     
         cnt.addMarker(EncodedImage.createFromImage(markerImg, false),new Coord(ex.getLatitude(),ex.getLongitude()), "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage(ex.getName()+" \n"+ex.getAddress() +"\n"
                            +"Lundi à Samedi: "+ex.getLundisamedio()+"-"+ex.getLundisamedif()
                            +"\n"+"Dimanche "+ex.getDimancheo()+"-"+ex.getDimanchef(), FontImage.MATERIAL_PLACE);
                }
            });
         
        add(root);
        add(nextForm1);
//        hi.show();
        
    }
    boolean tapDisabled = false;

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

    

}
