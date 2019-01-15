package hello.api.statistic.service;

import hello.api.statistic.model.StatisticInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface StatisticService {


    @Nullable
    void createStatistic(@Nonnull UUID uuid, @Nonnull String vk);

    @Nullable
    void updateUuidStat(@Nonnull UUID uuid, @Nonnull UUID newUuid);
//    @Nullable
//    void updateVKStat(@Nonnull StatisticInfo statisticInfo);



    List<StatisticInfo> findAllStatsByUUID(@Nonnull UUID uuid);

    StatisticInfo refreshStatistic(@Nonnull UUID uuid, @Nonnull String vk);


    @Nullable
    void deleteStats(@Nonnull UUID uuid);


}
