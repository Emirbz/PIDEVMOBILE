/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import PIDEV.Entities.Deal;
import PIDEV.Entities.Note;
import PIDEV.Entities.Reclamation;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Skan
 */
public class DealDetails {

    Resources res = UIManager.initFirstTheme("/theme");

    void showPropertyDetails(Deal d) {
        final Form propertyDetails = new Form("Property Details", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = new Toolbar(true);
        propertyDetails.setToolbar(tb);
        propertyDetails.getTitleArea().setUIID("Container");

        Image img = res.getImage("profilebg.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image placeholder = Image.createImage(240, 120);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        String url = "http://localhost/PIDEV/web/devis/" + d.getDevisName();
        String thumb = "http://localhost/PIDEV/web/devis/" + d.getDevisName();
        URLImage thumbImage = URLImage.createToStorage(encImage, url, thumb, URLImage.RESIZE_SCALE_TO_FILL);
        ScaleImageButton btn = new ScaleImageButton(thumbImage);
        btn.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        btn.setUIID("PictureWhiteBackground");
        propertyDetails.add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenter(
                                        btn
                                )
                        )
                )));

        propertyDetails.setTitle(d.getNom());
        propertyDetails.getContentPane().setScrollVisible(false);
        String price_formatted = d.getNewprix().toString();
        String summary = d.getDescription();
        String adresse = d.getAdresse();
        String region = d.getRegion();
        Slider rat = createStarRankSlider(d.getRating());
        Container ratc = new Container(BoxLayout.y());
        Container ra = new Container(BoxLayout.x());
        Label ratlabel = new Label("Rating:");
        Button rateme = new Button("Evaluer");
        ra.add(ratlabel);
        ra.add(rat);
        ratc.add(ra);
        ratc.add(rateme);
        Button rec = new Button("Réclamer");
        rateme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                Message m=new Message("test");
//                Display.getInstance().sendMessage(new String[] {"skander.chamakhi@gmail.com"}, "subject", m);

                InteractionDialog dlg = new InteractionDialog("Evaluer");
                dlg.setLayout(new BorderLayout());
                Container c = new Container();
                dlg.add(BorderLayout.CENTER, c);
                Container de = new Container(BoxLayout.x());
                Slider ttt = createStarRankSlider();
                Button close = new Button("Close");
                Button reclam = new Button("Evaluer");
                close.setWidth(150);
                reclam.setWidth(150);
                de.add(close);
                de.add(reclam);
                c.add(FlowLayout.encloseCenter(ttt));
                dlg.add(BorderLayout.SOUTH, de);
                close.addActionListener((ee) -> dlg.dispose());
                reclam.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Note n = new Note();
                        n.setIddeal(d.getId());
                        n.setIduser(4);
                        n.setRating(ttt.getProgress() / 2);
                        Addrating ar = new Addrating();
                        ar.ajoutTask(n);
                        dlg.dispose();
                        ToastBar.showMessage("Votre évaluation est enregistré", FontImage.MATERIAL_STAR);
                    }
                });
                dlg.show(30, 30, 30, 30);
            }

        });

        rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                Message m=new Message("test");
//                Display.getInstance().sendMessage(new String[] {"skander.chamakhi@gmail.com"}, "subject", m);

                InteractionDialog dlg = new InteractionDialog("Réclamer");
                dlg.setLayout(new BorderLayout());
                Container c = new Container();
                TextField t = new TextField("", "Contenu");
                c.add(t);
                dlg.add(BorderLayout.CENTER, c);
                Container de = new Container();
                Button close = new Button("Close");
                Button reclam = new Button("Réclamer");
                de.add(close);
                de.add(reclam);
                close.addActionListener((ee) -> dlg.dispose());
                dlg.addComponent(BorderLayout.SOUTH, de);
                reclam.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Reclamation r = new Reclamation();
                        r.setIdobj(d.getId());
                        r.setTypeobj(d.getClass().getName().substring(15, d.getClass().getName().length()));
                        r.setContenu(t.getText());
                        Addreclamation ad = new Addreclamation();
                        ad.add(r);
                        dlg.dispose();
                        ToastBar.showMessage("Votre réclamation sera traité par l'admin", FontImage.MATERIAL_MESSAGE);
                    }
                });
                dlg.show(30, 30, 30, 30);
            }

        });
        Container ccc=new Container(BoxLayout.x());
        Label ss=new Label("Ancien Prix :");
        ss.setUIID("SecondaryTitle");
        Label old = new Label(String.valueOf(d.getOldprix()));
        old.setUIID("strikedtext");
        old.getStyle().setFgColor(0xFFFF0000);
        ccc.add(ss);
        ccc.add(old);
        propertyDetails.add(ccc).
                add(new Label("Prix : " + price_formatted, "SecondaryTitle")).
                add("Adresse :\n" + adresse).
                add(ratc).
                add("Région : \n" + region).
                add("Description : \n" + summary).
                add(rec);
        Form previous = getCurrentForm();
        propertyDetails.getToolbar().setBackCommand("", (e) -> {
            previous.showBack();
        });
        propertyDetails.show();
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
        starRank.setProgress(value);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(convertToPixels(5, true), Font.STYLE_PLAIN);
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
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
