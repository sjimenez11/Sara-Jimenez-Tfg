package com.connectedReads.services;

import com.connectedReads.repositories.ReadingListBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ReadingListBookService {
    @Autowired
    private ReadingListBookRepository readingListBookRepository;
}
