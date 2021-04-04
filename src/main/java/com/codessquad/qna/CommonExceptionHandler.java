package com.codessquad.qna;

import com.codessquad.qna.domain.Result;
import com.codessquad.qna.exception.IllegalUserAccessException;
import com.codessquad.qna.exception.NotLoggedInException;
import com.codessquad.qna.exception.QuestionNotFoundException;
import com.codessquad.qna.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(IllegalUserAccessException.class)
    public String handleIllegalUserAccessException() {
        return "redirect:/";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException() {
        return "redirect:/";
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public String handleQuestionNotFoundException() {
        return "redirect:/";
    }

    @ExceptionHandler(NotLoggedInException.class)
    public Result handleNotLoggedInException() {
        return Result.fail("Login Needed");
    }
}
