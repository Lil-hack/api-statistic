package hello;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootApplication
public class Application {

    static final String URL_API_VK = "https://api.vk.com/method/users.get";
    static final String VK_TOKEN = "5c5e70cfcf443268b00e8914" +
            " fc9b752980d7c428691f9458d510eaa8f9ec1b7d16695aa764b516fc27a4f";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_VK)
                    .queryParam("user_ids", "basta").queryParam("fields", "counters")
                    .queryParam("access_token", VK_TOKEN);
            RestTemplate restTemplate = new RestTemplate();


            String jsonString = restTemplate.getForObject(builder.toUriString(), String.class);
            JsonFactory jfactory = new JsonFactory();

            /*** read from file ***/
            JsonParser jParser = jfactory.createJsonParser(jsonString);

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();
                if ("albums".equals(fieldname)) {

                    // current token is "name",
                    // move to next, which is "name"'s value
                    jParser.nextToken();
                    System.out.println(jParser.getText()); // display mkyong

                }

                if ("videos".equals(fieldname)) {

                    // current token is "age",
                    // move to next, which is "name"'s value
                    jParser.nextToken();
                    System.out.println(jParser.getIntValue()); // display 29

                }

                if ("notes".equals(fieldname)) {

                    jParser.nextToken(); // current token is "[", move next

                    // messages is array, loop until token equal to "]"
                    while (jParser.nextToken() != JsonToken.END_ARRAY) {

                        // display msg1, msg2, msg3
                        System.out.println(jParser.getText());

                    }

                }

            }
            jParser.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }



}
