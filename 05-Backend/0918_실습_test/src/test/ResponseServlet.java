package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        
        String data = (String) context.getAttribute("data");
        
        HttpSession session = request.getSession();
        
        String data2 = (String) session.getAttribute("data");
        String data3 = (String) request.getAttribute("data");
        
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("context의 data 값:"+data);
        out.println("session의 data 값:"+data2);
        out.println("request의 data 값:"+data3);
        
    }

}
