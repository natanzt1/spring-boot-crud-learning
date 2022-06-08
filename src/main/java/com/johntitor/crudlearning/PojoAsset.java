package com.johntitor.crudlearning;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.TreeMap;

@Data
public class PojoAsset {
    String assetId;
    TreeMap<Timestamp, Double> ES;
    TreeMap<Timestamp, Double> US;
    TreeMap<Timestamp, Double> RSL;
    List<Date> dateList;
}
