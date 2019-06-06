package com.books.addict.service.readService;

import com.books.addict.model.Book;
import com.books.addict.model.FeedBackRepository;
import com.books.addict.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class FeedbackServiceRImpl implements FeedbackServiceR {

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

    @Override
    public List<String> getFeedDescr(Integer id) {
        List<Feedback> feedbacks= (List<Feedback>) feedBackRepository.findAll();
        List<String> descroptions=new ArrayList<>();
        for(Feedback feedback:feedbacks){
            descroptions.add(feedback.getDescription());
        }
        return descroptions;
    }

    @Override
    public Optional<Feedback> getFeedById(Integer id) {
        return feedBackRepository.findById(id);
    }
}
