package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.controllers.validators.NewsValidator;
import com.gmail.nogovitsyndmitriy.exceptions.EntityNotFoundException;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.gmail.nogovitsyndmitriy.controllers.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final UserService userService;
    private final CommentService commentService;
    private final NewsValidator validator;

    @Autowired
    public NewsController(PageProperties pageProperties,
                          NewsService newsService,
                          UserService userService,
                          CommentService commentService,
                          NewsValidator validator) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.userService = userService;
        this.commentService = commentService;
        this.validator = validator;
    }


    @GetMapping
    public String getNews(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfNews = newsService.quantityOfNews();
        Long pagesQuantity = quantityOfPages(quantityOfNews, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<NewsDto> news = newsService.newsPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPage();
    }

    @GetMapping(value = "/{id}")
    public String getCurrentNews(@PathVariable("id") Long id, ModelMap modelMap) throws EntityNotFoundException {
        NewsDto news = newsService.get(id);
        if (news == null) {
            throw new EntityNotFoundException(id, "NOT_FOUND", "Entity not found.");
        }
        CommentDto comment = new CommentDto();
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("comment", comment);
        return pageProperties.getSingleNewsPagePath();
    }

    @PostMapping(value = "/comment/{news_id}")
    public String createComment(ModelMap modelMap, @PathVariable("news_id") Long id, @ModelAttribute CommentDto comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        NewsDto news = newsService.get(id);
        comment.setUserDto(userService.findByEmail(userPrincipal.getUsername()));
        comment.setNewsDto(news);
        comment.setCreated(LocalDateTime.now());
        comment = commentService.save(comment);
        modelMap.addAttribute("news", news);
        List<CommentDto> comments = commentService.findCommentsByNewsId(id);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("comment", comment);
        return pageProperties.getSingleNewsPagePath();
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
        Long quantityOfNews = newsService.quantityOfNews();
        Long pagesQuantity = quantityOfPages(quantityOfNews, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<NewsDto> news = newsService.newsPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("newsDto", newsDto);
        modelMap.addAttribute("pages", pagesQuantity);
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
