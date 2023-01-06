package com.cookiebros.libmvc.controllers;

import com.cookiebros.libmvc.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("")
    public String search(Model model,
                         @RequestParam(value = "search_query", required = false) String query) {
        model.addAttribute("query", query);
        model.addAttribute("books", searchService.findBooks(query));
        model.addAttribute("authors", searchService.findAuthors(query));
        return  "search";
    }
}
