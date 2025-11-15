package library_management.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId){
        // get book and user by id or else return null
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        // book must exist
        // book must not be borrowed
        // user must exist
        if (book != null && !book.isBorrowed() && user != null) {
            book.setBorrowedBy(user);   // set relationship ie user who borrowed book
            book.setBorrowed(true);     // mark book borrowed in database
            return save(book);          // persist database and return book object
        }

        // Handle errors (e.g., book not found, book already borrowed, user not found)
        return null;
    }

    public Book returnBook(Long bookId){
        // get book by id or else return null
        Book book = bookRepository.findById(bookId).orElse(null);

        // book must exist
        // book must be marked borrowed
        if(book != null && book.isBorrowed()){
            book.setBorrowedBy(null);   // remove relation ie user who borrowed book
            book.setBorrowed(false);    // mark book not borrowed
            return save(book);          // persist data in database and return the book object
        }

        // Handle errors (e.g., book not found, book not borrowed)
        return null;
    }
}
