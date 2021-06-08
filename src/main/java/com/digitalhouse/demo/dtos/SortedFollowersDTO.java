package com.digitalhouse.demo.dtos;

import java.util.Comparator;

public class SortedFollowersDTO implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
