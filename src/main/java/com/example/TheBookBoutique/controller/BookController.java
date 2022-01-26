package com.example.TheBookBoutique.controller;

import com.example.TheBookBoutique.model.Book;
import com.example.TheBookBoutique.model.BookEdit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.json.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private static Set<Book> books=new HashSet<Book>();
    private static Set<Book> manageBooks(Book book){
        init();
        if (book != null) books.add(book);
        return books;
    }
    private static Set<Book> init(){
        books=new HashSet<>();
        final String uri = "http://localhost:8090/books";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> responseEntity =
                restTemplate.getForEntity(uri, Object[].class);
        Object[] objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<Book> list = new ArrayList<Book>();
        for (Object o : objects) {
            Book book = mapper.convertValue(o, Book.class);
            books.add(book);
        }
        return books;
    }
    @GetMapping("/books")
    public String books(Model model){
        model.addAttribute("books",manageBooks(null));
        model.addAttribute("book",new Book());
        return "books_page";
    }
    @PostMapping("createBook")
    public String createBook(@ModelAttribute Book book,BindingResult result){
        if (result.hasErrors()) {
            return "redirect:/books";
        }
        final String uri = "http://localhost:8090/books/create";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, book, Book.class);
        return "redirect:/books";

    }
    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String handleDeleteBook(@RequestParam(name="bookId") String bookId) {
        final String uri = "http://localhost:8090/books/delete";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri+"/"+bookId);
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
        final String uri = "http://localhost:8090/books/update";
        Book bookEdit=(Book) model.getAttribute("book");
        String title=bookEdit.getTitle();
        String author=bookEdit.getAuthor();
        int quantity=bookEdit.getQuantity();
        String genre=bookEdit.getGenre();
        BookEdit editedBook=new BookEdit(title,author,quantity,genre);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri+"/"+id, editedBook,BookEdit.class);
        return "redirect:/books";
    }

}
