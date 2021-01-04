public class old {
    private String cusname;
    private String homeAddress;
    private String phone;
    private Integer clientType;
    private Integer orderId;
    private Integer userId;

    public old(String cusname, String homeAddress,String phone,Integer clientType,Integer orderId,Integer userId) {
        this.cusname = cusname;
        this.homeAddress = homeAddress;
        this.phone = phone;
        this.clientType = clientType;
        this.orderId = orderId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public old() {
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
