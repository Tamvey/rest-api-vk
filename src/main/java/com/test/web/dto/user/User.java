package com.test.web.dto.user;

import com.test.web.dto.user.Address;
import com.test.web.dto.user.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
