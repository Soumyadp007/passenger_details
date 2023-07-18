package com.passenger.service;

import com.passenger.payload.PassengerDto;

import java.util.List;

public interface PassengerService {
    PassengerDto createPassenger(PassengerDto passengerDto);

    List<PassengerDto> getAllPassenger(int pageNo, int pageSize, String sortBy, String sortDir);

    PassengerDto getPassengerById(long id);

    PassengerDto updatePassenger(PassengerDto passengerDto, long id);

    void deletePassenger(long id);
}
