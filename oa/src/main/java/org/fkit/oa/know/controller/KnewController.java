package org.fkit.oa.know.controller;


import javax.annotation.Resource;

import org.fkit.oa.news.repository.NewsRepository;
import org.fkit.oa.news.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/oa")
public class KnewController {
	@Resource
	private NewsRepository newsRepository;
	@Resource
	private NewsService newsService;
	
	/**
	 * 到新闻发布界面
	 * @return
	 */
	@RequestMapping("/know")
	public String showAddnews() {
		return "know/know";
	}
	
	
}
