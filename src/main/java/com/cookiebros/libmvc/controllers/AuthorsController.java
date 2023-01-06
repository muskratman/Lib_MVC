package com.cookiebros.libmvc.controllers;

import com.cookiebros.libmvc.models.Author;
import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Country;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.services.AuthorsService;
import com.cookiebros.libmvc.services.CountryService;
import com.cookiebros.libmvc.services.SearchService;
import com.cookiebros.libmvc.util.AuthorValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;
    private final CountryService countryService;
    private final SearchService searchService;
    private final AuthorValidatorImpl authorValidator;

    @Autowired
    public AuthorsController(AuthorsService authorsService, AuthorValidatorImpl authorValidator, SearchService searchService, CountryService countryService) {
        this.authorsService = authorsService;
        this.authorValidator = authorValidator;
        this.searchService = searchService;
        this.countryService = countryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", authorsService.findAll());
        return "authors/index";
    }



    //CRUD
    //CRUD
    //CRUD
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("author") Author author) {
        model.addAttribute("author", authorsService.findById(id));
        model.addAttribute("books", authorsService.findById(id).getBooks());
        return "authors/show";
    }
    @GetMapping("/new")
    public String newAuthor(Model model,
                            @ModelAttribute("author") Author author,
                            @ModelAttribute("country") Country country) {
        model.addAttribute("countries", countryService.findAll());
        return "authors/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("author") Author author, BindingResult bindingResult) {
        authorValidator.validate(author, bindingResult);

        if (bindingResult.hasErrors())
            return "authors/new";
        authorsService.save(author);
        return "redirect:/authors";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("country") Country country) {
        model.addAttribute("author", authorsService.findById(id));
        model.addAttribute("countries", countryService.findAll());
        return "authors/edit";
    }



    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("author") Author author, BindingResult bindingResult) {
        authorValidator.validate(author, bindingResult);

        if (bindingResult.hasErrors())
            return "authors/edit";

        authorsService.update(id, author);
        return "redirect:/authors";
    }



    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        authorsService.delete(id);
        return "redirect:/books";
    }



    //OTHER
    //OTHER
    //OTHER
    @PostMapping("/{id}/add_book")
    public String addBook(@PathVariable("id") int id,
                           @ModelAttribute("book") Book book) {
        
//        booksService.addOwner(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove_book")
    public String removeBook(@PathVariable("id") int id) {
//        booksService.removeOwner(id);
        return "redirect:/books/" + id;
    }

}
