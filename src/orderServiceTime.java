public class orderServiceTime {
    private Integer orderId;
    private String orderTime;
    private String serviceTimeBefore;
    private String serviceTimeAfter;

    public orderServiceTime(Integer orderId, String orderTime, String serviceTimeBefore, String serviceTimeAfter) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.serviceTimeBefore = serviceTimeBefore;
        this.serviceTimeAfter = serviceTimeAfter;
    }

    public orderServiceTime() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getServiceTimeBefore() {
        return serviceTimeBefore;
    }

    public void setServiceTimeBefore(String serviceTimeBefore) {
        this.serviceTimeBefore = serviceTimeBefore;
    }

    public String getServiceTimeAfter() {
        return serviceTimeAfter;
    }

    public void setServiceTimeAfter(String serviceTimeAfter) {
        this.serviceTimeAfter = serviceTimeAfter;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
