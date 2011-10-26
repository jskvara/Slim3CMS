package cms.util;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FlashScopeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain)
	throws IOException, ServletException {

		// store all flash messages from session to request
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);
			if (session != null) {
				List<Message> flashParams = (List<Message>)
						session.getAttribute(Messages.FLASH_SESSION_KEY);
					request.setAttribute(Messages.FLASH_REQUEST_KEY, flashParams);
					session.removeAttribute(Messages.FLASH_SESSION_KEY);
				}
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
	}
}
