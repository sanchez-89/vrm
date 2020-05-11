package com.vuelogix.recordingmanager.framework.rest;

enum ResponseStatus {
    SUCCESS,
    UNABLE_TO_PROCESS,
    ERROR
}

public abstract class AbstractResponse {

    private ResponseStatus responseStatus = ResponseStatus.SUCCESS; //override only if there is error
    private String errorMessage = "";

    public void setSuccess() {
        responseStatus = ResponseStatus.SUCCESS;
    }

    public void setError(String errorMessage) {
        responseStatus = ResponseStatus.ERROR;
        this.errorMessage = errorMessage;
    }

    public void setUnableToProcess(String errorMessage) {
        responseStatus = ResponseStatus.UNABLE_TO_PROCESS;
        this.errorMessage = errorMessage;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError(){
        return responseStatus !=ResponseStatus.SUCCESS;
    }

    @Override
    public String toString() {
        return "AbstractResponse{" +
            "responseStatus=" + responseStatus +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}
