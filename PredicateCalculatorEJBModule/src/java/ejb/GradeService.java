package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entity.Calculation;
import entity.User;
import util.PasswordHashUtil; // Changed from static import

@Stateless
public class GradeService {
    
    @PersistenceContext
    private EntityManager em;
    
    public User registerUser(String username, String password, String name, String email) {
        // Check if user exists
        if (em.find(User.class, username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        // Create new user with hashed password
        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordHashUtil.hashPassword(password)); // Proper password hashing
        user.setName(name);
        user.setEmail(email);
        user.getRoles().add("STUDENT"); // Default role
        
        em.persist(user);
        return user;
    }
    
    public User authenticate(String username, String password) {
        User user = em.find(User.class, username);
        if (user == null || !PasswordHashUtil.verifyPassword(password, user.getPassword())) {
            throw new SecurityException("Invalid credentials");
        }
        return user;
    }
    
    public Calculation createCalculation(String username, Calculation calculation) {
        User user = em.find(User.class, username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        
        user.addCalculation(calculation);
        em.persist(calculation);
        return calculation;
    }
    
    public List<Calculation> getUserCalculations(String username) {
        TypedQuery<Calculation> query = em.createNamedQuery(
            "Calculation.findByUser", Calculation.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
    
    public List<Calculation> getRecentCalculations(String username, int limit) {
        TypedQuery<Calculation> query = em.createNamedQuery(
            "Calculation.findRecent", Calculation.class);
        query.setParameter("username", username);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    public User findUser(String username) {
        return em.find(User.class, username); // Simplified to use em.find()
    }
}