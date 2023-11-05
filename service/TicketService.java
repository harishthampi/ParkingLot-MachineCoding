package service;

import exception.NoParkingSpotAvailableException;
import models.*;
import repository.TicketRepository;
import strategies.spotassignment.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {
    private VehicleService vehicleService;
    private  GateService gateService;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketRepository ticketRepository;
    public TicketService(VehicleService vehicleService,GateService gateService,SpotAssignmentStrategy spotAssignmentStrategy,TicketRepository ticketRepository){
        this.gateService=gateService;
        this.vehicleService=vehicleService;
        this.spotAssignmentStrategy=spotAssignmentStrategy;
        this.ticketRepository=ticketRepository;
    }
    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType,Long gateId) throws NoParkingSpotAvailableException {
        /*
        Check if Vehicle is present in database
        1.VehicleService.getVehicleByNumber() - will use this approach
    OR  2.VehicleRepository.fetchByNumber()

        if yes get the vehicle object
        else create a vehicle object
        then
        create a ticket
         */
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        if(vehicle==null){
            vehicle=vehicleService.registerVehicle(vehicleNumber,vehicleType);
        }
        Gate gate = gateService.getGate(gateId);
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getOperator());
        ticket.setGate(gate);
        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType,gate);
        if(parkingSpot==null){
            throw new NoParkingSpotAvailableException("No available spot for parking");
        }
        ticket.setParkingSpot(parkingSpot);
        Ticket savedTicket = ticketRepository.saveTicket(ticket);
        return savedTicket;
    }
}
