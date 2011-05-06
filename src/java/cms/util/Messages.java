package cms.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slim3.util.RequestLocator;

public final class Messages {

	public static final String FLASH_SESSION_KEY = "flash-session-key";
	public static final String FLASH_REQUEST_KEY = "flash-request-key";

	public static void setSessionMessage(String message) {
		setSessionMessage(message, Message.SUCCESS);
	}

	public static void setSessionMessage(String message, String type) {
		HttpSession session = getRequest().getSession();
		List<Message> messages = (List<Message>)session.getAttribute(FLASH_SESSION_KEY);
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		messages.add(new Message(message, type));
		session.setAttribute(FLASH_SESSION_KEY, messages);
	}
	
	public static void setRequestMessage(String message) {
		setRequestMessage(message, Message.SUCCESS);
	}

	public static void setRequestMessage(String message, String type) {
		HttpServletRequest request = getRequest();
		List<Message> messages = (List<Message>) request.getAttribute(FLASH_REQUEST_KEY);
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		messages.add(new Message(message, type));
		request.setAttribute(FLASH_REQUEST_KEY, messages);
	}

	public static List<Message> getRequestMessages() {
		return (List<Message>) getRequest().getAttribute(FLASH_REQUEST_KEY);
	}

	public static List<Message> getSessiontMessages() {
		return (List<Message>) getRequest().getSession().getAttribute(FLASH_SESSION_KEY);
	}

	public static List<Message> getAllMessages() {
		List<Message> messages = new ArrayList<Message>();

		List<Message> requestMessages = getRequestMessages();
		if (requestMessages != null) {
			messages.addAll(requestMessages);
		}

		List<Message> sessionMessages = getSessiontMessages();
		if (sessionMessages != null) {
			messages.addAll(sessionMessages);
		}

		return messages;
	}

	protected static HttpServletRequest getRequest() throws IllegalStateException {
		HttpServletRequest request = RequestLocator.get();
		if (request == null) {
			throw new IllegalStateException("JSP should be called via FrontController.");
		}

		return request;
	}
}
