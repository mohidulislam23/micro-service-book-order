package com.bjit.bookapprestapi.contorllers;


import com.bjit.bookapprestapi.models.ApiResponse;
import com.bjit.bookapprestapi.models.BookDto;
import com.bjit.bookapprestapi.repositories.BookRepo;
import com.bjit.bookapprestapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepo bookRepo;

    @PostMapping("/create")
    public ResponseEntity<BookDto> createUser(@Valid @RequestBody BookDto bookDto){
        BookDto createdBookDto = this.bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBookDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto,@PathVariable Integer bookId){

        BookDto updatedBook =  this.bookService.updateBook(bookDto,bookId);

        return ResponseEntity.ok(updatedBook);

    }

    @PutMapping("/decreaseQuantity/{bookId}/{quantity}")
    public String decreaseQuantity (@PathVariable Integer bookId,@PathVariable Integer quantity){

        bookService.updateQuantity(bookId,quantity);

        return  "cross-check";

    }


    @DeleteMapping("delete/{bookId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer bookId){

        this.bookService.deleteBook(bookId);

        return new ResponseEntity(new ApiResponse("Book Deleted Successfully",true),HttpStatus.OK);

    }


    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        System.out.println(this.bookService.getAllBooks());
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

//    @GetMapping("/author/{author_name}")
//    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String author_name) {
//
//        return ResponseEntity.ok(this.bookService.getBooksByAutherName(author_name));
////        return bookRepository.findByAuthorName(authorName);
//    }



    @GetMapping("/{author_name}/{book_name}")
    public ResponseEntity<BookDto> getBooksByAuthorAndBookName(@PathVariable String author_name,@PathVariable String book_name) {

        return ResponseEntity.ok(this.bookService.getBooksByAuthorAndBookName(author_name,book_name));

    }


    @GetMapping("/id/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer bookId){
        return ResponseEntity.ok(this.bookService.getBookById(bookId));
    }


    @GetMapping("/id")
    public String  getBookById(){

        return "cross-check";
    }




}