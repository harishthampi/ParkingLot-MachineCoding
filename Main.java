import controller.TicketController;
import dto.GenerateTicketRequestDto;
import dto.GenerateTicketResponseDto;
import models.VehicleType;
import repository.ParkingLotRepository;
import repository.TicketRepository;
import service.GateService;
import service.TicketService;
import service.VehicleService;
import strategies.spotassignment.RandomSpotAssignmentStrategy;
import strategies.spotassignment.SpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        ObjectRegistry objectRegistry = new ObjectRegistry();
        VehicleService vehicleService = new VehicleService();
        GateService gateService = new GateService();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotRepository);
        TicketService ticketService = new TicketService(vehicleService,gateService,spotAssignmentStrategy,ticketRepository);
        TicketController ticketController = new TicketController(ticketService);
        objectRegistry.register("ticket repository",ticketRepository);
        GenerateTicketRequestDto request =  new GenerateTicketRequestDto();
        request.setVehicleNumber("HL1145");
        request.setVehicleType(VehicleType.LARGE);
        request.setGateId(3L);
        GenerateTicketResponseDto responseDto = ticketController.generateTicket(request);
    }
}

/*
Requirements-1

1.Operator should be able to generate a ticket
2.Admin should be able to create a new parking lot

Requirement-1
1.Create a ParkingLot

MVC
Controller
Service
Repository

1.Identify which model is the requirement around(CRUD) - Ticket
2.Create Controller,Service,Repository for that model
     -TicketController
     -TicketService
     -TicketRepository
 */