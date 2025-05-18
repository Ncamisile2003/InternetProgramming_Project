package controller;

import ejb.GradeCalculator;
import entity.Calculation;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculate")
public class CalculationServlet extends HttpServlet {
    
    @EJB
    private GradeCalculator gradeCalculator;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            // 1. Get current user from session
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                throw new IllegalStateException("User not logged in");
            }
            
            // 2. Set current user in calculator
            gradeCalculator.setCurrentUser(user);
            
            // 3. Create and populate calculation
            Calculation calc = gradeCalculator.startNewCalculation();
            calc.setAssignment(Double.parseDouble(request.getParameter("assignment")));
            calc.setProject(Double.parseDouble(request.getParameter("project")));
            calc.setClassTest(Double.parseDouble(request.getParameter("classTest")));
            calc.setSemesterTest(Double.parseDouble(request.getParameter("semesterTest")));
            calc.setQuiz(Double.parseDouble(request.getParameter("quiz")));
            
            // 4. Save calculation - finalGrade will be auto-calculated by @PrePersist
            gradeCalculator.saveCurrentCalculation();
            
            // 5. Pass data to result page
            request.setAttribute("calculation", calc);
            request.getRequestDispatcher("result.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Calculation failed: " + e.getMessage());
            request.getRequestDispatcher("calculate.jsp").forward(request, response);
        }
    }
    
}
