package com.bjit.bookapprestapi.services.impl;

import com.bjit.bookapprestapi.entity.Book;
import com.bjit.bookapprestapi.exceptions.ResourceNotFoundException;
import com.bjit.bookapprestapi.models.BookDto;
import com.bjit.bookapprestapi.repositories.BookRepo;
import com.bjit.bookapprestapi.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = this.dtoToBook(bookDto);
        Book savedBook = this.bookRepo.save(book);
        return this.booktoDto(savedBook);


    }

    @Override
    public BookDto updateBook(BookDto bookDto, Integer id) {
        Book book = this.bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "Id", id));

        book.setBookName(bookDto.getBookName());
        book.setSummary(bookDto.getSummary());
        book.setAuthorName(bookDto.getAuthorName());

        Book updatedBook = this.bookRepo.save(book);

        BookDto updatedBookDto =  this.booktoDto(updatedBook);

        return updatedBookDto;
    }

    @Override
    public BookDto getBookById(Integer id) {
        Book book = this.bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "Id", id));

        return this.booktoDto(book);
    }

    @Override
    public BookDto getBooksByAuthorAndBookName(String authorName,String bookName) {
        Book book = bookRepo.findByBookNameAndAuthorName(authorName, bookName).orElseThrow(() -> new ResourceNotFoundException("bookName", "Id",1));
        return this.booktoDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books =  this.bookRepo.findAll();
        List<BookDto> bookDto = books.stream().map(book -> this.booktoDto(book)).collect(Collectors.toList());
        return bookDto;
    }

    @Override
    public List<BookDto> getBooksByAutherName(String authorName) {
        List<Book> books =  bookRepo.findAllByAuthorName(authorName);
        List<BookDto> bookDto = books.stream().map(book -> this.booktoDto(book)).collect(Collectors.toList());
        return bookDto;
    }

    @Override
    public void deleteBook(Integer bookId) {

        bookRepo.deleteById(bookId);

    }

    @Override
    public void updateQuantity(Integer bookId, Integer quantity) {
        var book = bookRepo.findById(bookId).get();
        book.setQuantity(book.getQuantity()-quantity);
        bookRepo.save(book);
    }

    private Book dtoToBook(BookDto bookDto){
        Book book = this.modelMapper.map(bookDto,Book.class);
        return book;
    }

    private BookDto booktoDto(Book book){
        BookDto bookDto = this.modelMapper.map(book,BookDto.class);
        return bookDto;
    }
}