package cms.model.converter;

import cms.model.service.ServiceException;
import org.slim3.controller.validator.Errors;

public class ConverterException extends ServiceException {

	public ConverterException() {
	}

	public ConverterException(ServiceException e) {
		super(e.getErrors());
	}
	
	public ConverterException(String name, String msg) {
		super(name, msg);
	}

	public ConverterException(Errors errors) {
		super(errors);
	}
}
