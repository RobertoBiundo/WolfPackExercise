package com.example.demo.objects.data_transfer_objects;

public class ResponseDTO {
    private Boolean status = false;
    private String message;
    private Object body;

    public ResponseDTO(Boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(Boolean status, Object body){
        this.status = status;
        this.body = body;
    }

    public ResponseDTO(){}

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }
}
