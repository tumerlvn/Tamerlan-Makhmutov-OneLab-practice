package com.example.practiceOne.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
public class CompletableFutureController {
    @GetMapping("/future")
    public ResponseEntity<List<ResponseEntity<Object>>> getEndpointsThroughFuture() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("authorization"));
        HttpEntity<String> newRequest = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String host = "http://localhost:8080";
        List<CompletableFuture<ResponseEntity<Object>>> futuresList = Stream
                .of(
                "/customer/all",
                "/customer/1/tickets",
                "/customer/my-tickets",
                "/flight"
                )
                .map(e -> host + e)
                .map(e -> CompletableFuture.supplyAsync(() -> restTemplate.exchange(e, HttpMethod.GET, newRequest, Object.class)))
                .toList();

        CompletableFuture[] futures = futuresList.toArray(new CompletableFuture[futuresList.size()]);

        try {
            CompletableFuture.allOf(futures).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        List<ResponseEntity<Object>> result = Arrays.stream(futures).map(e -> (ResponseEntity<Object>) e.join()).toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
