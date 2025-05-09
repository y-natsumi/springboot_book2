まず作成されているBOOKSテーブル削除
DROP TABLE books;

テーブル再作成
CREATE TABLE books (
  book_code INT AUTO_INCREMENT PRIMARY KEY,
  book_name VARCHAR(255) NOT NULL,
  book_price INT NOT NULL,
  author_id INT NOT NULL
);

著者テーブル作成
CREATE TABLE authors (
  author_id   INT AUTO_INCREMENT PRIMARY KEY,
  author_name VARCHAR(255) NOT NULL UNIQUE
);

booksテーブルへデータインサート
INSERT INTO books (book_name, book_price, author_id) VALUES
('Javaプログラミング基礎', 2500, 1),
('データベース入門', 3200, 2),
('はじめてのWebアプリケーション', 3700, 3),
('コンピュータリテラシー', 1500, 4);

authorsテーブルへデータインサート
INSERT INTO authors (author_name) VALUES
  ('山田四郎'),
  ('佐藤一郎'),
  ('田中二郎'),
  ('鈴木三郎');
