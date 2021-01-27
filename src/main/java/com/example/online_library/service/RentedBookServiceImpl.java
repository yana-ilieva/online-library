package com.example.online_library.service;

import com.example.online_library.repository.RentedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentedBookServiceImpl implements RentedBookService {

    private final RentedBookRepository rentBookRepository;

    @Autowired
    public RentedBookServiceImpl(RentedBookRepository rentBookRepository) {
        this.rentBookRepository = rentBookRepository;
    }
}
