package model;

public class Order {
    private String orderid;
    private String custId;
    private double date;
    private double price;

    public Order() {
    }

    public Order(String orderid, String custId, double date, double price) {
        this.setOrderid(orderid);
        this.setCustId(custId);
        this.setDate(date);
        this.setPrice(price);
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderid='" + orderid + '\'' +
                ", custId='" + custId + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
