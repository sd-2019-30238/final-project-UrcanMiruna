package com.books.addict.service.writeService;

import com.books.addict.model.Reader;
import com.books.addict.model.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReaderServiceWImpl implements ReaderServiceW {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public void addReader(Reader reader) {
        readerRepository.save(reader);
    }

    @Override
    public void updateReader(Reader reader) {
        readerRepository.save(reader);
    }
}
