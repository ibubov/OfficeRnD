import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        AuthAPI authAPI = new AuthAPI();
        HttpResponse<String> response = authAPI.getAccessToken("PDLpvoDnPKifisa2", "0O4pZupMYNC3wbNNBKqyQOuUDizlTMgu");
        JSONObject authenticationResponseBody = new JSONObject(response.body());
        String accessToken = authenticationResponseBody.getString("access_token");
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        System.out.println("Access Token: " + accessToken);

        PlansAPI plansAPI = new PlansAPI();
        HttpResponse<String> getPlans = plansAPI.getPlans("Private%20office", accessToken);
        JSONArray jsonArray = new JSONArray(getPlans.body());
        JSONObject plansResponseBody = jsonArray.getJSONObject(0);
        String planID = plansResponseBody.getString("_id");
        System.out.println("Status code: " + getPlans.statusCode());
        System.out.println("Response body: " + getPlans.body());
        System.out.println("Plan ID: " + planID);

        MembershipsAPI membershipsAPI = new MembershipsAPI();
        HttpResponse<String> postMembership = membershipsAPI.postMembershipWithPlan("newMembership", "65f5c78a1e817032a6b212e1", "2024-04-01T00:00:00.000Z", "5c5860d01c45a0000f241019", planID, accessToken);
        System.out.println("Status code: " + postMembership.statusCode());
        System.out.println("Response body: " + postMembership.body());
    }
}