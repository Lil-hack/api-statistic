package hello.api.statistic.web;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.api.statistic.model.StatisticInfo;
import hello.api.statistic.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api-statistic")
public class StatisticController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
    @Autowired
    private StatisticService statRepos;

    static final String ACCESS_TOKEN_VALIDATE_URI="https://api-my-gateway.herokuapp.com/api-gateway/oauth20/tokens/validate";
    static final String CLIENT_ID="9aa3ae11759ae257e2f29484a32820845ae59763";
    static final String CLIENT_SECRET="3997673d7814bbbcde139fe181e7fba723beb70a4e6e49363230ff78051f40d1";

    private boolean OauthCheckToken(String token)
    {

        if(token==null)
            return false;
        else {
            try {
                String token2 = token.replace("Bearer ", "");

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ACCESS_TOKEN_VALIDATE_URI)
                        .queryParam("token", token2);
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(builder.toUriString(), String.class);

                JsonFactory factory = new JsonFactory();

                ObjectMapper mapper = new ObjectMapper(factory);
                JsonNode rootNode = mapper.readTree(result);

                Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

                while (fieldsIterator.hasNext()) {

                    Map.Entry<String, JsonNode> field = fieldsIterator.next();

                    System.out.println(field.getKey());
                    if (field.getKey() == "error") {
                        return false;
                    }

                }
                return  true;
            } catch (Exception e) {
                logger.error("oauth", e);
                return  false;
            }
        }
    }

    @GetMapping("/getAll{uuid}")
    public ResponseEntity<List<StatisticInfo>> findAll(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        System.out.println(token);
        if(OauthCheckToken(token)==false) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        System.out.println("vcr norm");
        try {
            List<StatisticInfo> stats = statRepos.findAllStatsByUUID(uuid);

            if (stats.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            } else {
                return new ResponseEntity<List<StatisticInfo>>(stats, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getAllError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



    @PostMapping("/create")
    public ResponseEntity createStat(@RequestHeader(value="Authorization",required = false) String token,@RequestBody Map<String, String> info) {

        if(OauthCheckToken(token)==false) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        try {
            statRepos.createStatistic(UUID.fromString(info.get("uid")), info.get("vk"));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("create", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateUUID")
    public ResponseEntity updateUuidUser(@RequestHeader(value="Authorization",required = false) String token,@RequestBody Map<String, String> requestUserDetails) {
        if(OauthCheckToken(token)==false) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        try {


            statRepos.updateUuidStat(UUID.fromString(requestUserDetails.get("uid")),
                    UUID.fromString(requestUserDetails.get("newUid")));

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateUuidUserError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get{vk}{uuid}")
    public ResponseEntity<StatisticInfo> getStat(@RequestHeader(value="Authorization",required = false) String token,@RequestParam String vk, @RequestParam UUID uuid) {
        if(OauthCheckToken(token)==false) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        try {

            return new ResponseEntity(statRepos.refreshStatistic(uuid, vk), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete{uuid}")
    public ResponseEntity deleteStats(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        if(OauthCheckToken(token)==false) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        try {
            statRepos.deleteStats(uuid);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("deleteError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
