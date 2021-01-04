public class OldInfo {
    private Integer clientType;
    private String homeAreaCode;
    private Integer oldStatus;

    public OldInfo(Integer clientType, String homeAreaCode, Integer oldStatus) {
        this.clientType = clientType;
        this.homeAreaCode = homeAreaCode;
        this.oldStatus = oldStatus;
    }

    public OldInfo() {
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getHomeAreaCode() {
        return homeAreaCode;
    }

    public void setHomeAreaCode(String homeAreaCode) {
        this.homeAreaCode = homeAreaCode;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }
}
