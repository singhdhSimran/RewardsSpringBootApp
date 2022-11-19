package com.bank.rewards.controllers;

import com.bank.rewards.services.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/banking/v1/customers")
@Validated
public class CustomerController {

    @Autowired
    private PointsService pointsService;

    @GetMapping(value = "/{id}/points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> calculatePointsByRange(
            @PathVariable("id") Long customerId,
            @NotNull @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @NotNull @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return new ResponseEntity<>(this.pointsService.getPointsByCustomerIdAndBetweenDates(customerId, fromDate, toDate), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/points/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> calculatePointsByMonth(
            @PathVariable("id") Long customerId,
            @PathVariable("month") @Min(1) @Max(12) Integer month) {
        return new ResponseEntity<>(this.pointsService.getPointsByCustomerIdAndMonth(customerId, month), HttpStatus.OK);
    }

}
