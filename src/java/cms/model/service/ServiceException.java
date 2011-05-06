package cms.model.service;

import org.slim3.controller.validator.Errors;

public class ServiceException extends Exception {

	protected Errors errors;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String name, String msg) {
		this.errors = new Errors();
		this.errors.put(name, msg);
	}

	public ServiceException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
