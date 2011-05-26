package cms.controller.admin;

import cms.model.service.AuthorService;
import cms.util.GuiceUtil;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public abstract class AdminController extends Controller {

	private AuthorService authorService = GuiceUtil.getService(AuthorService.class);

	@Override
	protected Navigation setUp() {
		UserService userService = UserServiceFactory.getUserService();

		String thisURL = request.getRequestURI();
		if (userService.isUserLoggedIn() && (userService.isUserAdmin() ||
				authorService.isAuthor(userService.getCurrentUser().getEmail()))) {
			requestScope("username", userService.getCurrentUser().getEmail());
			requestScope("logoutUrl", userService.createLogoutURL(thisURL));
		} else {
			return new Navigation(userService.createLoginURL(thisURL), true);
		}

		return null;
	}
}
