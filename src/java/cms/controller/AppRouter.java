package cms.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {
	public AppRouter() {
		 // /{app}/admin/
		addRouting("/admin/page/tags/{key}", "/admin/page/tags?key={key}");
		addRouting("/admin/page/edit/{key}", "/admin/page/edit?key={key}");
		addRouting("/admin/page/delete/{key}", "/admin/page/delete?key={key}");

		addRouting("/admin/news/edit/{key}", "/admin/news/edit?key={key}");
		addRouting("/admin/news/delete/{key}", "/admin/news/delete?key={key}");

		addRouting("/admin/tag/edit/{key}", "/admin/tag/edit?key={key}");
		addRouting("/admin/tag/delete/{key}", "/admin/tag/delete?key={key}");

		addRouting("/admin/template/edit/{key}", "/admin/template/edit?key={key}");
		addRouting("/admin/template/delete/{key}", "/admin/template/delete?key={key}");

		addRouting("/admin/author/edit/{key}", "/admin/author/edit?key={key}");
		addRouting("/admin/author/delete/{key}", "/admin/author/delete?key={key}");
	}
} 