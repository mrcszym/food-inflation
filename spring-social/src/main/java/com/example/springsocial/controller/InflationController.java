package com.example.springsocial.controller;

import com.example.springsocial.model.Inflation;
import com.example.springsocial.repository.InflationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InflationController {

    @Autowired
    private InflationRepository inflationRepository;

    @GetMapping("/inflation/{month}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Inflation getInflationByMonth(@PathVariable String month){
        return inflationRepository.findInflationByMonth(month);
    }
}
