package com.passenger.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
}
