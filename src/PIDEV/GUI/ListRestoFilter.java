/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Services.ListEtablissementService;
import PIDEV.Entities.Etablissement;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;

import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;

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


/**
 *
 * @author Emir
 */
public class ListRestoFilter extends BaseForm {

    Form f;
    Label name;

    Label souscat;
    Label categorie;
    Container cx = new Container(BoxLayout.y());
    private Resources theme;

    public  ListRestoFilter(Resources res) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Reviews");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
          FloatingActionButton back = FloatingActionButton.createFAB(FontImage.MATERIAL_BACKUP);
           Form previous = getCurrentForm();
        tb.setBackCommand("", (e) -> {
            previous.showBack();
        });
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Liste Restaurant", BoxLayout.x());
        Container c1 = null;
        ListEtablissementService lr = new ListEtablissementService();
        for (Etablissement e : lr.getList2Rest()) {

            Image placeholder = Image.createImage(130, 110);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer(imgUrl);

            name = new Label();

            souscat = new Label();
            categorie= new Label();
            categorie.setText(e.getCategorie());
            categorie.setUIID("Label2");
             Style caticon = new Style(souscat.getUnselectedStyle());
            caticon.setFgColor(0x73879c);
            FontImage caticonx = FontImage.createMaterial(FontImage.MATERIAL_HOTEL, caticon);
            categorie.setIcon(caticonx);

           
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
                        new ProfilResto(theme,e.getId()).show();
                    } catch (IOException ex) {
                       
                    }

                }
            });

            name.setUIID("Label");

            souscat = new Label(e.getSouscat().toString());
            Style souscaticon = new Style(souscat.getUnselectedStyle());
            souscaticon.setFgColor(0x73879c);
            FontImage souscaticonx = FontImage.createMaterial(FontImage.MATERIAL_HOME, souscaticon);
            souscat.setIcon(souscaticonx);
            souscat.setUIID("Label2");

            String qualiteXX = String.valueOf(e.getMoyqualite());

            Slider qualite = createStarRankSlider(Integer.parseInt(qualiteXX.substring(0, 1)));
            System.out.println(Integer.parseInt(qualiteXX.substring(0, 1)));
            cnom.getStyle().setPaddingBottom(2);

            cnom.add(name);
            cnom.add(categorie);
            cnom.add(souscat);
            cnom.add(qualite);
        

//           cnom.add(servicec);
            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c1.getStyle().setPaddingBottom(20);

            c1.add(img1);

            c1.add(cnom);

//            c1.add(details);
            add(c1);

        }
        

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

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
 public Container ListRestoTrie(String sc) {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Liste Restaurant", BoxLayout.x());
        Container c1 = null;
        ListEtablissementService lr = new ListEtablissementService();
        for (Etablissement e : lr.getList2Cat(sc)) {
            System.out.println(e.getName());

            Image placeholder = Image.createImage(130, 100);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer(imgUrl);

            name = new Label();

            souscat = new Label();

           
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
                        new ProfilResto(theme,e.getId()).show();
                    } catch (IOException ex) {
                       
                    }

                }
            });

            name.setUIID("Label");

            souscat = new Label(e.getSouscat().toString());
            Style souscaticon = new Style(souscat.getUnselectedStyle());
            souscaticon.setFgColor(0x73879c);
            FontImage souscaticonx = FontImage.createMaterial(FontImage.MATERIAL_HOME, souscaticon);
            souscat.setIcon(souscaticonx);
            souscat.setUIID("Label2");

            String qualiteXX = String.valueOf(e.getMoyqualite());

            Slider qualite = createStarRankSlider(Integer.parseInt(qualiteXX.substring(0, 1)));
            System.out.println(Integer.parseInt(qualiteXX.substring(0, 1)));
            cnom.getStyle().setPaddingBottom(2);

            cnom.add(name);
            cnom.add(souscat);
            cnom.add(qualite);
        

//           cnom.add(servicec);
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
