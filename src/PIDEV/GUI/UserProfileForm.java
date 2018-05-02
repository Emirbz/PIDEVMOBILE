/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import com.cloudinary.Cloudinary;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.URL;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
//import PIDEV.GUI.CleanModern;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.*;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.table.TableLayout;
import java.io.OutputStream;

import java.util.Map;

/**
 *
 * @author ons
 */
public class UserProfileForm extends BaseForm {

    public UserProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("ContainerTop");

        getContentPane().setScrollVisible(false);
       
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Image img = res.getImage("profilebg.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Image placeholder = Image.createImage(150, 150);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//        Image imgUrl = URLImage.createToStorage(encImage, "Large_"+"http://localhost/PIDEV/web/devis/" + SignInForm.userCon.getDevis_name(), "http://localhost/PIDEV/web/devis/" + SignInForm.userCon.getDevis_name(),URLImage.RESIZE_FAIL);

        String url = "http://localhost/PIDEV/web/devis/" + SignInForm.userCon.getDevis_name();
        String thumb = "http://localhost/PIDEV/web/devis/" + SignInForm.userCon.getDevis_name();
        URLImage thumbImage = URLImage.createToStorage(encImage, url, thumb, URLImage.RESIZE_SCALE_TO_FILL);
        ScaleImageButton btn = new ScaleImageButton(thumbImage);
        btn.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        btn.setUIID("ButtonImage");

//        Button resB =new Button("Reservation");
//        Button favB = new Button("Favoris");
//        resB.setUIID("ButtonMenu");
//        favB.setUIID("ButtonMenu");
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenterBottom(
                                        btn
                                ))
                ))
        );
        ButtonGroup barGroup = new ButtonGroup();
        Button resB = RadioButton.createToggle("Reservation", barGroup);
        resB.setUIID("SelectBar");
        Button favB = RadioButton.createToggle("Favoris", barGroup);
        favB.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, resB, favB),
                FlowLayout.encloseCenterMiddle(arrow)
        ));
        resB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    ListReservationUser news = new ListReservationUser(res);
                    news.show();
                } catch (IOException ex) {

                }
            }
        });
       

        Label username = new Label(SignInForm.userCon.getUsername_canonical());
        username.setUIID("LabelUsername");

        Label name = new Label("Name : ");
        name.setUIID("LabelInfoProfil");
        Label nameContent = new Label(SignInForm.userCon.getName().toUpperCase().charAt(0)+SignInForm.userCon.getName().substring(1, SignInForm.userCon.getName().length())+" "+SignInForm.userCon.getSurname().toUpperCase().charAt(0)+SignInForm.userCon.getSurname().substring(1, SignInForm.userCon.getSurname().length()));
        nameContent.setUIID("LabelContentProfile");

       
        Label email = new Label("Email : ");
        email.setUIID("LabelInfoProfil");
        Label emailContent = new Label(SignInForm.userCon.getEmail());
        emailContent.setUIID("LabelContentProfile");

        Label phone = new Label("Phone : ");
        phone.setUIID("LabelInfoProfil");
        Label phoneContent = new Label(SignInForm.userCon.getPhone());
        phoneContent.setUIID("LabelContentProfile");

        Label address = new Label("Address : ");
        address.setUIID("LabelInfoProfil");
        Label addressContent = new Label(SignInForm.userCon.getAddress());
        addressContent.setUIID("LabelContentProfile");
        
        Container tl = BoxLayout.encloseY(name,nameContent,email,emailContent,phone,phoneContent,address,addressContent);
      //  Container infos= FlowLayout.encloseCenterMiddle(tl);
   
        add(tl);

    }

}
