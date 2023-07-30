package com.example.practiceOne.entities.additions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Builder
@Getter
public class EmptyResponse {
    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("time-stamp")
    private LocalDate timeStamp;
}
