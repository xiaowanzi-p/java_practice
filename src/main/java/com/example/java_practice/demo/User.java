package com.example.java_practice.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Comparable<User>{
    private Long id;

    @Override
    public int compareTo(User o) {
        if (this.id.longValue() > o.id.longValue()) {
            return 1;
        } else if (this.id.longValue() == o.id.longValue()) {
            return 0;
        } else {
            return -1;
        }
    }

}
