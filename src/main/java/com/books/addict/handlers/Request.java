package com.books.addict.handlers;

import org.springframework.stereotype.Component;

@Component
public interface Request {
    void handle(String type);
    String getType();
}
