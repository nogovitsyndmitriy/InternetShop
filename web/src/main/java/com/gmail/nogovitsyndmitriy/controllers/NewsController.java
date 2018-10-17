package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.controllers.validators.CommentValidator;
import com.gmail.nogovitsyndmitriy.controllers.validators.NewsValidator;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final CommentService commentService;
    private final NewsValidator validator;
    private final CommentValidator commentValidator;

    @Autowired
    public NewsController(PageProperties pageProperties,
                          NewsService newsService,
                          CommentService commentService,
                          NewsValidator validator,
                          CommentValidator commentValidator) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.commentService = commentService;
        this.validator = validator;
        this.commentValidator = commentValidator;
    }


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getNews(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfNews = newsService.quantityOfNews();
        Long pagesQuantity = quantityOfPages(quantityOfNews, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<NewsDto> news = newsService.newsPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPage();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getCurrentNews(@PathVariable("id") Long id, ModelMap modelMap) {
        NewsDto news = newsService.get(id);
        modelMap.addAttribute("news", news);
        CommentDto comment = new CommentDto();
        modelMap.addAttribute("comment", comment);
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        modelMap.addAttribute("comments", comments);
        return pageProperties.getSingleNewsPagePath();
    }

    @PostMapping(value = "/comment/{news_id}")
    @PreAuthorize("isAuthenticated()")
    public String createComment(ModelMap modelMap, @PathVariable("news_id") Long id, @ModelAttribute CommentDto comment, BindingResult result) {
        commentValidator.validate(comment, result);
        NewsDto news = newsService.get(id);
        modelMap.addAttribute("news", news);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("comment", comment);
            return pageProperties.getSingleNewsPagePath();
        } else {
            modelMap.addAttribute("comment", comment);
            comment = commentService.save(comment, id);
            modelMap.addAttribute("comment", comment);
            List<CommentDto> comments = commentService.findCommentsByNewsId(id);
            modelMap.addAttribute("comments", comments);
            return pageProperties.getSingleNewsPagePath();
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNewsPage(@ModelAttribute NewsDto news, ModelMap modelMap) {
        modelMap.addAttribute("news", news);
        return pageProperties.getCreateNewsPagePath();
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNews(@ModelAttribute NewsDto newsDto, @RequestParam(value = "page", defaultValue = "1") Long page, BindingResult result, ModelMap modelMap) {
        validator.validate(newsDto, result);
        if (result.hasErrors()) {
            return pageProperties.getErrorsPagePath();
        }
        newsService.save(newsDto);
        modelMap.addAttribute("newsDto", newsDto);
        Long quantityOfNews = newsService.quantityOfNews();
        Long pagesQuantity = quantityOfPages(quantityOfNews, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<NewsDto> news = newsService.newsPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPage();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String deleteNews(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            newsService.deleteById(id);
        }
        return "redirect:/web/news";
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNewsPage(@PathVariable("id") Long id, ModelMap modelMap) {
        NewsDto news = newsService.get(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getUpdateNewsPagePath();
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNews(@PathVariable("id") Long id,
                             @ModelAttribute NewsDto news,
                             BindingResult result,
                             ModelMap modelMap) {

        news.setId(id);
        validator.validate(news, result);
        if (result.hasErrors()) {
            return pageProperties.getErrorsPagePath();
        } else {
            news = newsService.update(news);
            modelMap.addAttribute("news", news);
            return "redirect:/web/news";
        }
    }
}
