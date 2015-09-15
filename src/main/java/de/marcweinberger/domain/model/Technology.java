package de.marcweinberger.domain.model;

/**
 * Technology value object.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 31.08.15
 */
public class Technology {

  private final String name;
  private int weight;

  public Technology(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Technology that = (Technology) o;

    if (weight != that.weight) return false;
    return name.equals(that.name);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + weight;
    return result;
  }

  @Override
  public String toString() {
    return "Technology{" +
      "name='" + name + '\'' +
      ", weight=" + weight +
      '}';
  }
}
