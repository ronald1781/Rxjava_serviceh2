package com.r2g.Rxjava_serviceh2.service.author;

import com.r2g.Rxjava_serviceh2.servicedto.request.AddAuthorRequest;
import io.reactivex.Single;

public interface AuthorService {
    Single<String> addAuthor(AddAuthorRequest addAuthorRequest);
}
