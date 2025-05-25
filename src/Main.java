import enums.PaperSize;
import enums.PaperType;
import exception.*;
import model.*;
import service.*;
import storage.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Paper paper = new Paper(PaperType.NORMAL, 0.05);
        Publication pub = new Publication("Sample Book", 120, PaperSize.A4, paper, true);

        PrintMachine machine = new PrintMachine("PM001", true, 30, 500);
        machine.loadPaper(200);

        PrintingService printingService = new PrintingService();
        PricingService pricingService = new PricingService(100, 10);
        MachineService machineService = new MachineService();

        try {
            printingService.printPublication(machine, pub);
            double paperCost = pricingService.calculatePaperCost(pub);
            double printCost = pricingService.calculatePrintCost(pub, 1.00);
            int totalPrinted = machineService.getTotalPagesPrinted(machine);
            System.out.println("Printed successfully. Pages: " + totalPrinted);
            System.out.format("Paper cost: %.2f, Print cost: %.2f%n", paperCost, printCost);
            FileManager.writeToFile("report.txt", "Total pages: " + totalPrinted);
        } catch (InvalidMachineTypeException | OutOfPaperException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Manager manager = new Manager("John Doe", 1000, 15);
        try {
            SerializationUtil.serializeEmployee(manager, "manager.ser");
            Employee deserialized = SerializationUtil.deserializeEmployee("manager.ser");
            System.out.println("Deserialized employee: " + deserialized.getName());
        } catch (Exception e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }
}