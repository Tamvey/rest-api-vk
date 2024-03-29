package com.test.web.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
