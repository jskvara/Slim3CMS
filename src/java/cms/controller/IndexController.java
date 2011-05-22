package cms.controller;

import cms.model.model.NewsEntity;
import cms.model.model.PageEntity;
import cms.model.model.TemplateEntity;
import cms.model.model.dto.NewsDTO;
import cms.model.model.dto.PageDTO;
import cms.model.model.dto.TemplateDTO;
import cms.model.service.NewsService;
import cms.model.service.PageService;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Errors;

public class IndexController extends Controller {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);
	private NewsService newsService = GuiceUtil.getService(NewsService.class);

	public IndexController() {
	}

	public IndexController(ServletContext servletContext, HttpServletRequest request, 
			HttpServletResponse response, String basePath, Errors errors) {
		this.servletContext = servletContext;
		this.request = request;
		this.response = response;
		this.basePath = basePath;
		this.errors = errors;
	}

	@Override
	public Navigation run() throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		if (basePath.equals("/")) {
			basePath = "index";
		}
		if (basePath.endsWith(".html")) {
			basePath = basePath.substring(0, basePath.length() - 5);
		}
		PageEntity pageEntity = pageService.getPageByUrl(basePath);
		PageDTO page = new PageDTO(pageEntity);
		if (pageEntity == null || !page.getVisible()) {
			NotFoundController notFoundController = new NotFoundController(
					servletContext, request, response, basePath, errors);
			notFoundController.run();
			
			return null;
		}

		String template = page.getTemplateContent();
		
		template = template.replace("{title}", page.getTitle());
		template = template.replace("{content}", page.getContent());
		if (template.contains("{tags}")) {
			StringBuilder tags = new StringBuilder();
			for (String tag : page.getTags()) {
				tags.append(tag).append(" ");
			}
			template = template.replace("{tags}", tags);
		}

		if (template.contains("{pages}")) {
			String pages = getPages();
			template = template.replace("{pages}", pages);
		}

		if (template.contains("{news}")) {
			String news = getNews("homepage");
			template = template.replace("{news}", news);
		}

		if (template.contains("{newsAll}")) {
			String newsAll = getNews("all");
			template = template.replace("{newsAll}", newsAll);
		}

		String[] tagsArray = page.getTags();
		StringBuilder tags = new StringBuilder();
		for (String tag : tagsArray) {
			tags.append(tag).append(" ");
		}
		template = template.replace("{tags}", tags);
		
		out.println(template);

		return null;
	}

	public String getPages() {
		TemplateEntity pagesTemplateEntity = templateService.getTemplateByName("pages");
		TemplateEntity pageItemTemplateEntity = templateService.getTemplateByName("pageItem");
		if (pagesTemplateEntity == null || pageItemTemplateEntity == null) {
			return "";
		}
		TemplateDTO pagesTemplateDTO = new TemplateDTO(pagesTemplateEntity);
		String pagesTemplate = pagesTemplateDTO.getContent();
		TemplateDTO pageItemTemplateDTO = new TemplateDTO(pageItemTemplateEntity);
		String pageItemTemplate = pageItemTemplateDTO.getContent();

		List<PageEntity> pagesList = pageService.getVisiblePages();

		StringBuilder pages = new StringBuilder();
		for (PageEntity pageEntity : pagesList) {
			PageDTO pageDTO = new PageDTO(pageEntity);
			String pageItem = pageItemTemplate;
			pageItem = pageItem.replace("{url}", pageDTO.getUrl());
			pageItem = pageItem.replace("{title}", pageDTO.getTitle());
			pages.append(pageItem);
		}

		pagesTemplate = pagesTemplate.replace("{pageItem}", pages.toString());

		return pagesTemplate;
	}

	public String getNews(String where) {
		TemplateEntity newsTemplateEntity = templateService.getTemplateByName("news");
		TemplateEntity newsItemTemplateEntity = templateService.getTemplateByName("newsItem");
		if (newsTemplateEntity == null || newsItemTemplateEntity == null) {
			return "";
		}
		TemplateDTO newsTemplateDTO = new TemplateDTO(newsTemplateEntity);
		String newsTemplate = newsTemplateDTO.getContent();
		TemplateDTO newsItemTemplateDTO = new TemplateDTO(newsItemTemplateEntity);
		String newsItemTemplate = newsItemTemplateDTO.getContent();

		List<NewsEntity> newsList = null;
		if (where.equalsIgnoreCase("all")) {
			newsList = newsService.getAllVisibleNews();
		} else {
			newsList = newsService.getHomepageNews();
		}

		StringBuilder news = new StringBuilder();
		for (NewsEntity newsEntity : newsList) {
			NewsDTO newsDTO = new NewsDTO(newsEntity);
			String newsItem = newsItemTemplate;
			newsItem = newsItem.replace("{title}", newsDTO.getTitle());
			newsItem = newsItem.replace("{text}", newsDTO.getText());
			newsItem = newsItem.replace("{created}", newsDTO.getCreated());
			news.append(newsItem);
		}

		newsTemplate = newsTemplate.replace("{newsItem}", news.toString());

		return newsTemplate;
	}
}
