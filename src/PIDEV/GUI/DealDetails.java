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
import com.codename1.components.ImageViewer;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Skan
 */
public class DealDetails {

    void showPropertyDetails(Deal d) {
        final Form propertyDetails = new Form("Property Details", new BoxLayout(BoxLayout.Y_AXIS));

        String price_formatted = d.getNewprix().toString();
        String title = d.getNom();
        String summary = d.getDescription();

        Image placeholder = Image.createImage(350, 200);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + d.getDevisName(),
                "http://localhost/PIDEV/web/devis/" + d.getDevisName());
        ImageViewer img1 = new ImageViewer(imgUrl);
        String adresse = d.getAdresse();
        String region = d.getRegion();
        Slider rat = createStarRankSlider(d.getRating());
        Container ratc = new Container(BoxLayout.x());

        Label ratlabel = new Label("Rating:");

        ratc.add(ratlabel);
        ratc.add(rat);

        propertyDetails.add(new Label("Nom : " + title, "LargeTitle")).
                add(new Label("Prix : " + price_formatted, "SecondaryTitle")).
                add(img1).
                add("Adresse :\n" + adresse).
                add("Région : \n" + region).
                add("Description : \n" + summary);
        propertyDetails.add(ratc);
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

}
