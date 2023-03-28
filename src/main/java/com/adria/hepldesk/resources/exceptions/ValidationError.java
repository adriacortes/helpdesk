package com.adria.hepldesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String mensagem, String path) {
        super(timestamp, status, error, mensagem, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName,String message) {
        this.errors.add(new FieldMessage(fieldName,message));
    }
}
