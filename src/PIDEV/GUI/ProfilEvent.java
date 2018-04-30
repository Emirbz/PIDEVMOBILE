/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Events;
import PIDEV.Services.Profileventservice;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Emir
 */
public class ProfilEvent extends BaseForm {

    Form f;
    Label name;
    Label address;
    Label dateEvenement;
    Label description;
    Label nbrplace;
    Label adressemail;
    private Resources theme;

    public ProfilEvent(Resources res, int id) throws IOException {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Event", BoxLayout.y());
        Profileventservice pes = new Profileventservice();
        for (Events e : pes.getList2(id)) {
            System.out.println("dqsdq");
            System.out.println(e.getName());

            name = new Label();

            address = new Label();

            description = new Label();
            nbrplace = new Label();
            adressemail = new Label();
            dateEvenement = new Label();

            Container cnom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container caddress = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cdate = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cdescription = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cplace = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cadressemail = new Container(new BoxLayout(BoxLayout.X_AXIS));
            cnom.add(name);
            caddress.add(address);
            cdescription.add(description);
            cplace.add(nbrplace);
            cadressemail.add(adressemail);
            cdate.add(dateEvenement);

//            cnom.add(dateEvenement);
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cnom.getStyle().setBgColor(0x99CCCC);
            cnom.getStyle().setBgTransparency(150);
            caddress.getStyle().setBgColor(0x99DDD);
            caddress.getStyle().setBgTransparency(150);
            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getDevis_name());
            ImageViewer img1 = new ImageViewer(imgUrl);

            Image placeholder1 = Image.createImage(500, 170);
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
            URLImage imgUrl1 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImage(), "http://localhost/PIDEV/web/uploads/images/" + e.getImage());
            ImageViewer img1x = new ImageViewer(imgUrl1);

            URLImage imgUrl2 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImage1(), "http://localhost/PIDEV/web/uploads/images/" + e.getImage1());
            ImageViewer img2x = new ImageViewer(imgUrl2);

            URLImage imgUrl3 = URLImage.createToStorage(encImage1, "http://localhost/PIDEV/web/uploads/images/" + e.getImage2(), "http://localhost/PIDEV/web/uploads/images/" + e.getImage2());
            ImageViewer img3x = new ImageViewer(imgUrl3);
            c1.add(img1);
            c1.add(cnom);
            c1.add(caddress);
            c1.add(cdescription);
            c1.add(cdate);
            //c1.add(cplace);
            c1.add(cadressemail);
            Container cimage = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cimage.add(img1x);
            cimage.add(img2x);
            cimage.add(img3x);
            c1.add(cimage);

            f.add(c1);

            name.setText(e.getName());

            address.setText(e.getAdresse());

            description.setText(e.getDescription());

            nbrplace.setText(String.valueOf(e.getNbrplace()));
            adressemail.setText(e.getAdressemail());

            dateEvenement.setText(e.getDateEvenement().toString());

//            dateEvenement.setText(e.getDateEvenement().toString());
//            f.getToolbar().addCommandToOverflowMenu("Back", theme.getImage("back.png"), new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    System.out.println("lol");
//                    AffichageEvents lr = new AffichageEvents();
//                    lr.ListEvents();
//                }
//            });

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

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
