package com.bjit.bookapprestapi.repositories;

import com.bjit.bookapprestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthorName(String authorName);

    Optional<Book> findByBookNameAndAuthorName(String authorName, String bookName);


}