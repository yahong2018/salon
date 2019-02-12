package com.zhxh.core.exception;

import com.zhxh.core.utils.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BusinessErrorHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView defaultExceptionHandler(BusinessException e) {
        Logger.error(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.addObject("stackTrace",ExceptionHelper.exceptionStackTrace(e));
        modelAndView.setViewName("error/businessError");

        return modelAndView;
    }
}
