import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class MembershipsAPI {

    public HttpResponse postMembershipWithPlan(String name, String memberId, String startDate, String officeID, String planID, String token){
        String rb = "[{\"name\":\"" + name + "\"," +
                "\"startDate\":\"" + startDate + "\"," +
                "\"member\":\"" + memberId + "\"," +
                "\"office\":\"" + officeID + "\"," +
                "\"plan\":\"" + planID + "\"}]";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://app.officernd.com/api/v1/organizations/qa-assignment-demo-ivaylo-bubov/memberships"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("authorization", "Bearer " + token)
                .method("POST", HttpRequest.BodyPublishers.ofString(String.join("&", rb)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
