package com.example.a1084081.oriondemoapp.util;

import com.example.a1084081.oriondemoapp.model.User;

import java.util.Comparator;

/**
 * Created by 1084081 on 5/1/17.
 */

public class AtoZComparator implements Comparator<User> {
    @Override
    public int compare(User o, User t1) {
        return o.getName().compareTo(t1.getName());
    }

}
