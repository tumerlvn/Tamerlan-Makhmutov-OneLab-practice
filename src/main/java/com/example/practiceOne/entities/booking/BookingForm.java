package com.example.practiceOne.entities.booking;

import com.example.practiceOne.entities.additions.SeatClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingForm {
    private Long customerId;
    private Long flightId;
    private Integer baggageAmount;
    private SeatClass seatClass;
}
