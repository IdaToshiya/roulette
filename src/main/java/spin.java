import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class spin
 */
@WebServlet("/spin")
public class spin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int yosou=Integer.parseInt(request.getParameter("yosou"));
		
		int min = 1;
	    int max = 9;

	    Random random = new Random();

	    int result = random.nextInt(max + min) + min;
	    
	    String gouhi;
	    
	    if (result == yosou) {
	    	gouhi = "maru";
	    	request.setAttribute("gouhi", gouhi);
	    	request.setAttribute("result", result);
			request.getRequestDispatcher("Main.jsp").forward(request, response);
	    }else {
	    	gouhi ="batu";
	    	request.setAttribute("gouhi", gouhi);
	    	request.setAttribute("result", result);
			request.getRequestDispatcher("Main.jsp").forward(request, response);
	    }
	}

}
