package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final UserService userService;
    private final CommentService commentService;
    private final static int QUANTITY_ON_PAGE = 5;


    public NewsController(PageProperties pageProperties, NewsService newsService, UserService userService, CommentService commentService) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.userService = userService;
        this.commentService = commentService;
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
        CommentDto comment = new CommentDto();
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("comment", comment);
        return pageProperties.getSingleNewsPagePath();
    }

    @PostMapping(value = "/createComment/{id}")
    public String createComment(ModelMap modelMap, @PathVariable("id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        CommentDto comment = new CommentDto();
        NewsDto news = newsService.get(id);
        comment.setUserDto(userService.findByEmail(userPrincipal.getUsername()));
        comment.setNewsDto(news);
        comment.setCreated(LocalDateTime.now());
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        comments.add(comment);
        comment = commentService.save(comment);
        newsService.update(news);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("comment", comment);
        return pageProperties.getSingleNewsPagePath();
    }
}