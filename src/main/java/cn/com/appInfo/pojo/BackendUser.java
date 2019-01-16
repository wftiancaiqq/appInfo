package cn.com.appInfo.pojo;

/**
 * created by Jason on 2019/1/12
 * 后台管理员pojo类
 */
import java.util.Date;

public class BackendUser {

  private Long id;
  private String userCode;
  private String userName;
  private Long userType;
  private Long createdBy;
  private Date creationDate;
  private Long modifyBy;
  private Date modifyDate;
  private String userPassword;

  public BackendUser() {
  }

  public BackendUser(Long id, String userCode, String userName, Long userType, Long createdBy, Date creationDate, Long modifyBy, Date modifyDate, String userPassword) {
    this.id = id;
    this.userCode = userCode;
    this.userName = userName;
    this.userType = userType;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
    this.userPassword = userPassword;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getUserType() {
    return userType;
  }

  public void setUserType(Long userType) {
    this.userType = userType;
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

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  @Override
  public String toString() {
    return "BackendUser{" +
            "id=" + id +
            ", userCode='" + userCode + '\'' +
            ", userName='" + userName + '\'' +
            ", userType=" + userType +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            ", userPassword='" + userPassword + '\'' +
            '}';
  }
}
