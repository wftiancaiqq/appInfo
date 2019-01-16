package cn.com.appInfo.pojo;


import java.util.Date;

public class DevUser {

  private Long id;
  private String devCode;
  private String devName;
  private String devPassword;
  private String devEmail;
  private String devInfo;
  private Long createdBy;
  private Date creationDate;
  private Long modifyBy;
  private Date modifyDate;

  public DevUser() {
  }

  public DevUser(Long id, String devCode, String devName, String devPassword, String devEmail, String devInfo, Long createdBy, Date creationDate, Long modifyBy, Date modifyDate) {
    this.id = id;
    this.devCode = devCode;
    this.devName = devName;
    this.devPassword = devPassword;
    this.devEmail = devEmail;
    this.devInfo = devInfo;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDevCode() {
    return devCode;
  }

  public void setDevCode(String devCode) {
    this.devCode = devCode;
  }

  public String getDevName() {
    return devName;
  }

  public void setDevName(String devName) {
    this.devName = devName;
  }

  public String getDevPassword() {
    return devPassword;
  }

  public void setDevPassword(String devPassword) {
    this.devPassword = devPassword;
  }

  public String getDevEmail() {
    return devEmail;
  }

  public void setDevEmail(String devEmail) {
    this.devEmail = devEmail;
  }

  public String getDevInfo() {
    return devInfo;
  }

  public void setDevInfo(String devInfo) {
    this.devInfo = devInfo;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(Long modifyBy) {
    this.modifyBy = modifyBy;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

  @Override
  public String toString() {
    return "DevUser{" +
            "id=" + id +
            ", devCode='" + devCode + '\'' +
            ", devName='" + devName + '\'' +
            ", devPassword='" + devPassword + '\'' +
            ", devEmail='" + devEmail + '\'' +
            ", devInfo='" + devInfo + '\'' +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            '}';
  }
}
