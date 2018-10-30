package Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class BaseModel {

    private HttpsURLConnection httpsURLConnection;

    public void PerformRequestToSwapi(String urlToGo) throws IOException {
        URL basicUrl = new URL(urlToGo);
        httpsURLConnection = (HttpsURLConnection) basicUrl.openConnection();
        httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        httpsURLConnection.setRequestProperty("content-type", "application/json");
    }

    public int GetResponseCode() throws IOException {
        int responseCode = httpsURLConnection.getResponseCode();
        System.out.println("Recieved Response code " + responseCode);
        return responseCode;
    }

    public StringBuffer GetResponseText() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        return response;

    }

    public Object GetTagValue(StringBuffer response, String tagName) {
        JSONObject obj_JSONObject = new JSONObject(response.toString());
        Object value = obj_JSONObject.get(tagName);
        System.out.println("Recieved Planets Count" + value);
        return value;

    }

    public Boolean CompareInfo(StringBuffer response, String planetInfoJson) throws IOException {

        File file = new File("/Automation/swapi/src/test/resources/" + planetInfoJson + ".json");
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject planetContent = new JSONObject(content);
        JsonParser parser = new JsonParser();
        JsonElement expected = parser.parse(planetContent.toString());
        JsonElement actual = parser.parse(response.toString());
        boolean isInfoEqual = expected.equals(actual);
        return isInfoEqual;


    }
}
