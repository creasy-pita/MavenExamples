package com.example.schedulingtasks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;

import org.sonatype.nexus.repository.storage.Asset;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SchedulingTasksApplicationTests {
    @Test
    public void nexusUseTest() {
        Asset asset = new Asset();
    }

    @Test
    public void convertString2Date() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date date = null;
        try {
            date = df.parse( "2020-02-17T22:23:23.556-08:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Date date = new Date("2020-02-17T22:23:23.556-08:00");
        String s = date.toString();
    }
	@Test
	public void contextLoads1() {
		String jsonStr = "{\n\"name\":\"1111\",\n" +
				"\"result\" : {\"names\":[]}}";
	jsonStr ="{\n" +
			"  \"name\" : \"repoAssetLister\",\n" +
			"  \"result\" : {\"names\":[],\"urls\":[\"/repository/-/npm-internal/gisqshoppingcart\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.1.2.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.1.3.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.2.1.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.3.0.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.3.2-beeta.1.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.3.2.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.4.1-beeta.1.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.4.1.tgz\",\"/repository/-/npm-internal/gisqshoppingcart/-/gisqshoppingcart-0.4.11-abeeta.1.tgz\"],\"lastUpdateds\":[\"2020-02-17T23:19:21.925-08:00\",\"2020-02-17T22:23:52.706-08:00\",\"2020-02-09T23:41:25.860-08:00\",\"2020-02-17T22:23:23.556-08:00\",\"2020-02-17T23:19:21.893-08:00\",\"2020-02-17T22:46:22.968-08:00\",\"2020-02-17T22:24:40.293-08:00\",\"2020-02-17T22:48:20.062-08:00\",\"2020-02-17T22:24:22.973-08:00\",\"2020-02-17T23:18:56.211-08:00\"]}\n" +
			"}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
		//load in list
		jsonStr = jsonStr.trim();
		boolean a = "12".equals("12");

		Object obj= JSONValue.parse(jsonStr);
		JSONObject jsonObject = (JSONObject) obj;
		jsonObject.get("name");
	}

	@Test
	public void contextLoads() {
        String baseUrl = "http://192.168.161.133:8073";
		String command = "curl -u admin:admin123 -X POST http://192.168.161.133:8073/service/rest/v1/script/repoAssetLister/run -H \"accept: application/json\" -H \"Content-Type: application/json\"  -d \"{ \\\"repoName\\\":\\\"npm-internal\\\",\\\"updateBeginDate\\\":\\\"2020-02-17T23:00:00\\\"}\"";
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get the POST result
		String jsonStr = JavaCurlExamples.inputStreamToString(process.getInputStream());
		jsonStr = jsonStr.replace("\"result\" : \"","\"result\" : ");
		jsonStr = jsonStr.replace("}\"\n}","}\n}");
		jsonStr = jsonStr.replace("\\\"","\"");
		jsonStr = jsonStr.trim();

		//load in list
		Object obj= JSONValue.parse(jsonStr);
		JSONObject jsonObject = (JSONObject) obj;
		jsonObject.get("name");
        JSONObject resultObj = (JSONObject)jsonObject.get("result");
        JSONArray namesArray = (JSONArray) resultObj.get("names");
		JSONArray versionsArray = (JSONArray) resultObj.get("versions");
		JSONArray authorsArray = (JSONArray) resultObj.get("creators");
        JSONArray urlsArray = (JSONArray) resultObj.get("urls");
        JSONArray lastUpdatedsArray = (JSONArray) resultObj.get("lastUpdateds");
        List<PackageInfo> packageInfos = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//        for(int i =0 ;i<namesArray.size();i++){
        for(int i =0 ;i<5;i++){
            try {
                String version = versionsArray.get(i).toString();
                String name = namesArray.get(i).toString();
                String author = authorsArray.get(i).toString();
                String lastUpdate = lastUpdatedsArray.get(i).toString();
                packageInfos.add(new PackageInfo(version, name,author, df.parse(lastUpdate),0));

                //FileDownload.downloadWithJavaIO(baseUrl+urlsArray.get(i), i+".tgz");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //
	}

	public String GetVersion(String str){

    	return "";
	}

}
