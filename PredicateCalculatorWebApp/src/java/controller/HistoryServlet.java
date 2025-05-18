package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // Check if user is logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("longin.jsp");
            return;
        }
        
        // Get calculation history from session (replace with database in production)
        List<Calculation> calculations = getCalculationHistory(session);
        
        // Set attributes for JSP
        request.setAttribute("calculations", calculations);
        
        // Forward to history page
        request.getRequestDispatcher("history.jsp").forward(request, response);
    }
    
    // Retrieve calculations from session storage
    private List<Calculation> getCalculationHistory(HttpSession session) {
        // Get existing calculations or create new list if none exists
        List<Calculation> calculations = (List<Calculation>) session.getAttribute("calculations");
        if (calculations == null) {
            calculations = new ArrayList<>();
            session.setAttribute("calculations", calculations);
        }
        return calculations;
    }
}

// Calculation model class
class Calculation {
    private String assignment;
    private String project;
    private String classTest;
    private String semesterTest;
    private String quiz;
    private String finalGrade;
    private String calculationDate;
    
    public Calculation(String assignment, String project, String classTest, 
                      String semesterTest, String quiz, String finalGrade, 
                      String calculationDate) {
        this.assignment = assignment;
        this.project = project;
        this.classTest = classTest;
        this.semesterTest = semesterTest;
        this.quiz = quiz;
        this.finalGrade = finalGrade;
        this.calculationDate = calculationDate;
    }
    
    // Getters
    public String getAssignment() { return assignment; }
    public String getProject() { return project; }
    public String getClassTest() { return classTest; }
    public String getSemesterTest() { return semesterTest; }
    public String getQuiz() { return quiz; }
    public String getFinalGrade() { return finalGrade; }
    public String getCalculationDate() { return calculationDate; }
}