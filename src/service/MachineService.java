package service;

import model.PrintMachine;

public class MachineService {
    public int getTotalPagesPrinted(PrintMachine machine) {
        return machine.getTotalPagesPrinted();
    }
}