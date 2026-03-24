package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProxyController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ProxyService catalanService;

    public ProxyController(ProxyService catalanService){
        this.catalanService = catalanService;
    }

    @GetMapping("/catalan")
    public String catalan(@RequestParam(value = "num") int num) {
        String catalanResponse = "no funciono";
        try{
            catalanResponse = catalanService.catalan(num, "http://ec2-98-93-151-37.compute-1.amazonaws.com:8080/catalan");
        }catch(Exception e){
            try{
                catalanResponse = catalanService.catalan(num, "http://ec2-98-92-198-216.compute-1.amazonaws.com:8080/catalan");
            }catch (Exception ex){

            }
        }
        return catalanResponse;
    }
}
