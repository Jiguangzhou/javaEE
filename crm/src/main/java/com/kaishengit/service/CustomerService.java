package com.kaishengit.service;

import com.kaishengit.mapper.CustomerMapper;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CustomerService {

    @Inject
    private CustomerMapper customerMapper;

}
