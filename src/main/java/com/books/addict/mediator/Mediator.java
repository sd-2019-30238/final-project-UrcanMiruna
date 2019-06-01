package com.books.addict.mediator;
import com.books.addict.handlers.Request;
import org.springframework.stereotype.Component;

@Component
public interface Mediator {
    void handle(Request request);
    void addHandler(Request request);
}
