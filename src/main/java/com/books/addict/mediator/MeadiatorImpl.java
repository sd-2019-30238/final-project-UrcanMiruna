package com.books.addict.mediator;

import com.books.addict.handlers.Request;
import com.books.addict.model.Reader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeadiatorImpl implements Mediator {
    private static List<Request> requests = new ArrayList<>();

    @Override
    public void handle(Request request) {
        System.out.println(requests.size());
        for(Request request1:requests){
            if(request1.getType().equalsIgnoreCase(request.getType())){
                request1.handle(request1);
            }
        }


    }

    @Override
    public void addHandler(Request request) {
        requests.add(request);
    }
}
