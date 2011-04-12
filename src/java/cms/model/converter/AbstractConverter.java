package cms.model.converter;

import cms.model.model.IEntity;
import cms.util.DateUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import org.slim3.util.ArrayMap;
import org.slim3.util.BeanUtil;

public abstract class AbstractConverter implements IConverter {

	protected IEntity entity;
	protected Map<String, Object> input = new ArrayMap<String, Object>();

	public IEntity convert(final Map<String, Object> input) throws ConverterException {
		BeanUtil.copy(input, this.input); // don't affect request values
		convertFields();
		BeanUtil.copy(this.input, entity);
		
		return entity;
	}

	abstract protected void convertFields() throws ConverterException;
	
	protected Date convertDate(String field) throws ConverterException {
		try {
			String date = (String) input.get(field);
			input.remove(field);
			return DateUtil.stringToDate(date);
		} catch (ParseException e) {
			throw new ConverterException(field, "Nesprávně zadané datum.");
		}
	}
}
