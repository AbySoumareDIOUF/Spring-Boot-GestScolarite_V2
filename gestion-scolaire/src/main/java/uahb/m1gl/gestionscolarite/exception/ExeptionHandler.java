package uahb.m1gl.gestionscolarite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uahb.m1gl.gestionscolarite.dto.ErrorDTO;

//@ControllerAdvice
public class ExeptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = {ScolariteNotFoundException.class})
    public ErrorDTO handleException(ScolariteNotFoundException exception){
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.value()+"")
                .message(exception.getMessage())
                .build();
    }
}
