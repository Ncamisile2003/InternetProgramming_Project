package controller;


import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("longin.jsp");
            return;
        }
        
        // Forward to profile page with user data
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("longin.jsp");
            return;
        }
        
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        
        // Validate current password (you would check against database in real app)
        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (!currentPassword.equals(user.getPassword())) {
                request.setAttribute("errorMessage", "Current password is incorrect");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }
            
            // Update password if new password provided
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }
        }
        
        // Update user details
        user.setName(name);
        user.setEmail(email);
        
        // In a real application, you would save to database here
        // userDAO.updateUser(user);
        
        // Update session with modified user
        session.setAttribute("user", user);
        request.setAttribute("successMessage", "Profile updated successfully!");
        
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}