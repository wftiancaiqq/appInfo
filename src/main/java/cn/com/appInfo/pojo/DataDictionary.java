package cn.com.appInfo.pojo;


import java.util.Date;

public class DataDictionary {

  private Long id;
  private String typeCode;
  private String typeName;
  private Long valueId;
  private String valueName;
  private Long createdBy;
  private Date creationDate;
  private Long modifyBy;
  private Date modifyDate;

  public DataDictionary() {
  }

  public DataDictionary(Long id, String typeCode, String typeName, Long valueId, String valueName, Long createdBy, Date creationDate, Long modifyBy, Date modifyDate) {
    this.id = id;
    this.typeCode = typeCode;
    this.typeName = typeName;
    this.valueId = valueId;
    this.valueName = valueName;
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

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getValueId() {
    return valueId;
  }

  public void setValueId(Long valueId) {
    this.valueId = valueId;
  }

  public String getValueName() {
    return valueName;
  }

  public void setValueName(String valueName) {
    this.valueName = valueName;
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
    return "DataDictionary{" +
            "id=" + id +
            ", typeCode='" + typeCode + '\'' +
            ", typeName='" + typeName + '\'' +
            ", valueId=" + valueId +
            ", valueName='" + valueName + '\'' +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            '}';
  }
}
