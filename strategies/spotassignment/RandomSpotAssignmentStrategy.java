package strategies.spotassignment;

import models.*;
import repository.ParkingLotRepository;
import repository.ParkingSpotRepository;

import java.util.List;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy{
    private ParkingLotRepository parkingLotRepository;
    private ParkingSpotRepository parkingSpotRepository;
    public RandomSpotAssignmentStrategy(ParkingLotRepository parkingLotRepository){
        this.parkingLotRepository=parkingLotRepository;
    }
    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLotWithGate(gate);
        List<ParkingSpot> parkingSpotList = parkingSpotRepository.getParkingSpotByParkingLot(parkingLot);
        for(ParkingSpot parkingSpot:parkingSpotList){
            if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)
            && parkingSpot.getSupportedVechileType().equals(vehicleType)){
                return parkingSpot;
            }
        }
        return null;
    }
}
