package hello.api.statistic.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import hello.api.statistic.model.StatisticInfo;
import hello.api.statistic.repository.StatisticRepos;
import hello.api.statistic.entity.Statistic;
import hello.api.statistic.web.StatisticController;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl
        implements StatisticService {
    private static final Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);
    @Autowired
    private StatisticRepos statisticRepos;
    static final String URL_API_VK = "https://api.vk.com/method/users.get";
    static final String VK_TOKEN = "5c5e70cfcf443268b00e8914" +
            " fc9b752980d7c428691f9458d510eaa8f9ec1b7d16695aa764b516fc27a4f";


    @Nullable
    @Override
    public void createStatistic(@Nonnull UUID uuid, @Nonnull String vk) {
        statisticRepos.saveAndFlush(createStat(uuid, vk));
    }

    @Nullable
    @Override
    public void updateUuidStat(@Nonnull UUID uuid, @Nonnull UUID newUuid) {
        statisticRepos.updateUuidUser(uuid, newUuid);

    }



    @Override
    public List<StatisticInfo> findAllStatsByUUID(@Nonnull UUID uuid) {
        return statisticRepos.findAllByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }


    @Override
    public StatisticInfo refreshStatistic(@Nonnull UUID uuid, @Nonnull String vk) {
        return createStatInfo(createStat(uuid, vk));
    }

    @Nullable
    @Override
    public void deleteStats(@Nonnull UUID uuid) {
        statisticRepos.deleteByUid(uuid);
    }

    @Nonnull
    private Statistic createStat(@Nonnull UUID uuid, @Nonnull String vk) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_VK)
                    .queryParam("user_ids", vk).queryParam("fields", "counters")
                    .queryParam("access_token", VK_TOKEN);
            RestTemplate restTemplate = new RestTemplate();
            String jsonString = restTemplate.getForObject(builder.toUriString(), String.class);

            UriComponentsBuilder builder2 = UriComponentsBuilder.fromHttpUrl(URL_API_VK)
                    .queryParam("user_ids", vk).queryParam("fields", "photo_max")
                    .queryParam("access_token", VK_TOKEN);
            RestTemplate restTemplate2 = new RestTemplate();
            String jsonString2 = restTemplate2.getForObject(builder2.toUriString(), String.class);

            System.out.println("jsonString2"+jsonString2);
            System.out.println("jsonString"+jsonString);

            JSONObject jsonObjectPhoto = new JSONObject(jsonString2);
            JSONObject jsonObjectPhoto2 = new JSONObject(jsonObjectPhoto.getJSONArray("response").get(0).toString());


            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonObject2 = new JSONObject(jsonObject.getJSONArray("response").get(0).toString()).getJSONObject("counters");

            Statistic stat=new Statistic();
            stat.setFirst_name(jsonObjectPhoto2.getString("first_name"));
            stat.setLast_name(jsonObjectPhoto2.getString("last_name"));
            stat.setPhotoUrl(jsonObjectPhoto2.getString("photo_max"));
            stat.setAlbums(jsonObject2.getInt("albums"));
            stat.setAudios(jsonObject2.getInt("audios"));
            stat.setVideos(jsonObject2.getInt("videos"));
            stat.setNotes(jsonObject2.getInt("notes"));
            stat.setPhotos(jsonObject2.getInt("photos"));
            stat.setGroups(jsonObject2.getInt("groups"));
            stat.setGifts(jsonObject2.getInt("gifts"));
            stat.setFriends(jsonObject2.getInt("friends"));
            stat.setOnline_friends(jsonObject2.getInt("online_friends"));
            stat.setFollowers(jsonObject2.getInt("followers"));
            stat.setSubscriptions(jsonObject2.getInt("subscriptions"));
            stat.setPages(jsonObject2.getInt("pages"));

            stat.setUid(uuid);
            return stat;
        }catch (Exception e) {
            logger.error("statistivkget", e);
            return null;
        }

    }








    @Nonnull
    private StatisticInfo createStatInfo(@Nonnull Statistic statfirst) {

        StatisticInfo stat = new StatisticInfo();
        stat.setFirst_name(statfirst.getFirst_name());
        stat.setLast_name(statfirst.getLast_name());
        stat.setPhotoUrl(statfirst.getPhotoUrl());
        stat.setAlbums(statfirst.getAlbums());
        stat.setAudios(statfirst.getAudios());
        stat.setVideos(statfirst.getVideos());
        stat.setNotes(statfirst.getNotes());
        stat.setPhotos(statfirst.getPhotos());
        stat.setGroups(statfirst.getGroups());
        stat.setGifts(statfirst.getGifts());
        stat.setFriends(statfirst.getFriends());
        stat.setOnline_friends(statfirst.getOnline_friends());
        stat.setFollowers(statfirst.getFollowers());
        stat.setSubscriptions(statfirst.getSubscriptions());
        stat.setPages(statfirst.getPages());
        stat.setUid(statfirst.getUid());
        stat.setDate(statfirst.getDate());


        return stat;


    }
}
