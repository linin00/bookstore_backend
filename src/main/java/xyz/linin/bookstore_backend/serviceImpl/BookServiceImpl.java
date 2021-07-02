package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.BookDao;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public Book find(Integer id) {
        return bookDao.find(id);
    }

    public boolean delete(Integer id) {
        return bookDao.delete(id);
    }

    public boolean edit(Integer id, BookDto bookDto) {
        return bookDao.edit(id, bookDto);
    }

    public List<Book> all() {
        return bookDao.all();
    }

    public boolean add(BookDto bookDto) {
        return bookDao.add(bookDto);
    }
}
