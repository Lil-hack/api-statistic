package hello.api.statistic.repository;

import hello.api.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface StatisticRepos
        extends JpaRepository<Statistic, Integer> {


    List<Statistic> findAllByUid(UUID uuid);

    @Transactional
    void deleteByUid(UUID uuid);
    @Transactional
    @Modifying
    @Query("update Statistic t   set t.uid=:newUid" +
            "     where t.uid = :uid")
    void updateUuidUser(@Param("uid") UUID uuid, @Param("newUid") UUID newUuid);

//    @Transactional
//    @Modifying
//    @Query("update Statistic t set t.subscribers=:subscribers, t.photo=:photo, t.video=:video where t.uid = :uid")
//    void updateVkUser(@Param("subscribers") Integer subscribers, @Param("photo") Integer photo ,@Param("video") Integer video,@Param("uid") UUID uid);

}