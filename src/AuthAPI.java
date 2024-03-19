import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthAPI {

    public HttpResponse getAccessToken(String clientId, String clientSecret){
        String[] requestBody = new String[]{
                "client_id=" + clientId,
                "client_secret=" + clientSecret,
                "grant_type=client_credentials",
                "scope=officernd.api.read officernd.api.write"
        };

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://identity.officernd.com/oauth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method("POST", HttpRequest.BodyPublishers.ofString(String.join("&", requestBody)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
