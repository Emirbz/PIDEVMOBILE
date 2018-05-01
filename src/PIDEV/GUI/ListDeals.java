/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Deal;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import PIDEV.Services.DealService;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Skander
 */
public class ListDeals {

    Form f;
    Label lb;
    Label lb2;
    Resources theme = UIManager.initFirstTheme("/theme");

//    public Container ListDeals() {
//        Container c = null;
//        DealService cs = new DealService();
//        for (int i = 0; i < cs.getListDeals2().size(); i++) {
//            Deal t = cs.getListDeals2().get(i);
//            Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//            Image placeholder = Image.createImage(300, 100);
//            ImageViewer img = new ImageViewer();
//            EncodedImage eci = EncodedImage.createFromImage(placeholder, false);
//            Image image = URLImage.createToStorage(eci, "http://localhost/pidev/web/devis/" + t.getDevisName(),
//                    "http://localhost/pidev/web/devis/" + t.getDevisName());
//            Label l3 = new Label(t.getNom());
//            img.setImage(image);
//            c4.add(img);
//            c4.add(l3);
//            l3.addPointerPressedListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    Form f2 = new Form(BoxLayout.y());
//                    f2.setTitle(t.getNom());
//                    Image placeholder = Image.createImage(300, 100);
//                    ImageViewer img = new ImageViewer();
//                    EncodedImage eci = EncodedImage.createFromImage(placeholder, false);
//                    Image image = URLImage.createToStorage(eci, "http://localhost/pidev/web/devis/" + t.getDevisName(),
//                            "http://localhost/pidev/web/devis/" + t.getDevisName());
//                    Label l3 = new Label(t.getNom());
//                    img.setImage(image);
//                    Container r = new Container();
//                    Button bt = new Button("rate me");
//                    for (int i = 0; i < t.getRating(); i++) {
//                        ImageViewer rating = new ImageViewer(theme.getImage("star1.png"));
//
//                        r.add(rating);
//                    }
//                    bt.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//                            Dialog s = new Dialog("Evaluation");
//                            ImageViewer rat1 = new ImageViewer(theme.getImage("star2.png"));
//                            ImageViewer rat2 = new ImageViewer(theme.getImage("star2.png"));
//                            ImageViewer rat3 = new ImageViewer(theme.getImage("star2.png"));
//                            ImageViewer rat4 = new ImageViewer(theme.getImage("star2.png"));
//                            ImageViewer rat5 = new ImageViewer(theme.getImage("star2.png"));
//                            s.add(rat1);
//                            s.add(rat2);
//                            s.add(rat3);
//                            s.add(rat4);
//                            s.add(rat5);
//                            rat1.addPointerPressedListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent evt) {
//                                    System.out.println("sss");
//                                }
//                            });
//                            Button close = new Button("Annuler");
//                            close.addActionListener((ee) -> s.dispose());
//                            s.add(close);
//                            s.showDialog();
//                        }
//                    });
//                    f2.add(img);
//                    f2.add(l3);
//                    f2.add(r);
//                    f2.add(bt);
//                    f2.show();
//                }
//            });
//            c4.setLeadComponent(l3);
//            c.add(c4);
//        }
//
//        return c;
//    }
    Container cx = new Container(BoxLayout.y());

    public Container ListDeals() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("List Deals", BoxLayout.x());
        Container c1 = null;
        DealService lr = new DealService();
        for (Deal e : lr.getListDeals2()) {

            Image placeholder = Image.createImage(130, 130);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevisName(),
                    "http://localhost/PIDEV/web/devis/" + e.getDevisName());
            ImageViewer img1 = new ImageViewer(imgUrl);

            Button details = new Button("Plus de détails");
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    DealDetails DD = new DealDetails();
//                    DD.showPropertyDetails(e);
                    UpdateDeal ud = new UpdateDeal();
                    ud.getoldValues(e);
                    ud.getF().show();
                }
            });
            int fontSize = Display.getInstance().convertToPixels(3);

            // requires Handlee-Regular.ttf in the src folder root!
            Font ttfFont = Font.createTrueTypeFont("Poppins", "Poppins-Bold.ttf").
                    derive(fontSize, Font.STYLE_PLAIN);
            Container cnom = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label name = new Label(e.getNom());

            Style nameicon = new Style(name.getUnselectedStyle());
            nameicon.setFgColor(0x566573);
            FontImage nameiconx = FontImage.createMaterial(FontImage.MATERIAL_BOOKMARK, nameicon);
            name.setIcon(nameiconx);
            System.out.println(e.getCat());
            String rating = String.valueOf(e.getRating());
            Slider rat = createStarRankSlider(Integer.parseInt(rating.substring(0, 1)));
            Container ratc = new Container(BoxLayout.x());

            Label ratlabel = new Label("Rating:");

            ratc.add(ratlabel);
            ratc.add(rat);
            cnom.getStyle().setPaddingBottom(2);

            cnom.add(name);

            cnom.add(ratc);
            cnom.add(details);
            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c1.getStyle().setPaddingBottom(20);

            c1.add(img1);

            c1.add(cnom);

            cx.add(c1);

        }
        return cx;

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

//    public Container ListDeals() {
//        Container c1 = null;
//        DealService cs = new DealService();
//        for (Deal e : cs.getListDeals2()) {
//
//            Image placeholder = Image.createImage(500, 170);
//            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevisName(),
//                    "http://localhost/PIDEV/web/devis/" + e.getDevisName());
//            ImageViewer img1 = new ImageViewer(imgUrl);
//
//            Label l3 = new Label(e.getNom());
//
//            Button details = new Button("Plus de détails");
//            details.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//
//                }
//            });
//
//            Container cnom = new Container(new BoxLayout(BoxLayout.X_AXIS));
//
//            cnom.add(l3);
//            cnom.getStyle().setBgColor(0x99CCCC);
//            cnom.getStyle().setBgTransparency(150);
//
//            c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//            c1.getStyle().setPaddingBottom(20);
//
//            c1.add(cnom);
//            c1.add(img1);
//            c1.add(details);
//
//            cx.add(c1);
//
//        }
//        return cx;
//
//    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
