package com.books.addict.service;

import com.books.addict.model.Book;
import com.books.addict.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Double rates(Book book);

}
