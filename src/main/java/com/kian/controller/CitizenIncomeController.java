package com.kian.controller;

import com.kian.exception.InvalidWeeklyIncomeException;
import com.kian.model.Frequency;
import com.kian.model.RegularAmount;
import com.kian.service.IncomeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@Controller
public class CitizenIncomeController {
    private static final String SUCCESS = "Your income has been added successfully";

    @Autowired
    private IncomeValidator validator;

    @GetMapping("/addIncome")
    public String showForm(final Model model) {
        final RegularAmount regularAmount = new RegularAmount();
        model.addAttribute("regularAmount", regularAmount);
        final List<Frequency> frequencyList= Arrays.asList(Frequency.values());
        model.addAttribute("frequencyList", frequencyList);
        return "addIncome";
    }

    @PostMapping(value = "/addIncome")
    public ResponseEntity<String> submitForm(@ModelAttribute RegularAmount regularAmount) {
        validator.validate(regularAmount);
        return ResponseEntity.ok()
                .body(SUCCESS);
    }

    @ExceptionHandler(InvalidWeeklyIncomeException.class)
    public ResponseEntity<String> handleInvalidMatchStoreException(final InvalidWeeklyIncomeException e, final WebRequest request) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
