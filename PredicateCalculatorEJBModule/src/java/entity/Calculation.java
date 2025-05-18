package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Calculation.findByUser", 
                query = "SELECT c FROM Calculation c WHERE c.user.username = :username"),
    @NamedQuery(name = "Calculation.findRecent", 
                query = "SELECT c FROM Calculation c WHERE c.user.username = :username ORDER BY c.calculationDate DESC")
})
public class Calculation implements Serializable {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double assignment;
    private double project;
    private double classTest;
    private double semesterTest;
    private double quiz;
    private double finalGrade;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date calculationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
    
    @PrePersist
    protected void onCreate() {
        this.calculationDate = new Date();
        this.finalGrade = calculateFinalGrade();
    }
    
    private double calculateFinalGrade() {
        return (assignment * 0.05) + 
               (project * 0.15) + 
               (classTest * 0.20) + 
               (semesterTest * 0.45) + 
               (quiz * 0.15);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAssignment() {
        return assignment;
    }

    public void setAssignment(double assignment) {
        this.assignment = assignment;
    }

    public double getProject() {
        return project;
    }

    public void setProject(double project) {
        this.project = project;
    }

    public double getClassTest() {
        return classTest;
    }

    public void setClassTest(double classTest) {
        this.classTest = classTest;
    }

    public double getSemesterTest() {
        return semesterTest;
    }

    public void setSemesterTest(double semesterTest) {
        this.semesterTest = semesterTest;
    }

    public double getQuiz() {
        return quiz;
    }

    public void setQuiz(double quiz) {
        this.quiz = quiz;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
}