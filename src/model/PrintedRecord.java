package model;

public class PrintedRecord {
    private Publication publication;
    private int copiesPrinted;

    public PrintedRecord(Publication publication, int copiesPrinted) {
        this.publication = publication;
        this.copiesPrinted = copiesPrinted;
    }

    public Publication getPublication() {
        return publication;
    }

    public int getCopiesPrinted() {
        return copiesPrinted;
    }
}