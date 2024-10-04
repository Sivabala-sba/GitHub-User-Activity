import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GitHubActivity {

    private static final String GitHub_API_URL = "https://api.github.com/users/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter GitHub username: ");
        String username = scanner.nextLine();
        try{
            fetchGitHubActivity(username);
        } catch (Exception e){
            System.out.println("Error: unable to fetch activity. " + e.getMessage());
        }
        scanner.close();
    }

    public static void fetchGitHubActivity(String username) throws Exception{
        String apiUrl = GitHub_API_URL + username + "/events";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if(connection.getResponseCode() != 200){
            System.out.println("Error: GitHub user not found or API error.");
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONArray events = (JSONArray) parser.parse(content.toString());

        if(events.isEmpty()){
            System.out.println("No recent activity found for the user: " + username);
        }else{
            System.out.println("Recent activity for GitHub user: " + username);
            for(int i = 0; i < events.size(); i++){
                JSONObject event = (JSONObject) events.get(i);
                displayEvent(event);
            }
        }
    }

    public static void displayEvent(JSONObject event){
        String type = (String) event.get("type");
        JSONObject repo = (JSONObject) event.get("repo");
        String repoName = (String) repo.get("name");

        switch (type) {
            case "PushEvent":
                JSONArray commits = (JSONArray) ((JSONObject) event.get("payload")).get("commits");
                System.out.println("Pushed " + commits.size() + " commit(s) to " + repoName);
                break;
            case "IssuesEvent":
                String action = (String) ((JSONObject) event.get("payload")).get("action");
                System.out.println("Opened a new issue in " + repoName);
                break;
            case "WatchEvent":
                System.out.println("Starred " + repoName);
                break;
            default:
                System.out.println("Performed " + type + " on " + repoName);
        }
    }
}