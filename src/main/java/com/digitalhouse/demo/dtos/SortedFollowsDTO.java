package com.digitalhouse.demo.dtos;

import java.util.Comparator;

public class SortedFollowsDTO implements Comparator<SellerDTO> {
    @Override
    public int compare(SellerDTO o1, SellerDTO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
