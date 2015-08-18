package de.marcweinberger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * Project entity.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 10.07.15
 */
@Document
public class Project {

  private String id;
  private String title;
  private String role;
  private String customer;
  private String sector;
  private String description;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
  private YearMonth start;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
  private YearMonth end;

  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public Project() {
  }


  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public YearMonth getEnd() {
    return end;
  }

  public void setEnd(YearMonth end) {
    this.end = end;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public YearMonth getStart() {
    return start;
  }

  public void setStart(YearMonth start) {
    this.start = start;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
