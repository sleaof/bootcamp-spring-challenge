package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.DTOs.PostDTO;
import com.digitalhouse.demo.DTOs.ResponseFollowedPostsDTO;
import com.digitalhouse.demo.models.Product;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final List<PostDTO> database;
    public PostRepositoryImpl() { database = loadDataBase(); }

    @Override
    public List<PostDTO> getAll() {
        return database;
    }

    @Override
    public PostDTO getById(Long idPost) {
        Optional<PostDTO> found = database.stream().filter(x -> x.getId_post().equals(idPost)).findFirst();
        PostDTO postDTO = null;

        if(found.isPresent()) {
            postDTO = found.get();
        }

        return postDTO;
    }

    @Override
    public void createPost(PostDTO postDTO) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        File file = null;
        FileWriter writeFile = null;
        JSONObject objPostDTO = new JSONObject();
        JSONObject objProduct = new JSONObject();

        objProduct.put("product_id", postDTO.getDetail().getProduct_id());
        objProduct.put("productName", postDTO.getDetail().getProductName());
        objProduct.put("type", postDTO.getDetail().getType());
        objProduct.put("brand", postDTO.getDetail().getBrand());
        objProduct.put("color", postDTO.getDetail().getColor());
        objProduct.put("notes", postDTO.getDetail().getNotes());

        objPostDTO.put("userId", postDTO.getUserId());
        objPostDTO.put("id_post", postDTO.getId_post());
        objPostDTO.put("date", postDTO.getDate());
        objPostDTO.put("detail", objProduct);
        objPostDTO.put("category", postDTO.getCategory());
        objPostDTO.put("price", postDTO.getPrice());

        try {
            file = ResourceUtils.getFile("classpath:posts.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        jsonArray.add(objPostDTO);
        try {
            writeFile = new FileWriter(ResourceUtils.getFile("classpath:posts.json"));
            writeFile.write(jsonArray.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PostDTO> getPostsById(Long userId) {
        return database.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
    }

    private List<PostDTO> loadDataBase() {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        File file = null;
        List<PostDTO> postsDTO = new ArrayList<>();

        try {
            file = ResourceUtils.getFile("classpath:posts.json");
            jsonArray = (JSONArray) parser.parse(new FileReader(file));

            if(jsonArray.size() > 0) {
                jsonArray.forEach(x -> {
                    JSONObject item = (JSONObject) x;
                    PostDTO postDTO = new PostDTO();
                    Product product = new Product();
                    JSONObject detail = (JSONObject) item.get("detail");

                    product.setProduct_id((Long) detail.get("product_id"));
                    product.setProductName((String) detail.get("productName"));
                    product.setType((String) detail.get("type"));
                    product.setBrand((String) detail.get("brand"));
                    product.setColor((String) detail.get("color"));
                    product.setNotes((String) detail.get("notes"));

                    postDTO.setDate((String) item.get("date"));
                    postDTO.setUserId((Long) item.get("userId"));
                    postDTO.setId_post((Long) item.get("id_post"));
                    postDTO.setDetail(product);
                    postDTO.setCategory((Long) item.get("category"));
                    postDTO.setPrice((Double) item.get("price"));
                    postsDTO.add(postDTO);
                });
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return postsDTO;
    }
}
