package model;

import enums.PaperSize;

public class Publication {
    private String title;
    private int copies;
    private PaperSize size;
    private Paper paper;
    private boolean isColor;

    public Publication(String title, int copies, PaperSize size, Paper paper, boolean isColor) {
        this.title = title;
        this.copies = copies;
        this.size = size;
        this.paper = paper;
        this.isColor = isColor;
    }

    public int getCopies() {
        return copies;
    }

    public Paper getPaper() {
        return paper;
    }

    public PaperSize getSize() {
        return size;
    }

    public boolean isColor() {
        return isColor;
    }

    public String getTitle() {
        return title;
    }
}