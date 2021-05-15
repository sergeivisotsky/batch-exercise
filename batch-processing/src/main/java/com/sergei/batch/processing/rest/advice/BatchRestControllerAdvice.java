package com.sergei.batch.processing.rest.advice;

import com.sergei.batch.processing.rest.dto.ResponseError;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@RestControllerAdvice
public class BatchRestControllerAdvice {

    @ExceptionHandler(FTPConnectionClosedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError ftpException(FTPConnectionClosedException ex, WebRequest request) {
        return new ResponseError(500, ex.getLocalizedMessage());
    }
}
