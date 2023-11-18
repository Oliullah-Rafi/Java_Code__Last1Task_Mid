import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            List<User> users = DBUtil.getAllUsers();
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userSession", user);
                    response.sendRedirect("welcome.jsp");
                    return;
                }
            }
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            throw new ServletException("something went wrong", e);
        }
    }
}
