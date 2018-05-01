/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;


import PIDEV.Services.ListEtablissementService;
import PIDEV.Entities.Etablissement;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Emir
 */
public class ListResto {

    Form f;
    Label name;
    Label address;
    Container cx = new Container(BoxLayout.y());
    

    public Container ListResto() {
        f = new Form("Liste Restaurant", BoxLayout.y());
        Container c1 = null;
        ListEtablissementService lr = new ListEtablissementService();
        for (Etablissement e : lr.getList2()) {

            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer(imgUrl);

            name = new Label();
            address = new Label();
            
              Button details= new Button("Profil de "+e.getName());
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
                    

                }
            });

            Container cnom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container caddress = new Container(new BoxLayout(BoxLayout.X_AXIS));

            cnom.add(name);
            cnom.getStyle().setBgColor(0x99CCCC);
            cnom.getStyle().setBgTransparency(150);
           
            caddress.add(address);
            caddress.getStyle().setBgColor(0xFF214F);
            caddress.getStyle().setBgTransparency(255);

           c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c1.getStyle().setPaddingBottom(20);

            

            c1.add(cnom);
            c1.add(caddress);
            c1.add(img1);
            c1.add(details);
            
            
            

            name.setText(e.getName());
            address.setText(e.getAddress());
          cx.add(c1);

        }
        return cx;
        
        
          
    }

}
