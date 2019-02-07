package com.konrad.garagev3.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnonymousUserQuestion {
    private String message;
    private String name;
    private String mail;
}