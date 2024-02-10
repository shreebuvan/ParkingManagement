import java.util.Scanner;
import java.util.ArrayList;

import constants.CommandConstants;

public class ParkingManagement {
    static ParkingManagement obj = new ParkingManagement();
    ArrayList<ParkingSlot> slots = new ArrayList<ParkingSlot>();
    int slotsAvailable = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Management!!! \nEnter your commands");
        while (true) {
            Scanner s = new Scanner(System.in);
            String[] command = s.nextLine().trim().split(" ");
            switch (command[0]) {
                case CommandConstants.CREATE_PARKING_LOT:
                    if (command.length == 1) {
                        System.out.println("The number of parking lots needs to be mentioned!!!");
                    } else {
                        obj.createParkingLot(Integer.parseInt(command[1]));
                    }
                    break;
                case CommandConstants.PARK:
                    if (command.length < 3) {
                        System.out.println("The vehicle Number and colour needs to be mentioned!!!");
                    } else {
                        obj.park(command[1], command[2]);
                    }
                    break;
                case CommandConstants.LEAVE:
                    if (command.length == 1) {
                        System.out.println("The parking lot number needs to be mentioned!!!");
                    } else {
                        obj.leave(Integer.parseInt(command[1]));
                    }
                    break;
                case CommandConstants.STATUS:
                    obj.status();
                    break;
                case CommandConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                    if (command.length == 1) {
                        System.out.println("The colour of the vehicle needs to be mentioned!!!");
                    } else {
                        obj.registrationNumbersForCarsWithColour(command[1]);
                    }
                    break;
                case CommandConstants.EXIT:
                	System.out.println("Exited!");
                	s.close();
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid command");
            }
        }
    }

    void createParkingLot(int slotCount) {
        if (slotCount <= 0) {
            System.out.println("The number of parking lots need to be greater than zero.");
        } else {
            slotsAvailable = slotCount;
            for (int slotNumber = 1; slotNumber <= slotCount; slotNumber++) {
                slots.add(new ParkingSlot(slotNumber));
            }
            System.out.println("Created a parking lot with " + slotCount + " slots");
        }
    }

    void park(String vehicleNumber, String vehicleColour) {
        if (slots.size() != 0) {
            if (slotsAvailable == 0) {
                System.out.println("Sorry, parking lot is full");
            } else {
                for (ParkingSlot slot: slots) {
                    if (slot.getAvailability()) {
                        slot.parkVehicle(vehicleNumber, vehicleColour);
                        slotsAvailable--;
                        break;
                    }
                }
            }
        } else {
            System.out.println("Parking lots need to be created first!!!");
        }
    }

    void leave(int slotNumber) {
        if (slots.size() != 0) {
            if (slotNumber <= 0) {
                System.out.println("The parking lot number needs to be greater than zero.");
            } else if (slotNumber > slots.size()) {
                System.out.println("The parking lot number needs to be less than " + slots.size());
            } else if (slotsAvailable == slots.size()) {
                System.out.println("There are no cars parked. All the parking slots are available.");
            } else {
                for (ParkingSlot slot: slots) {
                    if (slot.getSlotNumber() == slotNumber) {
                        if (!slot.getAvailability()) {
                            slot.removeVehicle();
                            slotsAvailable++;
                        } else {
                            System.out.println("The given parking slot number is not occupied");
                        }
                        break;
                    }
                }
            }
        } else {
            System.out.println("Parking lots need to be created first!!!");
        }
    }

    void status() {
        if (slots.size() != 0) {
            if (slotsAvailable == slots.size()) {
                System.out.println("There are no cars parked. All the parking slots are available.");
            } else {
                System.out.println("Slot No. Registration No. Colour");
                for (ParkingSlot slot: slots) {
                    if (!slot.getAvailability()) {
                        slot.getVehicleDetails();
                    }
                }
            }
        } else {
            System.out.println("Parking lots need to be created first!!!");
        }
    }

    void registrationNumbersForCarsWithColour(String colour) {
        if (slots.size() != 0) {
            ArrayList < String > registrationNumbers = new ArrayList<String>();
            for (ParkingSlot slot: slots) {
                if (slot.getVehicleColour().equals(colour)) {
                    registrationNumbers.add(slot.getVehicleNumber());
                }
            }
            if (registrationNumbers.size() != 0) {
                System.out.println(String.join(", ", registrationNumbers));
            } else {
                System.out.println("There are no cars parked with the given colour");
            }
        } else {
            System.out.println("Parking lots need to be created first!!!");
        }
    }
}