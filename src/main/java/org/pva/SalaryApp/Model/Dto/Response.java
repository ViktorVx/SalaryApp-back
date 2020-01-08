package org.pva.SalaryApp.Model.Dto;

public class Response {

    private ResponseCode responseCode;

    private String message;

    public Response(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
