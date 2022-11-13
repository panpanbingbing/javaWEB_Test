package servlet_Test;

import bean.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet({"/session/bing"})
public class ListenerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User("156","lishi","123456");
        session.setAttribute("user",user);
        ServletContext servletContext = request.getServletContext();
        int count=0;
        if(session!=null) {
        servletContext.setAttribute("count",++count);

        }

    }
}
