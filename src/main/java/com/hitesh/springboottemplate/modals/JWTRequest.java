package com.hitesh.springboottemplate.modals;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JWTRequest {
    private String email;
    private String password;
}
