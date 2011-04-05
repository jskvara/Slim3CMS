package cms.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CleanupFilter implements Filter {

	private FilterConfig filterConfig = null;

	public CleanupFilter() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain)
	throws IOException, ServletException {
		//try {
			// Messages.deleteSessionMessages();
			chain.doFilter(request, response);
			// TODO !!!
//			HttpServletRequest httpRequest = (HttpServletRequest) request;
//			httpRequest.getSession().setAttribute("messages", null);
//		} catch(Throwable t) {
//			t.printStackTrace();
//		}
	}

	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
