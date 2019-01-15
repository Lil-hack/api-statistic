package hello.api.statistic.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "stat")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photoUrl")
    private String photoUrl;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "albums")
    private Integer albums;
    @Column(name = "videos")
    private Integer videos;
    @Column(name = "audios")
    private Integer audios;
    @Column(name = "notes")
    private Integer notes;
    @Column(name = "photos")
    private Integer photos;
    @Column(name = "groups")
    private Integer groups;
    @Column(name = "gifts")
    private Integer gifts;
    @Column(name = "friends")
    private Integer friends;
    @Column(name = "online_friends")
    private Integer online_friends;
    @Column(name = "user_photos")
    private Integer user_photos;
    @Column(name = "followers")
    private Integer followers;
    @Column(name = "subscriptions")
    private Integer subscriptions;
    @Column(name = "pages")
    private Integer pages;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAlbums() {
        return albums;
    }

    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    public Integer getVideos() {
        return videos;
    }

    public void setVideos(Integer videos) {
        this.videos = videos;
    }

    public Integer getAudios() {
        return audios;
    }

    public void setAudios(Integer audios) {
        this.audios = audios;
    }

    public Integer getNotes() {
        return notes;
    }

    public void setNotes(Integer notes) {
        this.notes = notes;
    }

    public Integer getPhotos() {
        return photos;
    }

    public void setPhotos(Integer photos) {
        this.photos = photos;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getGifts() {
        return gifts;
    }

    public void setGifts(Integer gifts) {
        this.gifts = gifts;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }

    public Integer getOnline_friends() {
        return online_friends;
    }

    public void setOnline_friends(Integer online_friends) {
        this.online_friends = online_friends;
    }

    public Integer getUser_photos() {
        return user_photos;
    }

    public void setUser_photos(Integer user_photos) {
        this.user_photos = user_photos;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Integer subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(id, statistic.id) &&
                Objects.equals(photoUrl, statistic.photoUrl) &&
                Objects.equals(first_name, statistic.first_name) &&
                Objects.equals(last_name, statistic.last_name) &&
                Objects.equals(albums, statistic.albums) &&
                Objects.equals(videos, statistic.videos) &&
                Objects.equals(audios, statistic.audios) &&
                Objects.equals(notes, statistic.notes) &&
                Objects.equals(photos, statistic.photos) &&
                Objects.equals(groups, statistic.groups) &&
                Objects.equals(gifts, statistic.gifts) &&
                Objects.equals(friends, statistic.friends) &&
                Objects.equals(online_friends, statistic.online_friends) &&
                Objects.equals(user_photos, statistic.user_photos) &&
                Objects.equals(followers, statistic.followers) &&
                Objects.equals(subscriptions, statistic.subscriptions) &&
                Objects.equals(pages, statistic.pages) &&
                Objects.equals(date, statistic.date) &&
                Objects.equals(uid, statistic.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoUrl, first_name, last_name, albums, videos, audios, notes, photos, groups, gifts, friends, online_friends, user_photos, followers, subscriptions, pages, date, uid);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", photoUrl='" + photoUrl + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", albums=" + albums +
                ", videos=" + videos +
                ", audios=" + audios +
                ", notes=" + notes +
                ", photos=" + photos +
                ", groups=" + groups +
                ", gifts=" + gifts +
                ", friends=" + friends +
                ", online_friends=" + online_friends +
                ", user_photos=" + user_photos +
                ", followers=" + followers +
                ", subscriptions=" + subscriptions +
                ", pages=" + pages +
                ", date=" + date +
                ", uid=" + uid +
                '}';
    }
}
