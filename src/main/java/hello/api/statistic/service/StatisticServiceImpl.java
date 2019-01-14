package hello.api.statistic.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import hello.api.statistic.model.StatisticInfo;
import hello.api.statistic.repository.StatisticRepos;
import hello.api.statistic.entity.Statistic;
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

    @Nullable
    @Override
    public void updateVKStat(@Nonnull StatisticInfo statisticInfo) {
        statisticRepos.updateVkUser(statisticInfo.getSubscribers(), statisticInfo.getPhoto(), statisticInfo.getVideo(), statisticInfo.getUid());

    }

    @Override
    public List<StatisticInfo> findAllStatsByUUID(@Nonnull UUID uuid) {
        return statisticRepos.findAllByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticInfo> findWeekStatsByUUID(@Nonnull UUID uuid) {
        return statisticRepos.findFirst7ByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticInfo> findMonthStatsByUUID(@Nonnull UUID uuid) {
        return statisticRepos.findFirst30ByUid(uuid)
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
            JsonFactory jfactory = new JsonFactory();

            /*** read from file ***/
            Statistic stat = new Statistic();
            stat.setSubscribers(null);
            stat.setAudio(null);
            stat.setDate(null);
            stat.setGroups(null);
            stat.setInfo(null);
            stat.setLastactive(null);
            stat.setPhoto(null);
            stat.setVideo(stat.getVideo());
            stat.setWall(stat.getWall());
            stat.setUid(stat.getUid());
            return stat;
        }catch (Exception e) {

            return null;
        }

    }








    @Nonnull
    private StatisticInfo createStatInfo(@Nonnull Statistic stat) {

        StatisticInfo statInfo = new StatisticInfo();
        statInfo.setSubscribers(stat.getSubscribers());
        statInfo.setAudio(stat.getAudio());
        statInfo.setDate(stat.getDate());
        statInfo.setGroups(stat.getGroups());
        statInfo.setInfo(stat.getInfo());
        statInfo.setLastactive(stat.getLastactive());
        statInfo.setPhoto(stat.getPhoto());
        statInfo.setVideo(stat.getVideo());
        statInfo.setWall(stat.getWall());
        statInfo.setUid(stat.getUid());
        return statInfo;


    }
}
