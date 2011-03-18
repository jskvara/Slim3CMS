package cms.model.validator;

import cms.model.model.IEntity;

public interface IValidator {
	public boolean validate(IEntity entity) throws ValidatorException;
}
