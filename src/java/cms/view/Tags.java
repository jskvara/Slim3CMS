package cms.view;

import cms.util.Messages;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.slim3.util.RequestLocator;

public class Tags {
	private Tags() {}

	public static Iterator getMessages() {
		return Messages.getAllMessages().iterator();
	}

	protected static HttpServletRequest request() throws IllegalStateException {
		HttpServletRequest request = RequestLocator.get();
		if (request == null) {
			throw new IllegalStateException("JSP should be called via FrontController.");
		}
		
		return request;
    }
}
