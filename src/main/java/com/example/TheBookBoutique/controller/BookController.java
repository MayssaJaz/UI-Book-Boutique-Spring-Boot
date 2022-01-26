package com.example.TheBookBoutique.controller;

import com.example.TheBookBoutique.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BookController {
    private static Set<Book> books=new HashSet<Book>();
    private static Set<Book> manageBooks(Book book){
        init();
        if (book != null) books.add(book);
        return books;
    }
    private static Set<Book> init(){

        books.add(new Book(1,"MA","LA",4,"romance"));
        books.add(new Book(2,"MA","LA",4,"horror"));
        books.add(new Book(4,"MA","LA",4,"adventure"));
        return books;
    }
    @GetMapping("/books")
    public String books(Model model){
        model.addAttribute("books",manageBooks(null));
        model.addAttribute("book",new Book());
        return "books_page";
    }
    @PostMapping("createBook")
    public String createBook(@ModelAttribute Book book){
        System.out.println("book ="+ book);
        //call API here
        return "redirect:/books";
    }
    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String handleDeleteBook(@RequestParam(name="bookId") String bookId) {
        System.out.println(bookId);
        System.out.println("test");
        //call API here
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book editingBook=new Book();
        editingBook.setId(id);
        model.addAttribute("book", editingBook);
        return "edit_book_page";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") int id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "edit_book_page";
        }
        Book bookEdit=(Book) model.getAttribute("book");
        String title=bookEdit.getTitle();
        System.out.println("titleeee"+title);

        //call API here
        return "redirect:/books";
    }

}
