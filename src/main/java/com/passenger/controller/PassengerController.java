package com.passenger.controller;

import com.passenger.payload.PassengerDto;
import com.passenger.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto){
        PassengerDto dto = passengerService.createPassenger(passengerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<PassengerDto> getAllPassenger(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5" , required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
        return passengerService.getAllPassenger(pageNo, pageSize, sortBy, sortDir);
    }
    //http://localhost:9090/api/passenger/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable("id") long id){
        PassengerDto dto = passengerService.getPassengerById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody PassengerDto passengerDto, @PathVariable("id") long id){
        PassengerDto dto = passengerService.updatePassenger(passengerDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable("id") long id){
        passengerService.deletePassenger(id);
        return new ResponseEntity<>("Passenger is deleted!" , HttpStatus.OK);
    }
}
