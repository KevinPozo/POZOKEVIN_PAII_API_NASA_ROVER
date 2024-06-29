/**
 *
 * @author KevinPozo
 * Title: Api Fotos de Rover Mars (Lambda).
 *
 *
 * */
package Controller;

import Model.MarsPhoto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MarsApiController {

    private static final String API_KEY = "3OfVqX4Ib65vlYKpYLZzI8Tv0nBhEEzA6TYEThCb";

    public List<MarsPhoto> fetchMarsPhotos(String roverName, int sol, int numOfPhotos, String camera) throws IOException {
        String apiUrl = buildApiUrl(roverName, sol, numOfPhotos, camera);

        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to fetch data from API. Response code: " + connection.getResponseCode());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = in.lines().collect(Collectors.joining());
            in.close();

            JSONArray photosArray = new JSONObject(response).getJSONArray("photos");

            return IntStream.range(0, Math.min(numOfPhotos, photosArray.length()))
                    .mapToObj(photosArray::getJSONObject)
                    .map(this::parseMarsPhoto)
                    .collect(Collectors.toList());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private MarsPhoto parseMarsPhoto(JSONObject photoObj) {
        try {
            return new MarsPhoto(
                    photoObj.getInt("id"),
                    photoObj.getInt("sol"),
                    LocalDate.parse(photoObj.getString("earth_date")),
                    photoObj.getJSONObject("rover").getString("name"),
                    photoObj.getJSONObject("camera").getString("full_name"),
                    photoObj.getString("img_src")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String buildApiUrl(String roverName, int sol, int numOfPhotos, String camera) {
        String baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
        String apiUrl = baseUrl + roverName + "/photos?sol=" + sol + "&api_key=" + API_KEY;

        if (numOfPhotos > 0) {
            apiUrl += "&page=1&per_page=" + numOfPhotos;
        }

        if (camera != null && !camera.isEmpty()) {
            apiUrl += "&camera=" + camera;
        }

        return apiUrl;
    }
}
