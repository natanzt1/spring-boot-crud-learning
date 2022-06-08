package com.johntitor.crudlearning;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PojoCSV {
    public PojoCSV(String ASSET_ID, String COLLECTING_DATE, String INPUT, Double VALUE) {
        this.ASSET_ID = ASSET_ID;
        this.COLLECTING_DATE = COLLECTING_DATE;
        this.INPUT = INPUT;
        this.VALUE = VALUE;
        this.SOURCE = "";
        this.USERNAME = "";
        this.ASSET_TYPE = "";
        this.LAST_INTEGRATION_RUNTIME = "";
        this.INTEGRATION_NAME = "";
        this.SOURCE_ENVIRONMENT = "";
        this.INSERTION_DATE = "";
    }

    String ASSET_ID;
    String COLLECTING_DATE;
    String INPUT;
    Double VALUE;
    String SOURCE;
    String USERNAME;
    String ASSET_TYPE;
    String LAST_INTEGRATION_RUNTIME;
    String INTEGRATION_NAME;
    String SOURCE_ENVIRONMENT;
    String INSERTION_DATE;
}
