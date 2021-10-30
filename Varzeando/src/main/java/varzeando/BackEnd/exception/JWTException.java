package varzeando.BackEnd.exception;

import org.springframework.http.HttpStatus;


public class JWTException extends RuntimeException{

    private  String mensagem;
    private HttpStatus httpStatus;

    public JWTException(String message, HttpStatus httpStatus) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }
    @Override
    public String getMessage() {
        return mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
