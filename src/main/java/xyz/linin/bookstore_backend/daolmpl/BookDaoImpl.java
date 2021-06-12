package xyz.linin.bookstore_backend.daolmpl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.BookDao;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.repository.BookRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    public Book find(Integer id){
        return bookRepository.findById(id).get();
    }
    public boolean delete(Integer id) {
        if (!bookRepository.existsById(id)) return false;
        bookRepository.deleteById(id);
        return true;
    }
    public boolean edit(Integer id, BookDto bookDto) {
        Book book;
        if (!bookRepository.existsById(id)) {
            book = new Book();
        }
        else book = bookRepository.findById(id).get();
        modelMapper.map(bookDto, book);
        bookRepository.save(book);
        return true;
    }
    public List<Book> all() {
        return bookRepository.findAll();
    }
    public boolean add(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        return true;
    }
}
