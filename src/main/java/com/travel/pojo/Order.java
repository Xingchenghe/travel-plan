package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "tb_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;
  private Long itemType;
  private Long userId;
  private java.sql.Timestamp createTime;
  private Long itemId;
  private String itemOwnSpec;

  public String getItemOwnSpec() {
    return itemOwnSpec;
  }

  public void setItemOwnSpec(String itemOwnSpec) {
    this.itemOwnSpec = itemOwnSpec;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }


  public Long getItemType() {
    return itemType;
  }

  public void setItemType(Long itemType) {
    this.itemType = itemType;
  }


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

}
