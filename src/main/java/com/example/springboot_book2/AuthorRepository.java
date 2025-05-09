package com.example.springboot_book2;

import org.springframework.data.jpa.repository.JpaRepository;

// Authorエンティティを操作するためのリポジトリインターフェース
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // 著者名で検索（一意）
    Author findByAuthorName(String authorName);
}
