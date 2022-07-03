package tm;

public class itemTM {
    private String itemcode;
    private String description;
    private String packsize;
    private int qty;
    private double unitprice;
    private String itemtype;

    public itemTM() {
    }

    public itemTM(String itemcode, String description, String packsize, int qty, double unitprice, String itemtype) {
        this.setItemcode(itemcode);
        this.setDescription(description);
        this.setPacksize(packsize);
        this.setQty(qty);
        this.setUnitprice(unitprice);
        this.setItemtype(itemtype);
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    @Override
    public String toString() {
        return "itemTM{" +
                "itemcode='" + itemcode + '\'' +
                ", description='" + description + '\'' +
                ", packsize='" + packsize + '\'' +
                ", qty=" + qty +
                ", unitprice=" + unitprice +
                ", itemtype='" + itemtype + '\'' +
                '}';
    }
}
