package cms.model.service;

import org.slim3.controller.validator.Errors;

public class ServiceException extends Exception {

	protected Errors errors;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	/*public ValidatorException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ValidatorException(Throwable cause) {
		super(cause);
	}*/
}
