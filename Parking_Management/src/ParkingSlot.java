public class ParkingSlot {
    int slotNumber;
    String vehicleNumber;
    String vehicleColour;
    boolean isSlotAvailable;

    ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        vehicleNumber = "N/A";
        vehicleColour = "N/A";
        isSlotAvailable = true;
    }

    int getSlotNumber() {
        return slotNumber;
    }

    String getVehicleNumber() {
        return vehicleNumber;
    }

    String getVehicleColour() {
        return vehicleColour;
    }

    boolean getAvailability() {
        return isSlotAvailable;
    }

    void parkVehicle(String vehicleNumber, String vehicleColour) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleColour = vehicleColour;
        isSlotAvailable = false;
        System.out.println("Allocated slot number: " + slotNumber);
    }

    void removeVehicle() {
        vehicleNumber = "N/A";
        vehicleColour = "N/A";
        isSlotAvailable = true;
        System.out.println("Slot number " + slotNumber + " is free ");
    }

    void getVehicleDetails() {
        System.out.println("" + slotNumber + " " + vehicleNumber + " " + vehicleColour);
    }
}