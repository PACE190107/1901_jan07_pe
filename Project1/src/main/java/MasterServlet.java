

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterServlet extends HttpServlet{

private static final long serialVersionUID = 1L;

	
	// This is the object responsible for marshalling/unmarshalling POJOS to JSON
	private final ObjectMapper mapper = new ObjectMapper();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasicConfigurator.configure();

		System.out.println("doGet works");
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(MasterDispatcher.process(req, resp)));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost works");
		
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
