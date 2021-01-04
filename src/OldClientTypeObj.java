public class OldClientTypeObj {
    private Integer oldId;
    private Integer customerTypeId;
    private String createTime;
    private Integer status;

    public OldClientTypeObj(Integer oldId, Integer customerTypeId, String createTime, Integer status) {
        this.oldId = oldId;
        this.customerTypeId = customerTypeId;
        this.createTime = createTime;
        this.status = status;
    }
    public OldClientTypeObj() {
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
