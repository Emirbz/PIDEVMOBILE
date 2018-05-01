package PIDEV.Entities;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-01T18:17:21")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Date> date;
    public static volatile SingularAttribute<Review, User> iduser;
    public static volatile SingularAttribute<Review, Integer> nbrjaime;
    public static volatile SingularAttribute<Review, Double> service;
    public static volatile SingularAttribute<Review, String> titre;
    public static volatile SingularAttribute<Review, Etablissement> idetab;
    public static volatile SingularAttribute<Review, Integer> id;
    public static volatile SingularAttribute<Review, Double> qualite;
    public static volatile SingularAttribute<Review, String> commentaire;

}