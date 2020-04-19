package com.letswork.api.app.category.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum CategoryType {

    ADMINISTRATION("Administracja"),
    BANKING("Bankowość"),
    IT_SOFTWARE_DEVELOPMENT("IT - Rozwój oprogramowania"),
    MARKETING("Marketing");

    String name;
}
