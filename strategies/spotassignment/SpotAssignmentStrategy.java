package strategies.spotassignment;

import models.Gate;
import models.ParkingSpot;
import models.Vehicle;
import models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
