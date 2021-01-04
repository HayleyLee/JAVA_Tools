public class callLog {
    private String start_stamp;
    private String caller_id_number;
    private String destination_number;
    private String liancalltype;
    private String callStatus;
    private Integer billsec;
    private String cusname;

    public callLog(String start_stamp, String caller_id_number, String destination_number, String liancalltype, String callStatus, Integer billsec, String cusname) {
        this.start_stamp = start_stamp;
        this.caller_id_number = caller_id_number;
        this.destination_number = destination_number;
        this.liancalltype = liancalltype;
        this.callStatus = callStatus;
        this.billsec = billsec;
        this.cusname = cusname;
    }

    public callLog() {
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getStart_stamp() {
        return start_stamp;
    }

    public void setStart_stamp(String start_stamp) {
        this.start_stamp = start_stamp;
    }

    public String getCaller_id_number() {
        return caller_id_number;
    }

    public void setCaller_id_number(String caller_id_number) {
        this.caller_id_number = caller_id_number;
    }

    public String getDestination_number() {
        return destination_number;
    }

    public void setDestination_number(String destination_number) {
        this.destination_number = destination_number;
    }

    public String getLiancalltype() {
        return liancalltype;
    }

    public void setLiancalltype(String liancalltype) {
        this.liancalltype = liancalltype;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public Integer getBillsec() {
        return billsec;
    }

    public void setBillsec(Integer billsec) {
        this.billsec = billsec;
    }
}
