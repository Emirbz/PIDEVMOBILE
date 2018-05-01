/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Deal;
import PIDEV.Entities.Catdeal;
import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import PIDEV.Services.DealService;

/**
 *
 * @author Skan
 */
public class AddDeal {

    Form f;
    String imgname;

    public AddDeal() {

        f = new Form("Ajouter un Deal", BoxLayout.y());

        TextField nom = new TextField("", "Nom");
        TextField desc = new TextField("", "Description");
        TextField oldprix = new TextField("", "Ancien Prix");
        TextField promotion = new TextField("", "Promotion");
        TextField adresse = new TextField("", "Adresse");
        Picker datef = new Picker();
        TextField places = new TextField("", "Places disponibles");
        Picker region = new Picker();
        String[] p = {"Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba",
            "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
            "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
        region.setStrings(p);
        region.setSelectedString(p[0]);

        Picker cat = new Picker();
        DealService ds = new DealService();
        ArrayList<Catdeal> cats = ds.getListcats2();
        Catdeal cat1 = cats.get(0);
        Catdeal cat2 = cats.get(1);
        Catdeal cat3 = cats.get(2);
        String ses[] = new String[3];
        ses[0] = cat1.getNom();
        ses[1] = cat2.getNom();
        ses[2] = cat3.getNom();
        cat.setStrings(ses);
        cat.setSelectedString(ses[0]);
        Button avatar = new Button("");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
        Image circleMaskImage = Image.createImage(250, 200);
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage) defaultAvatar).toEncodedImage();
        Object circleMask = circleMaskImage.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);

        avatar.addActionListener(e -> {
            if (Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "Gallery")) {
                String pic = Capture.capturePhoto();
                if (pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(19999999);
                        String devisnamee = String.valueOf(randomInt) + ".jpg";
                        imgname = devisnamee;
                        System.out.println(imgname);
                        System.out.println(FileSystemStorage.getInstance().getAppHomePath());
                        String imageFile = "file://C:/wamp64/www/PIDEV/web/devis/" + devisnamee;
                        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);) {
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {
                            Log.e(err);
                        }
                    } catch (IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }
                }
            } else {
                openGallery(ee -> {
                    if (ee.getSource() != null) {
                        try {
                            Image img = Image.createImage((String) ee.getSource()).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                            avatar.setIcon(img.applyMask(circleMask));
                        } catch (IOException err) {
                            ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                            Log.e(err);
                        }
                    }
                }, GALLERY_IMAGE);
            }
        });
        Button btn = new Button("Valider");
        nom.getStyle().setMargin(2, 2, 2, 2);
        f.add(nom);
        desc.getStyle().setMargin(2, 2, 2, 2);
        f.add(desc);
        oldprix.getStyle().setMargin(2, 2, 2, 2);
        f.add(oldprix);
        promotion.getStyle().setMargin(2, 2, 2, 2);
        f.add(promotion);
        places.getStyle().setMargin(2, 2, 2, 2);
        f.add(places);
        adresse.getStyle().setMargin(2, 2, 2, 2);
        f.add(adresse);
        region.getStyle().setMargin(2, 2, 2, 2);
        f.add(region);
        cat.getStyle().setMargin(2, 2, 2, 2);
        f.add(cat);
        datef.getStyle().setMargin(2, 2, 2, 2);
        f.add(datef);
        avatar.getStyle().setMargin(2, 2, 2, 2);
        f.add(avatar);
        btn.getStyle().setMargin(2, 2, 2, 2);
        f.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Deal d = new Deal();
                d.setNom(nom.getText());
                d.setDescription(desc.getText());
                d.setAdresse(adresse.getText());
                d.setRegion(region.getSelectedString());
                System.out.println(cat.getSelectedStringIndex());
                d.setCat(cats.get(cat.getSelectedStringIndex()));
                d.setOldprix(Double.parseDouble(oldprix.getText()));
                d.setPromotion(Double.parseDouble(promotion.getText()));
                d.setPlacesdispo(Integer.parseInt(places.getText()));
                Date date = datef.getDate();
                d.setDatefin(date);
                d.setDevisName(imgname);
                ajoutTask(d);
                System.out.println("success");
            }
        });
        f.show();
    }

    public void ajoutTask(Deal ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev/web/app_dev.php/deals/ajs/" + ta.getCat().getId() + "?nom=" + ta.getNom() + "&promotion=" + ta.getPromotion()
                + "&oldprix=" + ta.getOldprix() + "&description=" + ta.getDescription() + "&datefin=" + ta.getDatefin()
                + "&region=" + ta.getRegion() + "&adresse=" + ta.getAdresse() + "&devisName=" + ta.getDevisName() + "&placesdispo=" + ta.getPlacesdispo();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
