package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-18T14:14:30")
@StaticMetamodel(Calculation.class)
public class Calculation_ { 

    public static volatile SingularAttribute<Calculation, Double> quiz;
    public static volatile SingularAttribute<Calculation, Double> classTest;
    public static volatile SingularAttribute<Calculation, Double> semesterTest;
    public static volatile SingularAttribute<Calculation, Double> assignment;
    public static volatile SingularAttribute<Calculation, Double> project;
    public static volatile SingularAttribute<Calculation, Double> finalGrade;
    public static volatile SingularAttribute<Calculation, Long> id;
    public static volatile SingularAttribute<Calculation, Date> calculationDate;
    public static volatile SingularAttribute<Calculation, User> user;

}