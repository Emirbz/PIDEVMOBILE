/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.User;
import PIDEV.Services.ProfilRestaurantService;
import PIDEV.Services.ReviewService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
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
    private Resources theme2;

    public ProfilResto(Resources res, int id) throws IOException {
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(" Profil");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
         Form previous = getCurrentForm();
        tb.setBackCommand("", (e) -> {
            previous.showBack();
        });

       
         
         theme = UIManager.initFirstTheme("/theme");

        ProfilRestaurantService pr = new ProfilRestaurantService();
        for (Etablissement e : pr.getList2(id)) {
            
              Image img = res.getImage("profilebg.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image placeholder = Image.createImage(240, 120);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        String url = "http://localhost/PIDEV/web/devis/" + e.getDevis_name();
        String thumb = "http://localhost/PIDEV/web/devis/" + e.getDevis_name();
        URLImage thumbImage = URLImage.createToStorage(encImage, url, thumb, URLImage.RESIZE_SCALE_TO_FILL);
        ScaleImageButton btn = new ScaleImageButton(thumbImage);
        btn.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        btn.setUIID("PictureWhiteBackground");
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenter(
                                        btn
                        )
                )
        )));
            /////////////

            name = new Label();

            address = new Label();

            Container caddress = new Container(new BoxLayout(BoxLayout.X_AXIS));

            caddress.add(address);
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            caddress.getStyle().setBgColor(0x99DDD);
            caddress.getStyle().setBgTransparency(150);
//            Image placeholder = Image.createImage(500, 170);
//            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
//            ImageViewer img1 = new ImageViewer();
//            img1.setImage(imgUrl);

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
            User x =SignInForm.userCon;
            listreview.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                
                       new ListReview(e.getId(), e, theme).show();
                
                    
                 
           
                }
            });
            listreview.setUIID("Button");
            Container rev = new Container(BoxLayout.x());
            rev.add(review);
            rev.add(listreview);
review.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     if (x==null)
                    { ToastBar.showMessage("Veuillez Connecter pour ajouter un FeedBack", FontImage.MATERIAL_INFO);}
                     else
                     { new AddReview(e,theme).show();}
                }
            });
            Label amentieslabel = new Label("Amenties :");
             Style amentiesi = new Style(amentieslabel.getUnselectedStyle());
            amentiesi.setFgColor(0x73879c);
            FontImage amentiesix = FontImage.createMaterial(FontImage.MATERIAL_SETTINGS, amentiesi);
            amentieslabel.setIcon(amentiesix);
            amentieslabel.setUIID("label4");
           
            Label horaires = new Label("Horaires :");
             Style horairei = new Style(horaires.getUnselectedStyle());
            horairei.setFgColor(0x73879c);
            FontImage horaireix = FontImage.createMaterial(FontImage.MATERIAL_TIMER, horairei);
            horaires.setIcon(horaireix);
            horaires.setUIID("label4");
            Label lundisamedi = new Label("Lundi-Samedi " + e.getLundisamedio() + "-" + e.getLundisamedif());
            Label DimanchLabel = new Label("Dimanche " + e.getDimancheo() + "-" + e.getDimanchef());

            lundisamedi.setUIID("Label3");
            DimanchLabel.setUIID("Label3");
            
             Label feedback = new Label("FeedBack :");
            feedback.setUIID("label4");
              Style feedbacki = new Style(feedback.getUnselectedStyle());
            feedbacki.setFgColor(0x73879c);
            FontImage feedbackix = FontImage.createMaterial(FontImage.MATERIAL_STAR, feedbacki);
            feedback.setIcon(feedbackix);
            ReviewService rs = new ReviewService();
            
            Label moy = new Label("Moyenne de "+rs.getNbrRev(e.getId())+" votes ");
            moy.setUIID("Label3");
            
            String qualiteXX = String.valueOf(e.getMoyqualite());
            String ServiceXX = String.valueOf(e.getMoyservice());

            Slider qualite = createStarRankSlider(Integer.parseInt(qualiteXX.substring(0, 1)));
            Slider service = createStarRankSlider(Integer.parseInt(ServiceXX.substring(0, 1)));
             Container qualitec = new Container(BoxLayout.x());
           Container servicec = new Container(BoxLayout.x());
           Label qualitel = new Label("Qualite :");qualitel.setUIID("Label3");
           Label servicel = new Label("Service :");servicel.setUIID("Label3");
           qualitec.add(qualitel);qualitec.add(qualite);
           servicec.add(servicel);servicec.add(service);
            
           
                 Label map = new Label("Location :");
             Style mapi = new Style(map.getUnselectedStyle());
            mapi.setFgColor(0x73879c);
            FontImage mapix = FontImage.createMaterial(FontImage.MATERIAL_TIMER, mapi);
            map.setIcon(mapix);
            map.setUIID("label4");
            FloatingActionButton mapresto = FloatingActionButton.createFAB(FontImage.MATERIAL_PLACE);
            mapresto.setUIID("label4");
            mapresto.addActionListener((evt) -> {
            ProfilMap pm = new ProfilMap();
            pm.start(e);
            });
            
            
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
            
            
            
        
            add(rev);
            add(feedback);
            add(moy);
            add(qualitec);
            add(servicec);
            add(horaires);
            add(lundisamedi);
            add(DimanchLabel);
            add(amentieslabel);
            add(amenties);
            add(map);
            add(mapresto);
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
    private Slider createStarRankSlider(int value) {
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(convertToPixels(2, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        Slider starRank = new Slider() {
            public void refreshTheme(boolean merge) {
                // special case when changing the theme while the dialog is showing
                initStarRankStyle(getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(getSliderFullUnselectedStyle(), fullStar);
            }
        };

        starRank.setEditable(false);
       
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
         starRank.setProgress(value);
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    private void initStarRankStyle(Sty