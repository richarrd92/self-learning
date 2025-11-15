package library_management.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        Book bookToUpdate = new Book();
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setBorrowed(book.isBorrowed());
        bookToUpdate.setBorrowedBy(bookToUpdate.getBorrowedBy());
        bookToUpdate.setTitle(bookToUpdate.getTitle());

        return bookService.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
    }

    // response entity helps in customization of the response sent back via HTTP
    // instead of simply sending an object back
    // a custom message and code can be sent back too
    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId){
        Book borrowedBook = bookService.borrowBook(bookId, userId);
        if (borrowedBook != null){
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId){
        Book returnedBook = bookService.returnBook(bookId);
        if(returnedBook != null){
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
