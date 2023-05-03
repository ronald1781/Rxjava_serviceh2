package com.r2g.Rxjava_serviceh2.service.book;

import com.r2g.Rxjava_serviceh2.servicedto.request.AddBookRequest;
import com.r2g.Rxjava_serviceh2.servicedto.request.UpdateBookRequest;
import com.r2g.Rxjava_serviceh2.servicedto.response.BookResponse;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface BookService {
    Single<String> addBook(AddBookRequest addBookRequest);

    Completable updateBook(UpdateBookRequest updateBookRequest);

    Single<List<BookResponse>> getAllBooks(int limit, int page);

    Single<BookResponse> getBookDetail(String id);

    Completable deleteBook(String id);
}
