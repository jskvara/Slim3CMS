package cms.util;

import cms.controller.IndexController;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slim3.controller.ControllerConstants;
import org.slim3.controller.FrontController;
import org.slim3.controller.validator.Errors;
import org.slim3.util.ApplicationMessage;
import org.slim3.util.CipherFactory;
import org.slim3.util.LocaleLocator;
import org.slim3.util.RequestLocator;
import org.slim3.util.RequestUtil;
import org.slim3.util.ResponseLocator;
import org.slim3.util.TimeZoneLocator;

public class FrontendFilter extends FrontController {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response,
						 FilterChain chain)
	throws IOException, ServletException {

		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(charset);
		}
		HttpServletRequest previousRequest = RequestLocator.get();
		RequestLocator.set(request);
		HttpServletResponse previousResponse = ResponseLocator.get();
		ResponseLocator.set(response);
		Locale previousLocale = LocaleLocator.get();
		LocaleLocator.set(processLocale(request));
		TimeZone previousTimeZone = TimeZoneLocator.get();
		TimeZoneLocator.set(processTimeZone(request));
		ApplicationMessage.setBundle(bundleName, LocaleLocator.get());
		CipherFactory.getFactory().clearLimitedKey();
		try {
			String basePath = RequestUtil.getPath(request);
			if (basePath.startsWith("/_ah/")) {
				chain.doFilter(request, response);
			}

			basePath = basePath.substring(1); // remove starting /
			Errors errors =
				(Errors) request.getAttribute(ControllerConstants.ERRORS_KEY);
			if (errors == null) {
				errors = new Errors();
				request.setAttribute(ControllerConstants.ERRORS_KEY, errors);
			}

			IndexController indexController = new IndexController(servletContext,
					request, response, basePath, errors);

			processController(request, response, indexController);
		} finally {
			ApplicationMessage.clearBundle();
			TimeZoneLocator.set(previousTimeZone);
			LocaleLocator.set(previousLocale);
			ResponseLocator.set(previousResponse);
			RequestLocator.set(previousRequest);
		}
	}
}
