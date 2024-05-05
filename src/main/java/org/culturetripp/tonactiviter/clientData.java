package org.culturetripp.tonactiviter;

import java.util.Date;

public class clientData {

    private Integer id;
    private String type;
    private Integer quantity;
    private double total;
    private Date date;

    public clientData(Integer id, String type, Integer quantity, double total, Date date) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;

    }

    public Date getDate() {
        return date;
    }
}
