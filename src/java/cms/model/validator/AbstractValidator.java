package cms.model.validator;

import cms.model.model.IEntity;
import java.util.HashMap;
import org.slim3.controller.ControllerConstants;
import org.slim3.controller.validator.Errors;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;

public abstract class AbstractValidator implements IValidator {

	protected Validators validators;

	public boolean validate(IEntity entity) throws ValidatorException {
		HashMap<String, Object> input = new HashMap<String, Object>();
		input.put(ControllerConstants.ERRORS_KEY, new Errors());
		BeanUtil.copy(entity, input);
		validators = new Validators(input);

		validateFields();

		if (!validators.validate()) {
			throw new ValidatorException(validators.getErrors());
		}

		return validators.validate();
	}

	protected abstract void validateFields();
}
