public class emergencyContact {
    private String guarPhone; //联系人手机号
    private Integer oldId;  //老人表ID
    private String contacName; //联系人姓名
    private String relationship;//联系人与老人关系
    private Integer level; //等级
    private Integer status;//状态 默认：1

    public emergencyContact(String guarPhone, Integer oldId, String contacName, String relationship, Integer level, Integer status) {
        this.guarPhone = guarPhone;
        this.oldId = oldId;
        this.contacName = contacName;
        this.relationship = relationship;
        this.level = level;
        this.status = status;
    }

    public emergencyContact() {
    }

    public String getGuarPhone() {
        return guarPhone;
    }

    public void setGuarPhone(String guarPhone) {
        this.guarPhone = guarPhone;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getContacName() {
        return contacName;
    }

    public void setContacName(String contacName) {
        this.contacName = contacName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
