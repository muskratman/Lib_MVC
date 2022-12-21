package com.cookiebros.libmvc.controllers;


import com.cookiebros.libmvc.services.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
    private final BooksServiceImpl booksService;
    @Autowired
    public MainController(BooksServiceImpl booksService) {
        this.booksService = booksService;
    }


    @GetMapping("")
    public String mainPage(Model model,
                           @RequestParam(value = "search_query", required = false) String query) {
        model.addAttribute("query", query);
        model.addAttribute("books", booksService.findByTitleStartingWith(query));
        return "index";
    }
}
