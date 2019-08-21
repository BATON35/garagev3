package com.konrad.garagev3.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnonymousUserQuestion {
    private String message;
    private String name;
    private String mail;
}
