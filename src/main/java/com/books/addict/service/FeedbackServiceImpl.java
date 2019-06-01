package com.books.addict.service;

import com.books.addict.model.Book;
import com.books.addict.model.FeedBackRepository;
import com.books.addict.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return (List<Feedback>) feedBackRepository.findAll();
    }

    @Override
    public Double rates(Book book) {
        List<Feedback> feedbacks = (List<Feedback>) feedBackRepository.findAll();
        int sum=0;
        for(Feedback feedback:feedbacks){
            sum+=feedback.getRate();
        }
        return Double.valueOf(1.0*sum/1.0*feedbacks.size());
    }
}
