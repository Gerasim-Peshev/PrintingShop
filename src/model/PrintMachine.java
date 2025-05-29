package model;

import exception.InvalidMachineTypeException;
import exception.OutOfPaperException;

import java.util.*;

public class PrintMachine {
    private String id;
    private boolean colorCapable;
    private int pagesPerMinute;
    private int maxPaper;
    private int currentPaper;
    private List<PrintedRecord> printedRecords = new ArrayList<>();
    public PrintMachine(String id, boolean colorCapable, int pagesPerMinute, int maxPaper) {
        this.id = id;
        this.colorCapable = colorCapable;
        this.pagesPerMinute = pagesPerMinute;
        this.maxPaper = maxPaper;
        this.currentPaper = 0;
    }

    public void loadPaper(int sheets) {
        if (currentPaper + sheets > maxPaper) currentPaper = maxPaper;
        else currentPaper += sheets;
    }

    public void print(Publication pub) throws InvalidMachineTypeException, OutOfPaperException {
        if (pub.isColor() && !colorCapable) {
            throw new InvalidMachineTypeException("Cannot print color on BW machine");
        }

        int totalPages = pub.getCopies();
        if (currentPaper < totalPages) {
            throw new OutOfPaperException("Not enough paper");
        }
        currentPaper -= totalPages;
        printedRecords.add(new PrintedRecord(pub, pub.getCopies()));
    }

    public int getTotalPagesPrinted() {
        return printedRecords.stream()
                .mapToInt(r -> r.getPublication().getCopies())
                .sum();
    }

    public List<PrintedRecord> getPrintedRecords() {
        return printedRecords;
    }
}