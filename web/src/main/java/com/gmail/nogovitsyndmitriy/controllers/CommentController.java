package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/web/comment")
public class CommentController {
    private final CommentService commentService;
    private final PageProperties pageProperties;

    @Autowired
    public CommentController(CommentService commentService,
                             PageProperties pageProperties) {
        this.commentService = commentService;
        this.pageProperties = pageProperties;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String getComments(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfComments = commentService.quantityOfComments();
        Long pageQuantity = PanginationUtil.quantityOfPages(quantityOfComments, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pageQuantity);
        List<CommentDto> comments = commentService.commentsPangination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("comments", comments);
        return pageProperties.getCommentsPagePath();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String deleteComment(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            commentService.deleteById(id);
        }
        return "redirect:/web/comment";
    }
}
