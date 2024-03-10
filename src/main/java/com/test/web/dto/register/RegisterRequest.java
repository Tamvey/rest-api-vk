package com.test.web.dto.register;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor

public class RegisterRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String authority;
    @NonNull
    private int enabled;
    @NonNull
    private String email;
}
