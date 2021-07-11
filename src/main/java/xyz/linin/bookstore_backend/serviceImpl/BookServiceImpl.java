package xyz.linin.bookstore_backend.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.BookDao;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private ModelMapper modelMapper;

    public Book find(Integer id) {
        return bookDao.findById(id);
    }

    public void delete(Integer id) {
        if (!bookDao.existsById(id)) throw new BusinessLogicException("书籍不存在");
        bookDao.deleteById(id);
    }

    public List<Book> all() {
        return bookDao.findAll();
    }

    public void add(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookDao.save(book);
    }

    public void edit(Integer id, BookDto bookDto) {
        Book book;
        if (!bookDao.existsById(id)) {
            book = new Book();
        }
        else book = bookDao.findById(id);
        modelMapper.map(bookDto, book);
        bookDao.save(book);
    }
}
