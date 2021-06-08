package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.exceptions.*;
import com.digitalhouse.demo.services.*;
import com.digitalhouse.demo.validations.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;
    private Validation validation;

    public UserController(UserService service, Validation validation) {
        this.service = service;
        this.validation = validation;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followById(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        if (!validation.validateById(userId) || !validation.validateById(userIdToFollow)
                || validation.validateIsEmpty(userId) || validation.validateIsEmpty(userIdToFollow))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateByEqualId(userId, userIdToFollow))
            return new ResponseEntity(new ConflictException("Parameter conflict. You can't pass equal values."), HttpStatus.CONFLICT);

        if (!validation.validateIfIsASeller(userIdToFollow))
            return new ResponseEntity(new BadRequestException("Invalid operation. A user cannot follow another user."), HttpStatus.BAD_REQUEST);

        if (service.follow(userId, userIdToFollow))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(new BadRequestException("Your operation could not be completed. Check the information."), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<NumberOfUsersFollowASellerDTO> numberOfUsersFollowASeller(@PathVariable Integer userId) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (!validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. A user who is not a seller has no followers."), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<NumberOfUsersFollowASellerDTO>(service.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ListOfUsersFollowASellerDTO> listOfUsersFollowASeller(@PathVariable Integer userId) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (!validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. If you're not a seller, you don't have followers."), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ListOfUsersFollowASellerDTO>(service.listOfUsersFollowASeller(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<ListOfSellersFollowAUserDTO> listOfSellersFollowAUser(@PathVariable Integer userId) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. If you're a seller, you can't follow anyone."), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ListOfSellersFollowAUserDTO>(service.listOfSellersFollowAUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowASeller(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        if (!validation.validateById(userId) || !validation.validateById(userIdToUnfollow)
                || validation.validateIsEmpty(userId) || validation.validateIsEmpty(userIdToUnfollow))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateByEqualId(userId, userIdToUnfollow))
            return new ResponseEntity(new ConflictException("Parameter conflict. You can't pass equal values."), HttpStatus.CONFLICT);

        if (!validation.validateIfIsASeller(userIdToUnfollow))
            return new ResponseEntity(new BadRequestException("Invalid operation. A user cannot unfollow another user."), HttpStatus.BAD_REQUEST);

        if (service.unfollow(userId, userIdToUnfollow))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(new BadRequestException("Your operation could not be completed. Check the information."), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followers/orderedList")
    public ResponseEntity<SortByNameDTO> sortFollowersByName(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (!validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. If you're not a seller, you don't have followers."), HttpStatus.BAD_REQUEST);

        if (order.equalsIgnoreCase("name_asc")) {
            return new ResponseEntity(service.sortFollowersByNameAsc(userId), HttpStatus.OK);
        } else if (order.equalsIgnoreCase("name_desc")) {
            return new ResponseEntity(service.sortFollowersByNameDesc(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity(new BadRequestException("Your operation could not be completed. Check the information."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/followed/orderedList")
    public ResponseEntity<SortByNameDTO> sortFollowedByName(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. If you're not a seller, you don't have followers."), HttpStatus.BAD_REQUEST);

        if (order.equalsIgnoreCase("name_asc")) {
            return new ResponseEntity(service.sortFollowedByNameAsc(userId), HttpStatus.OK);
        } else if (order.equalsIgnoreCase("name_desc")) {
            return new ResponseEntity(service.sortFollowedByNameDesc(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity(new BadRequestException("Your operation could not be completed. Check the information."), HttpStatus.BAD_REQUEST);
        }
    }
}
