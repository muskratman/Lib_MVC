package com.cookiebros.libmvc.controllers;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.services.BooksServiceImpl;
import com.cookiebros.libmvc.services.PeopleServiceImpl;
import com.cookiebros.libmvc.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksServiceImpl booksService;
    private final PeopleServiceImpl peopleService;
    private final BookValidator bookValidator;


    @Autowired
    public BooksController(BooksServiceImpl booksService, PeopleServiceImpl peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort", required = false) Boolean sortByYear) {

        model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book thisBook = booksService.findById(id);
        model.addAttribute("book", thisBook);
        Person owner = (thisBook.getOwner() != null) ? thisBook.getOwner(): null;
        model.addAttribute("owner", owner);
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/add_owner")
    public String addOwner(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person) {
        booksService.addOwner(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.removeOwner(id);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }


    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(value = "search_query", required = false) String query) {
        model.addAttribute("query", query);
        model.addAttribute("books", booksService.findByTitleStartingWith(query));
        return  "books/search";
    }
}