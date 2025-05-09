package com.example.springboot_book2;

import jakarta.persistence.*;

// 著者情報を表すエンティティクラス
@Entity
@Table(name = "authors")
public class Author {

    // 主キー：著者ID（自動採番）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    // 著者名（重複不可）
    @Column(name = "author_name", nullable = false, unique = true)
    private String authorName;

    // JPAで使用するためのデフォルトコンストラクタ
    protected Author() {}

    // コンストラクタ（著者名のみ指定）
    public Author(String authorName) {
        this.authorName = authorName;
    }

    // ゲッター（他のクラスから値を取得するため）
    public Integer getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
}
