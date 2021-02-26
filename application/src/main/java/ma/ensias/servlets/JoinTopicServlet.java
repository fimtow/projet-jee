package ma.ensias.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ma.ensias.dao.DAOFactory;
import ma.ensias.process.JoinTopic;

/**
 * Servlet implementation class JoinTopicServlet
 */

public class JoinTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_USER = "userSession";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinTopicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("userSession",DAOFactory.getInstance().getUserDao().find(1));
		
		JoinTopic joinTopic = new JoinTopic(request);
		
		Gson gson = new Gson();
		String message = gson.toJson(joinTopic);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(message);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
