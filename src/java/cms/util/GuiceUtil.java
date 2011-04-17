package cms.util;

import cms.model.service.Service;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceUtil {

	public static <T extends Service> T getService(Class<T> type) {
		Injector injector = Guice.createInjector(new GuiceModule());
		Service service = injector.getInstance(type);
		
		return (T) service;
	}
}
