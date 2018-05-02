/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Reservation;
import PIDEV.Services.ListEtablissementService;
import PIDEV.Services.ReservationService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author ons
 */
public class ListReservationUser extends BaseForm {

    Form f;
    Label aunomde;
    Label nombre;
    Label description;
    Label date;
    Container c = new Container(BoxLayout.y());

    public ListReservationUser(Resources res) throws IOException {
//        f = new Form("Liste Reservation", BoxLayout.y());
//        Container c1 = null;

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();
        Label spacer1 = new Label();

        //  spacer1.getUnselectedStyle().setFont(Poppins);
        addTab(swipe, res.getImage("profilebg.jpg"), spacer1, "Vos reservations");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ReservationService rs = new ReservationService();
        for (Reservation r : rs.getList2(SignInForm.userCon.getId())) {
            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name(), "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name());
            // ImageViewer img1 = new ImageViewer(imgUrl);
            System.out.println(r.getEtablissement().getDevis_name());
            aunomde = new Label(r.getAunomde());
            nombre = new Label();
            description = new Label();
            date = new Label();
            //          addButton(imgUrl, r.getAunomde(), r.getDescription(), String.valueOf(r.getNombre()));

        }
        add(listRes());

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
//        Label likes = new Label(likesStr);
//        Style heartStyle = new Style(likes.getUnselectedStyle());
//        heartStyle.setFgColor(0xff2d55);
//        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
//        likes.setIcon(heartImage);
//        likes.setTextPosition(RIGHT);

//        Label comments = new Label(commentsStr);
//        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
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

    public Container listRes() throws IOException {
        
   
   //     Label bokk = new Label("bokkk");
        ReservationService rs = new ReservationService();
        for (Reservation r : rs.getList2(SignInForm.userCon.getId())) {
            System.out.println(r.getAunomde());

            Image placeholder = Image.createImage(150, 150);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name(), "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name(),URLImage.createMaskAdapter(placeholder));


            ImageViewer img1 = new ImageViewer(imgUrl);
            
           // URLImage.ImageAdapter ada = URLImage.createMaskAdapter(roundMask);
          //  URLImage img = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name(), "http://localhost/PIDEV/web/devis/" + r.getEtablissement().getDevis_name(), ada);
            //img.fetch();
        //    ImageViewer img1=new ImageViewer(img);
            aunomde = new Label("Au Nom de : "+r.getAunomde());
            aunomde.setUIID("LabelInfos");
            nombre = new Label("Nombre de places : "+ String.valueOf(r.getNombre()));
            nombre.setUIID("LabelInfos");
            description = new Label("Type : " +r.getDescription());
            description.setUIID("LabelInfos");
            //date = new Label();
            // Container c= BoxLayout.encloseY(aunomde, description, nombre);

            Container image = BoxLayout.encloseX(img1);
            Container donnee = new Container(BoxLayout.y());
            donnee.add(aunomde);
            donnee.add(description);
            donnee.add(nombre);
            Container flow= FlowLayout.encloseCenterMiddle(donnee);
            Container cnt = new Container(BoxLayout.x());
            cnt.add(image);
            cnt.add(flow);
            cnt.setUIID("Container");

            c.add(cnt);

        }

        return c;
    }

    private void addButton(Image img, String title, String description, String nombre) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        Label desLabel = new Label("Occasion : " + description);
        Style s = new Style(desLabel.getUnselectedStyle());
        s.setFgColor(0xff214f);
        Label nombreLabel = new Label("Nombre de places : " + nombre);
        Style s1 = new Style(nombreLabel.getUnselectedStyle());
        s1.setFgColor(0xff214f);
//        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
//        likes.setTextPosition(RIGHT);
//        if (!liked) { 
//            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//        } else {
//            Style s = new Style(likes.getUnselectedStyle());
//            s.setFgColor(0xff214f);
//            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//            likes.setIcon(heartImage);
//        }
//        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        //                        BoxLayout.encloseX(likes, comments)
                        desLabel,
                        nombreLabel
                ));
        //   c.add(cnt);

        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

}
