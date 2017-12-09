package com.noticemedan.cinema.entity;

public class RoomEntity {
    private int id;
    private int rowAmount;
    private int columnAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowAmount() {
        return rowAmount;
    }

    public void setRowAmount(int rowAmount) {
        this.rowAmount = rowAmount;
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public void setColumnAmount(int columnAmount) {
        this.columnAmount = columnAmount;
    }

    public RoomEntity(int id, int rowAmount, int columnAmount) {
        this.id = id;
        this.rowAmount = rowAmount;
        this.columnAmount = columnAmount;
    }
}
