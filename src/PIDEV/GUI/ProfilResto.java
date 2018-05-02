/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Services.ProfilRestaurantService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Emir
 */
public class ProfilResto extends BaseForm {

    Label name;
    Label address;
    private Resources theme;

    public ProfilResto(Resources res, int id) throws IOException {
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("ContainerTop");
        
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
         theme = UIManager.initFirstTheme("/theme");

        ProfilRestaurantService pr = new ProfilRestaurantService();
        for (Etablissement e : pr.getList2(id)) {

            name = new Label();

            address = new Label();

            Container caddress = new Container(new BoxLayout(BoxLayout.X_AXIS));

            caddress.add(address);
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            caddress.getStyle().setBgColor(0x99DDD);
            caddress.getStyle().setBgTransparency(150);
            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer();
            img1.setImage(imgUrl);

            Image placeholder1 = Image.createImage(500, 170);
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
            URLImage imgUrl1 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImg1(), "http://localhost/PIDEV/web/uploads/images/" + e.getImg1());
            ImageViewer img1x = new ImageViewer(imgUrl1);

            URLImage imgUrl2 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImg2(), "http://localhost/PIDEV/web/uploads/images/" + e.getImg2());
            ImageViewer img2x = new ImageViewer(imgUrl2);

            URLImage imgUrl3 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImg3(), "http://localhost/PIDEV/web/uploads/images/" + e.getImg3());
            ImageViewer img3x = new ImageViewer(imgUrl3);

            GridLayout gr = new GridLayout(4, 4);
            Container amenties = new Container();
            amenties.setLayout(gr);
            Container amenties1 = new Container(BoxLayout.x());
            Container amenties2 = new Container(BoxLayout.x());
            Container amenties3 = new Container(BoxLayout.x());
            Container amenties4 = new Container(BoxLayout.x());

            CheckBox fumer = new CheckBox();
            Label fumerl = new Label("Fumer");
            fumer.setEnabled(false);
            fumer.setSelected(e.isFumer());
            fumerl.setUIID("Label3");
            CheckBox parking = new CheckBox();
            Label parkingl = new Label("Parking");
            parking.setEnabled(false);
            parking.setSelected(e.isParking());
            parkingl.setUIID("Label3");
            CheckBox cartecredit = new CheckBox();
            Label cartecreditl = new Label("Cartecredit");
            cartecredit.setEnabled(false);
            cartecredit.setSelected(e.isCartecredit());
            cartecreditl.setUIID("Label3");
            CheckBox chaiseroulante = new CheckBox();
            Label chaiseroulantel = new Label("Chaiseroulante");
            chaiseroulante.setEnabled(false);
            chaiseroulante.setSelected(e.isChaiseroulante());
            chaiseroulantel.setUIID("Label3");
            CheckBox terasse = new CheckBox();
            Label terassel = new Label("Terasse");
            terasse.setEnabled(false);
            terasse.setSelected(e.isTerasse());
            terassel.setUIID("Label3");
            CheckBox wifi = new CheckBox();
            Label wifil = new Label("Wifi");
            wifi.setEnabled(false);
            wifi.setSelected(e.isWifi());
            wifil.setUIID("Label3");
            CheckBox reservations = new CheckBox();
            Label reservationsl = new Label("Reservations");
            reservations.setEnabled(false);
            reservations.setSelected(e.isReservations());
            reservationsl.setUIID("Label3");
            CheckBox climatisation = new CheckBox();
            Label climatisationl = new Label("Climatisation");
            climatisation.setEnabled(false);
            climatisation.setSelected(e.isClimatisation());
            climatisationl.setUIID("Label3");
            
            Button review = new Button("Donner Avis");
            review.setUIID("Button");
            
            Button listreview = new Button("Liste des  Avis");
            listreview.setUIID("Button");
            Container rev = new Container(BoxLayout.x());
            rev.add(review);
            rev.add(listreview);
            Button reservation = new Button("Reserver");
            reservation.setUIID("Button");
            Container reservationCon= FlowLayout.encloseCenterMiddle(reservation);
            
           
review.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
      new AddReview(e,theme).show();
                }
            });
            Label amentieslabel = new Label("Amenties :");
            amentieslabel.setUIID("label4");
            Label horaires = new Label("Horaires :");
            horaires.setUIID("label4");
            Label lundisamedi = new Label("Lundi-Samedi " + e.getLundisamedio() + "-" + e.getLundisamedif());
            Label DimanchLabel = new Label("Dimanche " + e.getDimancheo() + "-" + e.getDimanchef());

            lundisamedi.setUIID("Label3");
            DimanchLabel.setUIID("Label3");
            amenties.add(fumerl);
            amenties.add(fumer);
            amenties.add(parkingl);
            amenties.add(parking);
            amenties.add(cartecreditl);
            amenties.add(cartecredit);
            amenties.add(chaiseroulantel);
            amenties.add(chaiseroulante);
            amenties.add(terassel);
            amenties.add(terasse);
            amenties.add(wifil);
            amenties.add(wifi);
            amenties.add(reservationsl);
            amenties.add(reservations);
            amenties.add(climatisationl);
            amenties.add(climatisation);

            amenties.add(amenties1);
            amenties.add(amenties2);
            amenties.add(amenties3);
            amenties.add(amenties4);

            Label galerielabel = new Label("Galerie :");
            galerielabel.setUIID("label4");
            Label title = new Label("Profil de " + e.getName());
            add(title);
            add(img1);
            add(rev);
            add(reservationCon);
            add(horaires);
            add(lundisamedi);
            add(DimanchLabel);
            add(amentieslabel);
            add(amenties);
            add(galerielabel);
            add(img1x);
            add(img2x);
            add(img3x);

            name.setText(e.getName());

            address.setText(e.getAddress());

//          f.getToolbar().addCommandToOverflowMenu("Back",theme.getImage("back.png") , new ActionListener() {
//                                    @Override
//                                    public void actionPerformed(ActionEvent evt) 
//                                    {
//                                        System.out.println("lol");
//                                        ListResto lr = new ListResto();
//                                        lr.ListResto();
//                                    }
//                                       });
//           
        }

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }
}
