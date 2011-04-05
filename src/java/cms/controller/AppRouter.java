package cms.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {
	public AppRouter() {
		addRouting("/admin/page/edit/{key}", "/admin/page/edit?key={key}"); // /{app}/admin/
		addRouting("/admin/page/delete/{key}", "/admin/page/delete?key={key}");
	}
} 