package xyz.linin.bookstore_backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    private final BookService bookService;

    @GetMapping
    public DataResponse<List<Book>> getAll() {
        return new DataResponse<>(bookService.all());
    }

    @GetMapping("/{bookId}")
    public DataResponse<Book> getById(@PathVariable Integer bookId) {
        return new DataResponse<>(bookService.find(bookId));
    }

    @ApiOperation("修改书本信息")
    @PutMapping("/{bookId}")
    public ResponseEntity<?> edit(@PathVariable Integer bookId, @Valid @RequestBody BookDto bookDto) {
        bookService.edit(bookId, bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("删除书本")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> del(@PathVariable Integer bookId) {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("添加书本")
    @PostMapping("/import")
    public ResponseEntity<?> importUser(@Valid @RequestBody BookDto bookDto) {
        bookService.add(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
