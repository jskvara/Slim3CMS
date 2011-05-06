package cms.util;

import java.io.Serializable;

public class Message implements Serializable {
	protected String message;
	protected String type;

	public static String SUCCESS = "success";
	public static String INFO = "info";
	public static String ERROR = "error";

	public Message() {
	}

	public Message(String message) {
		this.message = message;
		this.type = SUCCESS;
	}

	public Message(String message, String type) {
		this.message = message;
		this.type = type;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Message{message=" + message + "type=" + type + '}';
	}
}
