package io.swagger.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

public class AlreadyExistsException extends ApiException {
    private int code;
    public AlreadyExistsException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
