package xyz.linin.bookstore_backend.daolmpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.BookDao;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.repository.BookRepository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    public Book findById(Integer id) {
        return bookRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return bookRepository.existsById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
