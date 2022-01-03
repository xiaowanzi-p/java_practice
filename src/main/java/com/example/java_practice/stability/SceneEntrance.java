package com.example.java_practice.stability;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SceneEntrance {
}
