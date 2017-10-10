package de.marcweinberger.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

  @Id
  private String id;
  private String email;

  public User(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (!id.equals(user.id)) return false;
    return email.equals(user.email);

  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + email.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      ", id=" + id +
      '}';
  }
}
