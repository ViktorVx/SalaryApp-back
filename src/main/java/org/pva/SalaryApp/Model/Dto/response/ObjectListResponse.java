package org.pva.SalaryApp.Model.Dto.response;

import java.util.List;

public class ObjectListResponse<T> extends Response {

    private List<T> objectList;

    public ObjectListResponse(ResponseCode responseCode, String message) {
        super(responseCode, message);
    }

    public List<T> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<T> objectList) {
        this.objectList = objectList;
    }
}
