public class oldObject {
    private String cusname;
    private String homeAddress;
    private String hometownAreaCode;
    private String sex;
    private String idNumber;
    private String birthDate;
    private String residenceAddress;
    private String userbody;
    private String phone;
    private String cid;

    public oldObject() {
    }

    public oldObject(String cusname, String homeAddress, String hometownAreaCode, String sex, String idNumber, String birthDate, String residenceAddress, String userbody, String phone, String cid) {
        this.cusname = cusname;
        this.homeAddress = homeAddress;
        this.hometownAreaCode = hometownAreaCode;
        this.sex = sex;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
        this.residenceAddress = residenceAddress;
        this.userbody = userbody;
        this.phone = phone;
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public String getHometownAreaCode() {
        return hometownAreaCode;
    }

    public void setHometownAreaCode(String hometownAreaCode) {
        this.hometownAreaCode = hometownAreaCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getUserbody() {
        return userbody;
    }

    public void setUserbody(String userbody) {
        this.userbody = userbody;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
