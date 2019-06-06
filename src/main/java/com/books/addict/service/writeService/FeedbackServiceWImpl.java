package com.books.addict.service.writeService;

import com.books.addict.model.Book;
import com.books.addict.model.FeedBackRepository;
import com.books.addict.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class FeedbackServiceWImpl implements FeedbackServiceW {

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public void addFeedback(Feedback feedback) {
        feedBackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Feedback feedback) {
        feedBackRepository.delete(feedback);
    }
}
