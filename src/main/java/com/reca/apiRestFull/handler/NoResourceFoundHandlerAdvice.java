package com.reca.apiRestFull.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class NoResourceFoundHandlerAdvice {

    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView( "redirect:/swagger-ui/index.html");
    }
}
