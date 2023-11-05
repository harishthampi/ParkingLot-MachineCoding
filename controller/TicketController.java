package controller;
import dto.GenerateTicketRequestDto;
import dto.GenerateTicketResponseDto;
import dto.ResponseStatus;
import exception.NoParkingSpotAvailableException;
import models.Ticket;
import service.TicketService;

/*First layer of client interaction
requirement-Generate a ticket

    If we get the details of vehicle and gate we can generate a ticket.
    1.Get the vehicle.
    2.Generate Ticket.

    The TicketController calls the TicketService
    TicketService- generateTicket()
    we need to get vehicle details
    so call VehicleService - getVehicleByNumber() - if vehicle information is already in database pull it.
    if not createVehicle()
    assignSpot()
    createTicketObjectAndStore()


 */
public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto generateTicketRequestDto){
        try{
            Ticket ticket=ticketService.generateTicket(generateTicketRequestDto.getVehicleNumber(),
                    generateTicketRequestDto.getVehicleType(),
                    generateTicketRequestDto.getGateId());
            GenerateTicketResponseDto response = new GenerateTicketResponseDto();
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setTicket(ticket);
            return response;
        }catch (NoParkingSpotAvailableException noParkingSpotAvailableException){
            GenerateTicketResponseDto response = new GenerateTicketResponseDto();
            response.setResponseStatus(ResponseStatus.FAILURE);
            return  response;
        }

    }
}
/*
Problems with directly involving models as parameter(example-generateTicket(Vehicle vehicle, Gate gate)).
1.if we need to alter the number or type of params,current clients will start failing.
suppose change generateTicket(Vehicle vehicle, Gate gate) to generateTicket(Vehicle vehicle, Gate gate,Operator operator).
2.Models are internal implementation details of the system and we may not want them exposed to the client.
3.Client has to do a lot of GET requests (Get vehicle,Get gate etc)to be able to make this network call.

Solution - DTO Data Transfer Object

 */
