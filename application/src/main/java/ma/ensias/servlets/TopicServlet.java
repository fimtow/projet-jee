package ma.ensias.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ma.ensias.beans.Topic;
import ma.ensias.forms.TopicForm;

/**
 * Servlet implementation class Topic
 */

public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TopicForm topicForm = new TopicForm();
		Topic topic = topicForm.searchTopic(request);
		
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", topicForm.getResult());
		jsonObject.add("errors", gson.toJsonTree(topicForm.getErrors()));
		if(topicForm.getResult())
		{
			jsonObject.add("topic", gson.toJsonTree(topic));
			if(topicForm.getLogged())
			{
				jsonObject.addProperty("joined", topicForm.getJoined());
			}
			jsonObject.add("posts", gson.toJsonTree(topicForm.getPosts()));
		}
		String message = jsonObject.toString();

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
		TopicForm topicForm = new TopicForm();
		topicForm.createTopic(request);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", topicForm.getResult());
		jsonObject.add("errors", new Gson().toJsonTree(topicForm.getErrors()));
		String message = jsonObject.toString();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(message);
		out.flush();
	}

}
