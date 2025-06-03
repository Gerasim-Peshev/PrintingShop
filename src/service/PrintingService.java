package service;

import exception.InvalidMachineTypeException;
import exception.OutOfPaperException;
import model.PrintMachine;
import model.Publication;

public class PrintingService {
    public void printPublication(PrintMachine machine, Publication pub)
            throws InvalidMachineTypeException, OutOfPaperException {
        machine.print(pub);
    }
}