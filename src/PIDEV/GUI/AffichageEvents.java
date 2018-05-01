/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Events;
import PIDEV.Services.Eventsservices;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;

import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;

import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Emir
 */
public class AffichageEvents {

    Form f;
    Label name;
    Label adresse;
    Label description;
    Label dateEvenement;
    Label numTel;

    Label lb;
    Container cx = new Container(BoxLayout.y());
    private Resources theme;


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

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    public Container ListEvents() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Liste Evenements", BoxLayout.x());
        Container c1 = null;
        Eventsservices Es = new Eventsservices();
        for (Events e : Es.getLisEvent1()) {

            Image placeholder = Image.createImage(130, 100);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer(imgUrl);

            name = new Label();
            adresse = new Label();
            description = new Label();
            dateEvenement = new Label();
            numTel = new Label();
            Date d = e.getDateEvenement();
            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(sfd.format(d));
            int fontSize = Display.getInstance().convertToPixels(3);

            // requires Handlee-Regular.ttf in the src folder root!
            Font ttfFont = Font.createTrueTypeFont("Poppins", "Poppins-Bold.ttf").
                    derive(fontSize, Font.STYLE_PLAIN);
            Container cnom = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            name = new Label(e.getName());
            name.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        ProfilEvent pe=new ProfilEvent(theme, e.getId());
                        pe.getF().show();
                    } catch (IOException ex) {

                    }

                }
            });

            name.setUIID("Label");
            adresse.setUIID("Label");
            description.setUIID("Label");
            numTel.setUIID("Label");
            dateEvenement.setUIID("Label");
            name.setText(e.getName());
            adresse.setText(e.getAdresse());
            description.setText(e.getDescription());
            dateEvenement.setText(e.getDateEvenement().toString());
            cnom.add(name);
            cnom.add(adresse);
            cnom.add(description);
            cnom.add(numTel);
            cnom.add(dateEvenement);

            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c1.getStyle().setPaddingBottom(20);

            c1.add(img1);

            c1.add(cnom);

//            c1.add(details);
            cx.add(c1);

        }
        return cx;

    }
}
