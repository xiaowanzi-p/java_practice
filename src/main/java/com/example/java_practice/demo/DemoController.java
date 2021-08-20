package com.example.java_practice.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.mongo.model.Product;
import com.example.java_practice.mongo.repositories.ProductRepo;
import com.example.java_practice.nio.NioServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private NioServer nioServer;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/test")
    public boolean test() {
        Product product = new Product();
        product.setName("汽水");
        product.setPrice(14);
        product.setUuid("duushushiushishiu1");
        productRepo.save(product);
        return true;
    }

    @PostMapping("/test2")
    public boolean test2() {
        Page<Product> test = productRepo.findAllByName("test", PageRequest.of(0, 1));
        return true;
    }
}

