package com.hitesh.springboottemplate.modals;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JWTResponse {
    private String token;
    private String email;
}
