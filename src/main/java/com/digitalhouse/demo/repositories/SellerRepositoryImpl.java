package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.models.Seller;
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
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class SellerRepositoryImpl implements SellerRepository {
    private final List<Seller> database;
    public SellerRepositoryImpl() { database = loadDataBase(); }

    @Override
    public List<Seller> getAll() {
        return database;
    }

    @Override
    public Seller getById(Long userId) {
        Optional<Seller> found = database.stream().filter(x -> x.getUserId().equals(userId)).findFirst();
        Seller seller = null;

        if(found.isPresent()) {
            seller = found.get();
        }

        return seller;
    }

    @Override
    public void follow(Seller seller, Long userId) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        File file = null;
        FileWriter writeFile = null;
        JSONObject sellerObj = new JSONObject();
        List<Long> newFollowers = new ArrayList<>();
        newFollowers = seller.getFollowers();
        newFollowers.add(userId);

        sellerObj.put("userName", seller.getUserName());
        sellerObj.put("userId", seller.getUserId());
        sellerObj.put("followers", newFollowers);

        try {
            file = ResourceUtils.getFile("classpath:sellers.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            if(obj.get("userId").toString().equals(seller.getUserId().toString())) {
                jsonArray.remove(i);
            }
        }

        jsonArray.add(sellerObj);
        try {
            writeFile = new FileWriter(ResourceUtils.getFile("classpath:sellers.json"));
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unfollow(Seller seller, Long userId) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        File file = null;
        FileWriter writeFile = null;
        JSONObject sellerObj = new JSONObject();
        List<Long> newFollowers = new ArrayList<>();
        newFollowers = seller.getFollowers();
        newFollowers.remove(userId);

        sellerObj.put("userName", seller.getUserName());
        sellerObj.put("userId", seller.getUserId());
        sellerObj.put("followers", newFollowers);

        try {
            file = ResourceUtils.getFile("classpath:sellers.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            if(obj.get("userId").toString().equals(seller.getUserId().toString())) {
                jsonArray.remove(i);
            }
        }

        jsonArray.add(sellerObj);
        try {
            writeFile = new FileWriter(ResourceUtils.getFile("classpath:sellers.json"));
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Seller> loadDataBase() {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        File file = null;
        List<Seller> sellers = new ArrayList<>();

        try {
            file = ResourceUtils.getFile("classpath:sellers.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

            if(jsonArray.size() > 0) {
                jsonArray.forEach(x -> {
                    JSONObject item = (JSONObject) x;
                    Seller seller = new Seller();
                    seller.setUserId((Long) item.get("userId"));
                    seller.setUserName((String) item.get("userName"));
                    seller.setFollowers((List<Long>) item.get("followers"));
                    sellers.add(seller);
                });
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return sellers;
    }
}
