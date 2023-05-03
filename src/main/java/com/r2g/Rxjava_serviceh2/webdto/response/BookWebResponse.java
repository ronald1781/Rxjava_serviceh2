package com.r2g.Rxjava_serviceh2.webdto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookWebResponse {
    private String id;
    private String title;
    private String authorName;
}
