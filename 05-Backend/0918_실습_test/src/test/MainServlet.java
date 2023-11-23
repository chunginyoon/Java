package test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        
        context.setAttribute("data", request.getParameter("a"));
        HttpSession session = request.getSession();
        
        session.setAttribute("data", request.getParameter("a"));
        
        request.setAttribute("data", request.getParameter("a"));
        
        response.getWriter().write("context에 저장 완료");
        
    }
}