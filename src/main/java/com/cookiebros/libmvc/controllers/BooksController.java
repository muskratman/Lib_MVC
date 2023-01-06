package com.cookiebros.libmvc.controllers;

import com.cookiebros.libmvc.models.Book;
import com.cookiebros.libmvc.models.BookInfo;
import com.cookiebros.libmvc.models.Person;
import com.cookiebros.libmvc.services.BooksInfoService;
import com.cookiebros.libmvc.services.BooksService;
import com.cookiebros.libmvc.services.PeopleService;
import com.cookiebros.libmvc.util.BookInfoValidatorImpl;
import com.cookiebros.libmvc.util.BookValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BooksInfoService bookInfoService;
    private final BookValidatorImpl bookValidator;
    private final BookInfoValidatorImpl bookInfoValidator;


    @Autowired
    public BooksController(BooksService booksService,
                           PeopleService peopleService,
                           BooksInfoService bookInfoService,
                           BookValidatorImpl bookValidator,
                           BookInfoValidatorImpl bookInfoValidator){
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookInfoService = bookInfoService;
        this.bookValidator = bookValidator;
        this.bookInfoValidator = bookInfoValidator;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort", required = false) String sort) {

        model.addAttribute("page", page);
        model.addAttribute("per_page", booksPerPage);
        model.addAttribute("sort", sort);
        model.addAttribute("books", booksService.findAll(page, booksPerPage, sort));
        return "books/index";
    }




    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book,
                          @ModelAttribute("bookInfo")BookInfo bookInfo) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @ModelAttribute("bookInfo")BookInfo bookInfo,
                         BindingResult bindingResult1) {
        bookValidator.validate(book, bindingResult);
        bookInfoValidator.validate(bookInfo, bindingResult1);

        if (bindingResult.hasErrors() || bindingResult1.hasErrors())
            return "books/new";
        booksService.save(book, bookInfo);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("bookInfo", booksService.findById(id).getBookInfo());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @ModelAttribute("bookInfo") BookInfo bookInfo,
                         BindingResult bindingResult1) {
        bookValidator.validate(book, bindingResult);
        bookInfoValidator.validate(book, bindingResult1);

        if (bindingResult.hasErrors() || bindingResult1.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        bookInfoService.update(id, bookInfo);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }





    @PostMapping("/{id}/add_owner")
    public String addOwner(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person) {
//        booksService.addOwner(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
//        booksService.removeOwner(id);
        return "redirect:/books/" + id;
    }


}