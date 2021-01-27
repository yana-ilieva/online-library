package com.example.online_library.controller;

import com.example.online_library.service.RentedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentedBooks")
public class RentedBookController {

    private final RentedBookService rentedBookService;

    @Autowired
    public RentedBookController(RentedBookService rentedBookService) {
        this.rentedBookService = rentedBookService;
    }
}

