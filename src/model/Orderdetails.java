package model;

public class Orderdetails {
    private String oid;
    private String icode;
    private int qty;
    private double discount;

    public Orderdetails() {
    }

    public Orderdetails(String oid, String icode, int qty, double discount) {
        this.setOid(oid);
        this.setIcode(icode);
        this.setQty(qty);
        this.setDiscount(discount);
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "orderdetails{" +
                "oid='" + oid + '\'' +
                ", icode='" + icode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }
}
