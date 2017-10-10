package de.marcweinberger.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
  private String client;
  private String sector;
  private String description;
  private Integer hourlyRate;
  private List<String> technologies = new ArrayList<>();

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

  public Project(String title) {
    this.title = title;
  }


  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
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

  public List<String> getTechnologies() {
    return technologies;
  }

  public void setTechnologies(List<String> technologies) {
    this.technologies = technologies;
  }

  public Integer getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(Integer hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String title;

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Project build() {
      return new Project(title);
    }
  }
}
