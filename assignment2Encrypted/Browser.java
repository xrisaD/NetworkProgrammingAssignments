import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

public class Browser {
    private static HttpClient client;
    public static void main(String[] args) throws IOException, InterruptedException {
        // init client
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        // create array with all numbers from 1 to 100
        int arr[] = new int[100];
        for (int i = 1; i <= 100; i ++) {
            arr[i-1] = i;
        }

        int averageGuesses = 0;
        for (int i=1; i <=100; i++){
            int numOfGuesses = binarySearch(arr);
            averageGuesses = averageGuesses + numOfGuesses;
        }
        System.out.println(averageGuesses/100);

    }


    private static int binarySearch(int arr[]) throws IOException, InterruptedException {
        int l = 0, r = arr.length - 1;
        HttpRequest request = null;
        int counter = 0;
        String cookie = null;
        while (true) {
            counter++;
            int m = l + (r - l) / 2;
            String uri = "http://localhost:8080/guess.html?value=" + m;

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                                            .uri(URI.create(uri))
                                            .timeout(Duration.ofMinutes(2))
                                            .GET();
            if (cookie != null) {
                // create request
                request = builder.headers("Cookie", cookie).build();
            } else {
                request = builder.build();
            }

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // get the cookie and save it for the next request
            Optional<String> optionalCookie = response.headers().firstValue("set-cookie");
            if (optionalCookie.isPresent()) {
                cookie = optionalCookie.get();
            }

            // send it to server
            String body = response.body();

            // Check if we found it
            if (body.contains("Success")) {
                return counter;
            }
            // If the target value greater, ignore left half
            if (body.contains("higher")) {
                l = m + 1;
            }
            // If the target value is smaller, ignore right half
            else {
                r = m - 1;
            }
        }
    }
}
