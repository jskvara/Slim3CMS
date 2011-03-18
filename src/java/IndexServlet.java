import cms.model.model.PageEntity;
import cms.model.service.PageService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slim3.util.RequestMap;

public class IndexServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		PageService pageService = new PageService();

		String action = request.getParameter("action");
		if ("new".equalsIgnoreCase(action)) {
			//String url = request.getParameter("url");
			//if (url != null && !url.equals("")) {
			RequestMap requestMap = new RequestMap(request);
			System.out.println("RM: "+ requestMap.get("url"));
			Map<String, Object> rm = new HashMap<String, Object>();
			//rm = request.getParameterMap();
			rm.put("url", request.getParameter("url"));
			pageService.insert(rm);
			//}
		} 

		List<PageEntity> pages = pageService.getAllPages();
		request.setAttribute("pages", pages);

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/WEB-INF/viewPages.jsp");
		rd.include(request, response);

    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}
