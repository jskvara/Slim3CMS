package cms.model.converter;

import cms.model.model.NewsEntity;

public class NewsConverter extends AbstractConverter {

	public NewsConverter() {
		entity = new NewsEntity();
	}

	public void convertFields() throws ConverterException {
		((NewsEntity) entity).setCreated(convertDate("created"));
	}
}
