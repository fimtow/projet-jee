package ma.ensias.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

public class RestrictionFilter implements Filter {
    public static final String SESSION_USER = "userSession";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
    	
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.startsWith( "/sign" ) ) {
            chain.doFilter( request, response );
            return;
        }
        
        HttpSession session = request.getSession();

        if ( session.getAttribute( SESSION_USER ) == null ) {    		
    		JsonObject jsonObject = new JsonObject(); 
    		jsonObject.addProperty("error", "You need to be logged in");
    		String message = jsonObject.toString();
    		PrintWriter out = response.getWriter();
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		out.print(message);
    		out.flush();
        } else {
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}