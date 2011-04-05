package cms.model.validator;

import cms.model.service.ServiceException;
import org.slim3.controller.validator.Errors;

public class ValidatorException extends ServiceException {

	public ValidatorException() {
	}

	public ValidatorException(String msg) {
		super(msg);
	}

	public ValidatorException(Errors errors) {
		this.errors = errors;
	}
}
