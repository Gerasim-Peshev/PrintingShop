package test;

import enums.PaperSize;
import enums.PaperType;
import model.*;
import exception.*;
import service.*;
import storage.SerializationUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

public class PrintshopFullTest {

    @Test
    public void testPaperPriceCalculation() {
        Paper paper = new Paper(PaperType.GLOSSY, .1);
        assertEquals(.1, paper.getPrice(PaperSize.A5));
        assertEquals(.12, paper.getPrice(PaperSize.A4));
        assertEquals(.15, paper.getPrice(PaperSize.A3));
    }

    @Test
    public void testPrintMachineLoadAndPrint() throws Exception {
        PrintMachine machine = new PrintMachine("M1", true, 50, 1000);
        machine.loadPaper(500);
        Publication pub = new Publication("Book", 200, PaperSize.A4,
                new Paper(PaperType.NORMAL, 0.05), true);

        machine.print(pub);
        assertEquals(200, machine.getTotalPagesPrinted());
        assertThrows(OutOfPaperException.class, () -> {
            Publication bigPub = new Publication("BigBook", 400, PaperSize.A4,
                    new Paper(PaperType.NORMAL, 0.05), true);
            machine.print(bigPub);
        });
    }

    @Test
    public void testInvalidMachineTypeException() {
        PrintMachine bwMachine = new PrintMachine("BW1", false, 30, 500);
        Publication colorPub = new Publication("ColorMag", 50, PaperSize.A5,
                new Paper(PaperType.GLOSSY, 0.10), true);

        assertThrows(InvalidMachineTypeException.class, () -> {
            bwMachine.print(colorPub);
        });
    }

    @Test
    public void testPricingServiceWithDiscount() {
        PricingService pricing = new PricingService(100, 10);
        Paper paper = new Paper(PaperType.NORMAL, 0.05);
        Publication pub = new Publication("TestPub", 150, PaperSize.A5, paper, false);

        double paperCost = pricing.calculatePaperCost(pub);
        assertEquals(7.5d, paperCost);

        double printCost = pricing.calculatePrintCost(pub, 1.0);
        assertEquals(135.0, printCost, 0.01);
    }

    @Test
    public void testManagerSalaryWithBonus() {
        Manager manager = new Manager("Boss", 1000, 20);
        double threshold = 10000;
        double revenue = 15000;

        assertEquals(1200d, manager.getSalaryWithBonus(revenue, threshold));
        assertEquals(1000d, manager.getSalaryWithBonus(9000d, threshold));
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        Manager manager = new Manager("Manager", 1000, 10);
        String fileName = "test_manager.ser";

        SerializationUtil.serializeEmployee(manager, fileName);
        Employee deserialized = SerializationUtil.deserializeEmployee(fileName);

        assertEquals(manager.getName(), deserialized.getName());
        assertEquals(manager.getBaseSalary(), deserialized.getBaseSalary());

        new File(fileName).delete();
    }

    @Test
    public void testFileManagerReadWrite() throws IOException {
        String fileName = "test_report.txt";
        String content = "Sample report content";

        storage.FileManager.writeToFile(fileName, content);
        String readContent = storage.FileManager.readFromFile(fileName);

        assertEquals(content, readContent);

        new File(fileName).delete();
    }
}