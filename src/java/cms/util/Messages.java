package cms.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slim3.util.RequestLocator;

public final class Messages {

	public static void setSessionMessage(String message) {
		setSessionMessage(message, Message.SUCCESS);
	}

	public static void setSessionMessage(String message, String type) {
		HttpSession session = getRequest().getSession();
		List<Message> messages = (List<Message>)session.getAttribute("messages");
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		messages.add(new Message(message, type));
		session.setAttribute("messages", messages);
	}

	public static void setRequestMessage(String message) {
		HttpServletRequest request = getRequest();
		List<Message> messages = (List<Message>) request.getAttribute("messages");
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		messages.add(new Message(message));
		request.setAttribute("messages", messages);
	}

//	public static void deleteSessionMessages() {
//		getRequest().getSession().setAttribute("messages", null);
//	}

	public static List<Message> getRequestMessages() {
		return (List<Message>) getRequest().getAttribute("messages");
	}

	public static List<Message> getSessiontMessages() {
		return (List<Message>) getRequest().getSession().getAttribute("messages");
	}

	public static List<Message> getAllMessages() {
		List<Message> messages = new ArrayList<Message>();

		// TODO errors
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
