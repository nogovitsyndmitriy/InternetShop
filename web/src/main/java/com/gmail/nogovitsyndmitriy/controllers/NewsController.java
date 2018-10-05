package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.NewsService;

import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final UserService userService;
    private final static int QUANTITY_ON_PAGE = 5;

    public NewsController(PageProperties pageProperties, NewsService newsService, UserService userService) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.userService = userService;
    }

    @GetMapping
    public String getNews(@RequestParam(value = "page", defaultValue = "1") long page, ModelMap modelMap) {
        long quantityOfNews = newsService.quantityOfNews();
        long pagesQuantity = quantityOfPages(quantityOfNews, QUANTITY_ON_PAGE);
        List<NewsDto> news = newsService.newsPagination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPage();
    }

    @GetMapping(value = "/{id}")
    public String getCurrentNews(@PathVariable("id") long id, ModelMap modelMap) {
        NewsDto news = newsService.get(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getSingleNewsPagePath();
    }
}
