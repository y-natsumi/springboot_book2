package com.example.springboot_book2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// Bookエンティティを操作するためのリポジトリインターフェース
public interface BookRepository extends JpaRepository<Book, Integer> {
    // 書籍コードによる検索
    List<Book> findByBookCode(Integer code);
    // 書籍名による曖昧検索
    List<Book> findByBookNameLike(String pattern);
    // 著者名による曖昧検索
    List<Book> findByAuthor_AuthorNameLike(String pattern);
}