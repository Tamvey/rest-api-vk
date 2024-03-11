package com.test.web.enitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String enabled;

    @Column
    private String date;

    @Column
    private String requestType;

    @Column
    private String endpoint;

    @Column
    private String result;

    public Audit(String username, String enabled, String date, String requestType, String endpoint, String result) {
        this.username = username;
        this.enabled = enabled;
        this.date = date;
        this.requestType = requestType;
        this.endpoint = endpoint;
        this.result = result;
    }

}
