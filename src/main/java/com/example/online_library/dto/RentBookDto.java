package com.example.online_library.dto;

import java.util.Date;

public class RentBookDto {

    private Date rentDate;

    private Date returnDate;

    private ViewBookDto book;

    private ViewUserDto operator;

    private ViewBookDto reader;
}
