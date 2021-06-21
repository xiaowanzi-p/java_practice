package com.example.java_practice.arithmetic.select;

import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Demo {

    public static void main(String[] a) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1620705600000L), ZoneOffset.UTC);
        System.out.println(zonedDateTime);
    }
}
