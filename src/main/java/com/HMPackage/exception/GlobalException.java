package com.HMPackage.exception;

import com.HMPackage.baseResponse.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    private ResponseEntity badRequestEx(BadRequestEx e){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        baseResponse.setStatusMessage("BAD REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }
    @ExceptionHandler
    private ResponseEntity notFoundException(NotFoundEx e){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        baseResponse.setStatusMessage("NOT FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
    }
    @ExceptionHandler
    private ResponseEntity unAuthEx(UnAuthEx e){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        baseResponse.setStatusMessage("UNAUTHORIZED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
    }
}
