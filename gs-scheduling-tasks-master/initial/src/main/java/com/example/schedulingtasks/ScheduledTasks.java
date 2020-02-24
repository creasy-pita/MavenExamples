package com.example.schedulingtasks;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        String command = "curl -u admin:admin123 -X POST http://192.168.161.133:8073/service/rest/v1/script/repoAssetLister/run -H \"accept: application/json\" -H \"Content-Type: application/json\"  -d \"{ \\\"repoName\\\":\\\"npm-internal\\\"}\"";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the POST result
        String jsonStr = JavaCurlExamples.inputStreamToString(process.getInputStream());
        log.info("jsonStr is :" + jsonStr);

        //load in list
        Object obj= JSONValue.parse(jsonStr);
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.get("name");
        Object resultObj = jsonObject.get("result");

        // name:gisqshoppingcart|Version:6|id:36e3dec8de528c9b812ce6e257caaf97|blobCreated:2020-02-09T22:44:35.116-08:00|blobUpdated:2020-02-17T22:24:40.317-08:00|size:14578|lastUpdated:2020-02-17T22:24:40.318-08:00
        List<PackageInfo> packageInfosOld = new ArrayList<>();
        packageInfosOld.add(new PackageInfo("0.3.1","gisqshoppingcart-0.3.1",new Date(),0));
        packageInfosOld.add(new PackageInfo("0.2.1","gisqshoppingcart-0.2.1",new Date(),0));
        packageInfosOld.add(new PackageInfo("0.1.2","gisqshoppingcart-0.1.2",new Date(),0));

        //
    }
}