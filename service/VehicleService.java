package service;

import models.Vehicle;
import models.VehicleType;
import repository.VehicleRepository;

import java.util.Vector;

public class VehicleService {
    private VehicleRepository vehicleRepository;
    public VehicleService(){

    }
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }
    public Vehicle getVehicle(String vehicleNumber){
        return null;
    }
    public Vehicle registerVehicle(String vehicleNumber,VehicleType vehicleType){
        /*Ideally we will have an object of VehicleRepository and you will call DB to get vehicle
        vehicleRepository.fetchVehicleFromDBByVehicleNumber(number);
         */
        Vehicle v = new Vehicle();
        v.setNumber("HR45665");
        v.setVehicleType(VehicleType.LARGE);
        return v;
    }
}
