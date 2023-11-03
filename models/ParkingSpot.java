package models;

import java.util.List;

public class ParkingSpot extends BaseModel{
    private List<VehicleType> supportedVechileType;
    private ParkingSpotStatus parkingSpotStatus;
    private int number;
    public List<VehicleType> getSupportedVechileType() {
        return supportedVechileType;
    }

    public void setSupportedVechileType(List<VehicleType> supportedVechileType) {
        this.supportedVechileType = supportedVechileType;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

