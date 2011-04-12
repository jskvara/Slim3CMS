package cms.model.converter;

import cms.model.service.ServiceException;
import org.slim3.controller.validator.Errors;

public class ConverterException extends ServiceException {

	public ConverterException() {
	}
	
	public ConverterException(String name, String msg) {
		this.errors = new Errors();
		this.errors.put(name, msg);
	}

	public ConverterException(Errors errors) {
		this.errors = errors;
	}
}
