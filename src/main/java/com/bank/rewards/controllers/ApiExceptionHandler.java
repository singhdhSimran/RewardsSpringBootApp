package com.bank.rewards.controllers;

import com.bank.rewards.constants.ErrorMessages;
import com.bank.rewards.exceptions.ConfigurationException;
import com.bank.rewards.exceptions.NoRecordFoundException;
import com.bank.rewards.models.ApiError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ApiExceptionHandler {

    private static Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    public ApiError handleNoRecordFoundExceptions(NoRecordFoundException ex) {
        logger.debug("No record exception", ex);
        return new ApiError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConfigurationException.class)
    @ResponseBody
    public ApiError handleConfigurationException(ConfigurationException ex) {
        logger.debug("Configuration exception", ex);
        return new ApiError(ErrorMessages.APP_ISSUE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ApiError handleCConstraintViolationException(ConstraintViolationException ex) {
        logger.debug("Constraint Violation Exception", ex);
        return new ApiError(ex.getMessage());
    }
}
