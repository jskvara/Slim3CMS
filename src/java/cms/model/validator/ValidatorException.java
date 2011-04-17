package cms.model.validator;

import cms.model.service.ServiceException;
import org.slim3.controller.validator.Errors;

public class ValidatorException extends ServiceException {

	public ValidatorException() {
	}

	public ValidatorException(String name, String msg) {
		super(name, msg);
	}

	public ValidatorException(Errors errors) {
		super(errors);
	}
}
