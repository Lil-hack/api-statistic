package hello.api.statistic.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "subscribers")
    private Integer subscribers;


    @Column(name = "photo")
    private Integer photo;

    @Column(name = "wall")
    private Integer wall;

    @Column(name = "infoage")
    private String info;

    @Column(name = "video")
    private Integer video;

    @Column(name = "audio")
    private Integer audio;

    @Column(name = "groups")
    private Integer groups;

    @Column(name = "lastactive")
    private String lastactive;

    @Column(name = "date")
    private Date date;

    @Column(name = "uid")
    private UUID uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    public Integer getWall() {
        return wall;
    }

    public void setWall(Integer wall) {
        this.wall = wall;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getVideo() {
        return video;
    }

    public void setVideo(Integer video) {
        this.video = video;
    }

    public Integer getAudio() {
        return audio;
    }

    public void setAudio(Integer audio) {
        this.audio = audio;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public String getLastactive() {
        return lastactive;
    }

    public void setLastactive(String lastactive) {
        this.lastactive = lastactive;
    }


    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(id, statistic.id) &&
                Objects.equals(subscribers, statistic.subscribers) &&
                Objects.equals(photo, statistic.photo) &&
                Objects.equals(wall, statistic.wall) &&
                Objects.equals(info, statistic.info) &&
                Objects.equals(video, statistic.video) &&
                Objects.equals(audio, statistic.audio) &&
                Objects.equals(groups, statistic.groups) &&
                Objects.equals(lastactive, statistic.lastactive) &&
                Objects.equals(date, statistic.date) &&
                Objects.equals(uid, statistic.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscribers, photo, wall, info, video, audio, groups, lastactive, date, uid);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", subscribers=" + subscribers +
                ", photo=" + photo +
                ", wall=" + wall +
                ", info='" + info + '\'' +
                ", video=" + video +
                ", audio=" + audio +
                ", groups=" + groups +
                ", lastactive='" + lastactive + '\'' +
                ", date=" + date +
                ", uid=" + uid +
                '}';
    }
}
