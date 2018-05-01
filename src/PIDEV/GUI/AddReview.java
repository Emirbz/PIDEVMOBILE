/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Review;
import PIDEV.Services.ReviewService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author Emir
 */
public class AddReview extends BaseForm {
    Form f;
    Etablissement e;
    
    public AddReview(Etablissement e,Resources res) {
                  super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
         super.addSideMenu(res);

        

      

      
   
        TextField commentaire= new TextField("", "Commentaire");
        TextField titre = new TextField("", "Titre");
       
        titre.setUIID("TEXTFIILD");
        commentaire.setUIID("TEXTFIILD");
        
     
        Button btn = new Button("Valider");



         ButtonGroup barGroup = new ButtonGroup();
RadioButton all = RadioButton.createToggle("Noter "+e.getName(), barGroup);

        all.setUIID("SelectBar");
         add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all)
                
        ));
         Slider qualite = createStarRankSlider();
         Slider service = createStarRankSlider();
           Container rate = new Container(BoxLayout.x());
              Container rate1 = new Container(BoxLayout.x());
                   
                   Label qualitel = new Label("Qualite :");
                   Label servicel = new Label("Service :");
                   rate.add(qualitel);
                   rate.add(qualite);
                   rate1.add(servicel);
                   rate1.add(service);

        
        add(rate);
        add(rate1);

        

        add(commentaire);

        add(titre);

       

        add(btn);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Review d = new Review();
                d.setQualite(Double.valueOf(qualite.getProgress()));
                d.setService(Double.valueOf(service.getProgress()));
                d.setCommentaire(commentaire.getText());
                d.setTitre(titre.getText());
                System.out.println(titre.getText());
                d.setIdetab(e);
               ReviewService rs = new ReviewService();
               rs.AddReview(d);
                       
                ToastBar.showMessage("Merci de noter "+e.getName(), FontImage.MATERIAL_INFO);
           
                try {
                    new ProfilResto(res,e.getId()).show();
                } catch (IOException ex) {
                    
                }
        
 
                
            }
        });

       
    
}
   private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(convertToPixels(2, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    /**
     * Binds the rating widget to the UI if the app wasn't rated yet
     *
     * @param time time in milliseconds for the widget to appear
     * @param appstoreUrl the app URL in the store
     * @param supportEmail support email address if the rating is low
     */
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    
    
}
