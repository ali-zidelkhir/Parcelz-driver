package com.example.parcelz_driver.directory;

public class MyRequest_Model {

    String driver_id;
    String request_id;
    public MyRequest_Model( ) {
    }
    public MyRequest_Model(String driver_id, String request_id) {
        this.driver_id = driver_id;
        this.request_id = request_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}
