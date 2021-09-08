SELECT noOfCopies FROM tbl_book_copies WHERE branchID = (SELECT branchID FROM tbl_library_branch WHERE branchName = 'Sharpstown') AND bookId = (SELECT bookId FROM tbl_book WHERE title = 'The Lost Tribe');

SELECT branchId, noOfCopies FROM tbl_book_copies WHERE bookID = (SELECT bookID FROM tbl_book WHERE title = 'The Lost Tribe');

SELECT name FROM tbl_borrower WHERE cardNo NOT IN(SELECT cardNo FROM tbl_book_loans);

SELECT * FROM (SELECT tbl_book.bookId, title, branchId, dueDate FROM tbl_book JOIN tbl_book_loans ON tbl_book.bookId = tbl_book_loans.bookId) AS a JOIN tbl_library_branch AS b WHERE a.branchId = b.branchId AND b.branchName = 'Sharpstown' AND dueDate = curDate();

SELECT tbl_library_branch.branchName, COUNT(tbl_book_loans.branchId) FROM tbl_library_branch LEFT JOIN tbl_book_loans ON tbl_library_branch.branchId = tbl_book_loans.branchId GROUP BY tbl_library_branch.branchId;

SELECT name, address, COUNT(tbl_book_loans.cardNo) FROM tbl_borrower LEFT JOIN tbl_book_loans ON tbl_borrower.cardNo = tbl_book_loans.cardNo GROUP BY tbl_borrower.cardNo HAVING COUNT(tbl_book_loans.cardNo)>=5;

SELECT * FROM tbl_book LEFT JOIN tbl_author ON tbl_author.authorID = tbl_book.authId WHERE authorName = 'Stephen King';
