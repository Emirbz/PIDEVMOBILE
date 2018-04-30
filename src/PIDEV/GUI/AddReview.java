/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Review;
import PIDEV.Services.ReviewService;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Date;

/**
 *
 * @author Emir
 */
public class AddReview {
    Form f;
    Etablissement e;
    public void AddReview(Etablissement e) {

        f = new Form("Ajouter un Deal", BoxLayout.y());

        TextField qualite= new TextField("", "Qualite");
        TextField service = new TextField("", "Service");
        TextField commentaire= new TextField("", "Commentaire");
        TextField titre = new TextField("", "Titre");
        
     
        Button btn = new Button("Valider");

        f.add(qualite);

        f.add(service);

        f.add(commentaire);

        f.add(titre);

       

        f.add(btn);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Review d = new Review();
                d.setQualite(Double.valueOf(qualite.getText()));
                d.setService(Double.valueOf(service.getText()));
                d.setCommentaire(commentaire.getText());
                d.setTitre(titre.getText());
                System.out.println(titre.getText());
                d.setIdetab(e);
               ReviewService rs = new ReviewService();
               rs.AddReview(d);
                       
                System.out.println("success");
            }
        });

        f.show();
    
}

    
    
}
