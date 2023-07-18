package com.passenger.service.serviceImpl;

import com.passenger.entity.Passenger;
import com.passenger.exception.ResourceNotFoundException;
import com.passenger.payload.PassengerDto;
import com.passenger.repository.PassengerRepository;
import com.passenger.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger= mapToEntity(passengerDto);
        Passenger save = passengerRepo.save(passenger);
        return mapToDto(save);
    }

    @Override
    public List<PassengerDto> getAllPassenger(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageSize, pageNo, sort);
        Page<Passenger> all = passengerRepo.findAll(pageable);
        List<Passenger> content = all.getContent();
        return content.stream().map(passenger -> mapToDto(passenger)).collect(Collectors.toList());
    }

    @Override
    public PassengerDto getPassengerById(long id) {
        Passenger passengers = passengerRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("passenger not found with id:" + id)
        );
        return mapToDto(passengers);
    }

    @Override
    public PassengerDto updatePassenger(PassengerDto passengerDto, long id) {
        Passenger passengers = passengerRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("passenger not found with id:" + id)
        );
        passengers.setId(id);
        Passenger passenger = mapToEntity(passengerDto);
        Passenger save= passengerRepo.save(passenger);
        return mapToDto(save);
    }

    @Override
    public void deletePassenger(long id) {
        Passenger passengers = passengerRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("passenger not found with id:" + id)
        );
        passengerRepo.deleteById(id);
    }

    PassengerDto mapToDto(Passenger passenger){
        return modelMapper.map(passenger, PassengerDto.class);
    }
    Passenger mapToEntity(PassengerDto passengerDto){
        return modelMapper.map(passengerDto, Passenger.class);
    }
}
