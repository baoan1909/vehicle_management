package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.dtos.CustomerDTO;
import com.example.vehicle_management.models.*;
import com.example.vehicle_management.services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO toDTO(CustomerRegisterTicket customerRegisterTicket, ICustomerService customerService, ICardService cardService, IParkingFeeOfCustomerService parkingFeeOfCustomerService, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        Customer customer = customerService.getCustomerById(customerRegisterTicket.getCustomerId());
        String fullName = (customer != null) ? customer.getFullName() : "";
        String gender = (customer != null) ? customer.getGender() : "";
        String address = (customer != null) ? customer.getAddress() : "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = (customer != null) ? customer.getDateOfBirth() : null;


        String email = (customer != null) ? "a@gmail.com" : "";
        String identifyCard = (customer != null) ? customer.getIdentifyCard() : "";
        String phoneNumber = (customer != null) ? customer.getPhoneNumber() : "";

        Card card = cardService.getCardById(customerRegisterTicket.getCardId());
        String cardNumber = (card != null) ? card.getCardNumber() : "";
        ParkingFeeOfCustomer parkingFeeOfCustomer = parkingFeeOfCustomerService.getParkingFeeOfCustomerById(customerRegisterTicket.getFeeCustomerId());

        VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(customerRegisterTicket.getVehicleTypeId());
        String vehicleTypeName =(vehicleType!= null) ? vehicleType.getVehicleTypeName() : "";
        TicketType ticketType = ticketTypeService.getTicketTypeById(customerRegisterTicket.getTicketTypeId());
        String ticketTypeName =(ticketType!= null) ? ticketType.getTicketTypeName() : "";
        return new CustomerDTO(
                customerRegisterTicket.getCustomerRegisterTicketId(),
                customerRegisterTicket.getCardId(),
                cardNumber,
                customerRegisterTicket.getCustomerId(),
                fullName,
                gender,
                address,
                dateOfBirth,
                email,
                identifyCard,
                customerRegisterTicket.getFeeCustomerId(),
                phoneNumber,
                customerRegisterTicket.getVehicleTypeId(),
                vehicleTypeName,
                customerRegisterTicket.getLicensePlate(),
                customerRegisterTicket.getTicketTypeId(),
                ticketTypeName,
                customerRegisterTicket.getEffectiveDate(),
                customerRegisterTicket.getExpirationDate(),
                customerRegisterTicket.getPrice()
        );
    }
    public static List<CustomerDTO> toDTOList(List<CustomerRegisterTicket> customerRegisterTickets, ICustomerService customerService, ICardService cardService, IParkingFeeOfCustomerService parkingFeeOfCustomerService, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        return customerRegisterTickets.stream()
                .map(customerRegisterTicket -> toDTO(customerRegisterTicket, customerService, cardService, parkingFeeOfCustomerService, vehicleTypeService, ticketTypeService))
                .collect(Collectors.toList());
    }

}
