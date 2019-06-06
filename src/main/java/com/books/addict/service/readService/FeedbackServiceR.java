package com.books.addict.service.readService;

import com.books.addict.model.Book;
import com.books.addict.model.Feedback;

import java.util.List;

public interface FeedbackServiceR {
    List<Feedback> getAllFeedbacks();
    Double rates(Book book);
    List<String> getFeedDescr(Integer id);
}
