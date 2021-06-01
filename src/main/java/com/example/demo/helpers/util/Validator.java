package com.example.demo.helpers.util;

import java.util.List;

public class Validator {

    private Validator(){
    }

    public static boolean validateStringValue(String value){
        return !(value == null || value.isBlank());
    }

    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }
}
