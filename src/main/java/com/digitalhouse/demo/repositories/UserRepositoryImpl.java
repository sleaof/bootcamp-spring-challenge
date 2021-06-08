package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.models.Seller;
import com.digitalhouse.demo.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> database;
    public UserRepositoryImpl() { database = loadDataBase(); }

    @Override
    public List<User> getAll() {
        return database;
    }

    @Override
    public User getById(Long userId) {
        Optional<User> found = database.stream().filter(x -> x.getUserId().equals(userId)).findFirst();
        User user = null;

        if(found.isPresent()) {
            user = found.get();
        }

        return user;
    }

    @Override
    public void follow(User user, Long userIdToFollow) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        File file = null;
        FileWriter writeFile = null;
        JSONObject userObj = new JSONObject();
        List<Long> newFolloweds = new ArrayList<>();
        newFolloweds = user.getFollowed();
        newFolloweds.add(userIdToFollow);

        userObj.put("userName", user.getUserName());
        userObj.put("userId", user.getUserId());
        userObj.put("followed", newFolloweds);

        try {
            file = ResourceUtils.getFile("classpath:users.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            if(obj.get("userId").toString().equals(user.getUserId().toString())) {
                jsonArray.remove(i);
            }
        }

        jsonArray.add(userObj);
        try {
            writeFile = new FileWriter(ResourceUtils.getFile("classpath:users.json"));
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unfollow(User user, Long userIdToUnfollow) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        File file = null;
        FileWriter writeFile = null;
        JSONObject userObj = new JSONObject();
        List<Long> newFolloweds = new ArrayList<>();
        newFolloweds = user.getFollowed();
        newFolloweds.remove(userIdToUnfollow);

        userObj.put("userName", user.getUserName());
        userObj.put("userId", user.getUserId());
        userObj.put("followed", newFolloweds);

        try {
            file = ResourceUtils.getFile("classpath:users.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            if(obj.get("userId").toString().equals(user.getUserId().toString())) {
                jsonArray.remove(i);
            }
        }

        jsonArray.add(userObj);
        try {
            writeFile = new FileWriter(ResourceUtils.getFile("classpath:users.json"));
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> loadDataBase() {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        File file = null;
        List<User> users = new ArrayList<>();

        try {
            file = ResourceUtils.getFile("classpath:users.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

            if(jsonArray.size() > 0) {
                jsonArray.forEach(x -> {
                    JSONObject item = (JSONObject) x;
                    User user = new User();
                    user.setUserId((Long) item.get("userId"));
                    user.setUserName((String) item.get("userName"));
                    user.setFollowed((List<Long>) item.get("followed"));
                    users.add(user);
                });
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return users;
    }
}
