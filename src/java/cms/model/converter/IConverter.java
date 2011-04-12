package cms.model.converter;

import cms.model.model.IEntity;
import java.util.Map;

public interface IConverter {
	public IEntity convert(final Map<String, Object> input) throws ConverterException;
}
