package com.example.springboot_book2;

import jakarta.persistence.*;

// 書籍情報を表すエンティティクラス
@Entity
@Table(name = "books")
public class Book {

    // 主キー：書籍コード（自動採番）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_code")
    private Integer bookCode;

    // 書籍名（NULL不可）
    @Column(name = "book_name", nullable = false)
    private String bookName;

    // 書籍の価格
    @Column(name = "book_price", nullable = false)
    private int bookPrice;

    // 書籍に紐づく著者（多対一の関係）
    @ManyToOne(optional = false) // 書籍には必ず著者が必要
    @JoinColumn(name = "author_id", nullable = false) // booksテーブルにauthor_id列を持たせる
    private Author author;

    // JPA用のデフォルトコンストラクタ
    protected Book() {}

    // 新規登録用コンストラクタ
    public Book(String bookName, int bookPrice, Author author) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.author = author;
    }

    // 更新用コンストラクタ
    public Book(Integer bookCode, String bookName, int bookPrice, Author author) {
        this(bookName, bookPrice, author);
        this.bookCode = bookCode;
    }

    // ゲッター（フィールドの値を取得）
    public Integer getBookCode() { return bookCode; }
    public String getBookName() { return bookName; }
    public int getBookPrice() { return bookPrice; }
    public Author getAuthor() { return author; }
}
