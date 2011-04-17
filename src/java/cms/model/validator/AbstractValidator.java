package cms.model.validator;

import cms.model.model.IEntity;
import java.util.HashMap;
import org.slim3.controller.ControllerConstants;
import org.slim3.controller.validator.Errors;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;

public abstract class AbstractValidator implements IValidator {

	protected boolean add = false;
	protected Validators validators;
	protected HashMap<String, Object> input = new HashMap<String, Object>();

	public boolean validateAdd(IEntity entity) throws ValidatorException {
		add = true;
		return validate(entity);
	}

	public boolean validateEdit(IEntity entity) throws ValidatorException {
		return validate(entity);
	}

	protected boolean validate(IEntity entity) throws ValidatorException {
		input.put(ControllerConstants.ERRORS_KEY, new Errors());
		BeanUtil.copy(entity, input);
		validators = new Validators(input);

		if (!validateFields() || !validators.validate()) {
			throw new ValidatorException(validators.getErrors());
		}

		return validators.validate();
	}

	protected abstract boolean validateFields();

	protected boolean isAdd() {
		return add;
	}

	protected boolean isEdit() {
		return !add;
	}
}
