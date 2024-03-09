package com.test.web.dto.album;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Album {
    private int userId;
    private int id;
    private String title;
}
