public class ServiceTable {
    private Integer oldId;
    private Integer orderId;
    private String oldName;
    private String cusName;
    private String cusAddress;
    private String goodsName;
    private String serviceTimeBef;
    private String serviceTimeArf;
    private String workerName;
    private String merchantsName;

    public ServiceTable(Integer oldId,Integer orderId, String oldName, String cusName, String cusAddress, String goodsName, String serviceTimeBef, String serviceTimeArf, String workerName, String merchantsName) {
        this.oldId = oldId;
        this.orderId = orderId;
        this.oldName = oldName;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.goodsName = goodsName;
        this.serviceTimeBef = serviceTimeBef;
        this.serviceTimeArf = serviceTimeArf;
        this.workerName = workerName;
        this.merchantsName = merchantsName;
    }

    @Override
    public String toString() {
        return orderId+","+cusName+","+cusAddress+","+goodsName+","+serviceTimeBef+","+serviceTimeArf+","+workerName+","+merchantsName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getServiceTimeBef() {
        return serviceTimeBef;
    }

    public void setServiceTimeBef(String serviceTimeBef) {
        this.serviceTimeBef = serviceTimeBef;
    }

    public String getServiceTimeArf() {
        return serviceTimeArf;
    }

    public void setServiceTimeArf(String serviceTimeArf) {
        this.serviceTimeArf = serviceTimeArf;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getMerchantsName() {
        return merchantsName;
    }

    public void setMerchantsName(String merchantsName) {
        this.merchantsName = merchantsName;
    }
}
