package ejb;

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import entity.Calculation;
import entity.User;



@Stateful
public class GradeCalculator {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    private User currentUser;
    private Calculation currentCalculation;
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public Calculation startNewCalculation() {
        this.currentCalculation = new Calculation();
        return currentCalculation;
    }
    
    public Calculation getCurrentCalculation() {
        return currentCalculation;
    }
    
    public Calculation saveCurrentCalculation() {
        if (currentUser == null || currentCalculation == null) {
            throw new IllegalStateException("User or calculation not set");
        }
        
        currentUser.addCalculation(currentCalculation);
        em.persist(currentCalculation);
        return currentCalculation;
    }
    
    public List<Calculation> getUserCalculations() {
        if (currentUser == null) {
            throw new IllegalStateException("User not set");
        }
        
        return em.createQuery(
            "SELECT c FROM Calculation c WHERE c.user.username = :username " +
            "ORDER BY c.calculationDate DESC", Calculation.class)
            .setParameter("username", currentUser.getUsername())
            .getResultList();
    }
}
