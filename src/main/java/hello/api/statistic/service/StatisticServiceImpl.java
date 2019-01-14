package hello.api.statistic.service;

import hello.api.statistic.model.StatisticInfo;
import hello.api.statistic.repository.StatisticRepos;
import hello.api.statistic.entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl
        implements StatisticService {

    @Autowired
    private StatisticRepos statisticRepos;


    @Nullable
    @Override
    public void createStatistic(@Nonnull UUID uuid, @Nonnull String vk) {
        statisticRepos.saveAndFlush(createStat(uuid, vk));
    }
    @Nullable
    @Override
    public void updateUuidStat(@Nonnull UUID uuid, @Nonnull UUID newUuid)
    {statisticRepos.updateUuidUser(uuid,newUuid);

    }
    @Nullable
    @Override
    public void updateVKStat(@Nonnull StatisticInfo statisticInfo)
    {statisticRepos.updateVkUser(statisticInfo.getSubscribers(),statisticInfo.getPhoto(),statisticInfo.getVideo(),statisticInfo.getUid());

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
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://vk.com/" + vk);
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method and default Headers.
        String result = restTemplate.getForObject(builder.toUriString(), String.class);


        Statistic stat = new Statistic();
  //      stat.setSubscribers(Integer.parseInt(getInfo("Подписчики <em class=\"pm_counter\">", result)));
        stat.setAudio(11);
        stat.setDate(Calendar.getInstance().getTime());
//        stat.setGroups(Integer.parseInt(getInfo("Интересные страницы <em class=\"pm_counter\">", result)));
//        stat.setInfo(getTextInfo("<div class=\"pp_info\">", result));
//        stat.setLastactive(getTextInfo("activity_offline_text\">", result));
       stat.setPhoto(Integer.parseInt(getInfo("Фотографии <em class=\"pm_counter\">", result)));
        stat.setVideo(Integer.parseInt(getInfo("Видео <em class=\"pm_counter\">", result)));
//        stat.setWall(30);
        stat.setUid(uuid);
        return stat;
    }


    String getInfo(String info, String result) {
        int startPos = result.indexOf(info) + info.length();
        int lastPos = result.substring(startPos, startPos + 20).indexOf("<");

        String sotni = "<span class=\"num_delim\"> </span>";
        int findpos = result.substring(lastPos, lastPos + 30).indexOf(sotni);
        if (findpos != -1) {
            int startPos2 = startPos + findpos + sotni.length();
            int lastPos2 = result.substring(startPos2, startPos2 + 10).indexOf("<");
            System.out.println(startPos + " " + lastPos + " " + findpos + " " + startPos2 + " " + lastPos2);
            return result.substring(startPos, startPos + lastPos).replaceAll("\\D+", "")
                    + result.substring(startPos2, startPos2 + lastPos2).replaceAll("\\D+", "");
        } else {
            return result.substring(startPos, startPos + lastPos).replaceAll("\\D+", "");
        }


    }

    String getTextInfo(String info, String result) {
        int startPos = result.indexOf(info) + info.length();
        int lastPos = result.substring(startPos, startPos + 100).indexOf("<");
        return result.substring(startPos, startPos + lastPos);
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
