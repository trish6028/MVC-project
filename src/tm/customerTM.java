package tm;

public class customerTM {
    private String custid;
    private String title;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalcode;

    public customerTM() {
    }

    public customerTM(String custid, String title, String name, String address, String city, String province, String postalcode) {
        this.setCustid(custid);
        this.setTitle(title);
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setProvince(province);
        this.setPostalcode(postalcode);
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Override
    public String toString() {
        return "customerTM{" +
                "custid='" + custid + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalcode='" + postalcode + '\'' +
                '}';
    }
}
