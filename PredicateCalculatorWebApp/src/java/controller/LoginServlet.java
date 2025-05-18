package controller;

import ejb.GradeService;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
     private GradeService gradeService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            User user = gradeService.authenticate(username, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/longin.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("longin.jsp");
    }
}