package com.digitalhouse.demo.DTOs;

import javax.persistence.Column;
import java.util.Objects;

public class UserCountDTO {

    private Integer userId;
    private String userName;
    private Long followers_count;

    public UserCountDTO(Integer userId, String userName, Long followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public UserCountDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Long followers_count) {
        this.followers_count = followers_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCountDTO that = (UserCountDTO) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getFollowers_count(), that.getFollowers_count());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getFollowers_count());
    }

    @Override
    public String toString() {
        return "UserCountDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", followers_count=" + followers_count +
                '}';
    }
}
