package cms.model.validator;

import cms.model.model.IEntity;

public interface IValidator {
	public boolean validateAdd(IEntity entity) throws ValidatorException;
	public boolean validateEdit(IEntity entity) throws ValidatorException;
}
