package com.johntitor.crudlearning;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class TVADump {
    String path = "E:\\Project\\Java\\SPRING\\crud-learning\\src\\main\\java\\com\\johntitor\\crudlearning\\prepared_output.json";
    List<PojoAsset> listAsset = new ArrayList<>();
    List<PojoCSV> listCSV = new ArrayList<>();
    public void readJson() {
        try {
            System.out.println(path);
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader(path));//path to the JSON file.
            data.forEach((key, value) -> {
                String assetId = key.toString();
                System.out.println(assetId);
                loopEachAssetId(assetId, value);
            });
            convertPojoAssetToPojoCSV(listAsset);
            System.out.println(listAsset.toString());
            writeDataToCSV(listCSV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loopEachAssetId(String assetId, Object data) {
        PojoAsset asset = new PojoAsset();
        asset.setAssetId(assetId);
        List<Date> listDate = new ArrayList<>();
        ((JSONObject) data).forEach((key, value) -> {
            String parameter = key.toString();
            TreeMap<Timestamp, Double> parameterValues = loopEachParameter(value);
            switch (parameter.toLowerCase(Locale.ROOT)) {
                case "es":
                    asset.setES(parameterValues);
                    break;
                case "us":
                    asset.setUS(parameterValues);
                    break;
                case "rsl":
                    asset.setRSL(parameterValues);
                    asset.setDateList(getDateListFromAllData(parameterValues));
                    break;
            }
        });
        System.out.println("list Date: " + asset.getDateList());
        listAsset.add(asset);
    }

    private TreeMap<Timestamp, Double> loopEachParameter(Object data) {
//        System.out.println(parameter + ":" + data);
        HashMap<Timestamp, Double> parameterValues = new HashMap<>();
        TreeMap<Timestamp, Double> sortedParameterValues = new TreeMap<>();
        ((JSONObject) data).forEach((key, value) -> {
//            System.out.println(key + ":" + value.toString());
            Timestamp timestamp = Timestamp.valueOf(key.toString());
            JSONObject object = (JSONObject) value;
            Double parameterValue = Double.valueOf(object.get("Value").toString());
            parameterValues.put(timestamp, parameterValue);
        });
        sortedParameterValues.putAll(parameterValues);
        System.out.println(sortedParameterValues);
        return sortedParameterValues;
    }

    private Date getDate(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        return date;
    }

    private List<Date> getDateListFromAllData(TreeMap<Timestamp, Double> parameterVales) {
        List<Date> dateList = new ArrayList<>();
        List<String> checker = new ArrayList<>();
        parameterVales.forEach((key, value) -> {
            Date date = getDate(Timestamp.valueOf(key.toString()));
            String dateCheck = date.toString();
            if (!checker.contains(dateCheck)) {
                dateList.add(date);
                checker.add(dateCheck);
            }
        });
        return dateList;
    }

    private void convertPojoAssetToPojoCSV(List<PojoAsset> assetList) {
        List<Date> dateList = assetList.get(0).getDateList();
        dateList.forEach(date -> {
            assetList.forEach(asset -> {
                asset.getES().forEach((key, value) -> {
                    Date date1 = getDate(key);
                    if (date1.toString().equalsIgnoreCase(date.toString())) {
                        String parameter = "ES";
                        PojoCSV csv = new PojoCSV(asset.getAssetId(), key.toString(), parameter, value);
                        listCSV.add(csv);
                    }
                });
                asset.getUS().forEach((key, value) -> {
                    Date date1 = getDate(key);
                    if (date1.toString().equalsIgnoreCase(date.toString())) {
                        String parameter = "US";
                        System.out.println("HAHA: " + date1);
                        PojoCSV csv = new PojoCSV(asset.getAssetId(), key.toString(), parameter, value);
                        listCSV.add(csv);
                    }
                });
                asset.getRSL().forEach((key, value) -> {
                    Date date1 = getDate(key);
                    if (date1.toString().equalsIgnoreCase(date.toString())) {
                        String parameter = "RSL";
                        System.out.println("HAHA: " + date1);
                        PojoCSV csv = new PojoCSV(asset.getAssetId(), key.toString(), parameter, value);
                        listCSV.add(csv);
                    }
                });
            });
        });
    }

    private void writeDataToCSV(List<PojoCSV> csvList) {
        try (PrintWriter writer = new PrintWriter("insp_agg_prepared.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("ASSET_ID");
            sb.append(',');
            sb.append("COLLECTING_DATE");
            sb.append(',');
            sb.append("INPUT");
            sb.append(',');
            sb.append("VALUE");
            sb.append(',');
            sb.append("SOURCE");
            sb.append(',');
            sb.append("USERNAME");
            sb.append(',');
            sb.append("ASSET_TYPE");
            sb.append(',');
            sb.append("LAST_INTEGRATION_RUNTIME");
            sb.append(',');
            sb.append("INTEGRATION_NAME");
            sb.append(',');
            sb.append("SOURCE_ENVIRONMENT");
            sb.append(',');
            sb.append("INSERTION_DATE");
            sb.append('\n');

            csvList.forEach((csv) ->{
                sb.append(csv.getASSET_ID());
                sb.append(',');
                sb.append(csv.getCOLLECTING_DATE());
                sb.append(',');
                sb.append(csv.getINPUT());
                sb.append(',');
                sb.append(csv.getVALUE());
                sb.append(',');
                sb.append(csv.getSOURCE());
                sb.append(',');
                sb.append(csv.getUSERNAME());
                sb.append(',');
                sb.append(csv.getASSET_TYPE());
                sb.append(',');
                sb.append(csv.getLAST_INTEGRATION_RUNTIME());
                sb.append(',');
                sb.append(csv.getINTEGRATION_NAME());
                sb.append(',');
                sb.append(csv.getSOURCE_ENVIRONMENT());
                sb.append(',');
                sb.append(csv.getINSERTION_DATE());
                sb.append('\n');
            });

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        TVADump tvaDump = new TVADump();
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        tvaDump.readJson();
    }
}
