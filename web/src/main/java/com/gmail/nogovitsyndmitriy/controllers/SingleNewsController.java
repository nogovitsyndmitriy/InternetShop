package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/single_news")
public class SingleNewsController {
    @Autowired
    private final PageProperties pageProperties;

    public SingleNewsController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }
    @GetMapping
    public String readNews(@ModelAttribute NewsDto news, ModelMap modelMap){
        modelMap.addAttribute("news", news);
        return pageProperties.getSingleNewsPagePath();
    }
}
