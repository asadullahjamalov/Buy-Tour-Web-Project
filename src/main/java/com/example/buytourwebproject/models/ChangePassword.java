package com.example.buytourwebproject.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ChangePassword {
    private String newPassword;
    private String newPasswordValidator;
}
