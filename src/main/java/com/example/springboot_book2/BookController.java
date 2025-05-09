package com.example.springboot_book2;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// Web画面からのリクエストを処理するコントローラー
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // トップページ（書籍一覧表示）
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.addObject("bookList", bookRepository.findAll()); // 全書籍取得
        mv.setViewName("books"); // 表示するHTML名
        return mv;
    }

    // 検索処理（書籍コード、書籍名、著者名）
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(
        @RequestParam(name = "code", required = false) Integer code,
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "author", required = false) String author,
        ModelAndView mv) {

        List<Book> bookList;

        if (code != null && code != 0) {
            bookList = bookRepository.findByBookCode(code); // 書籍コード検索
        } else if (name != null && !name.isBlank()) {
            bookList = bookRepository.findByBookNameLike("%" + name + "%"); // 曖昧検索
        } else if (author != null && !author.isBlank()) {
            bookList = bookRepository.findByAuthor_AuthorNameLike("%" + author + "%");
        } else {
            bookList = bookRepository.findAll(); // 条件なしは全件表示
        }

        if (bookList.isEmpty()) {
            mv.addObject("errorMsg", "該当するデータはありません");
        } else {
            mv.addObject("bookList", bookList);
        }

        mv.setViewName("books");
        return mv;
    }

    // 書籍更新画面の表示（価格だけ変更）
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(
        @RequestParam("code") int bookCode,
        @RequestParam("price") int bookPrice,
        ModelAndView mv) {
        mv.addObject("bookCode", bookCode);
        mv.addObject("bookPrice", bookPrice);
        mv.setViewName("book_edit");
        return mv;
    }

    // 更新処理（価格のみ更新）
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(
        @RequestParam("code") int bookCode,
        @RequestParam("price") int bookPrice,
        ModelAndView mv) {

        Book book = bookRepository.findByBookCode(bookCode).get(0);
        Book updated = new Book(book.getBookCode(), book.getBookName(), bookPrice, book.getAuthor());
        bookRepository.save(updated); // 上書き保存

        mv.addObject("bookList", bookRepository.findAll());
        mv.setViewName("books");
        return mv;
    }

    // 書籍削除
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("code") int bookCode, ModelAndView mv) {
        bookRepository.deleteById(bookCode); // 主キーで削除
        mv.addObject("bookList", bookRepository.findAll());
        mv.setViewName("books");
        return mv;
    }

    // 登録画面を表示
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "book_insert";
    }

    // 書籍の新規登録
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(
        @RequestParam("name") String bookName,
        @RequestParam("price") int bookPrice,
        @RequestParam("author") String authorName,
        ModelAndView mv) {

        // 著者名から既存著者を検索し、なければ新規作成
        Author author = authorRepository.findByAuthorName(authorName);
        if (author == null) {
            author = authorRepository.save(new Author(authorName));
        }

        // 書籍を保存
        bookRepository.save(new Book(bookName, bookPrice, author));
        mv.addObject("bookList", bookRepository.findAll());
        mv.setViewName("books");
        return mv;
    }
}
