package ma.ensias.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
		
		
		String message;
		if(!topicForm.getResult())
		{ 
			JsonObject jsonObject = new JsonObject();
    		jsonObject.addProperty("error", "Inexistent topic");
    		message = jsonObject.toString();
		}
		else
		{
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(topic);
			JsonElement jsonElement2 = gson.toJsonTree(topicForm.getPosts());
			jsonElement.getAsJsonObject().add("posts", jsonElement2);
			message = gson.toJson(jsonElement);
		}
		
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
		doGet(request, response);
	}

}
