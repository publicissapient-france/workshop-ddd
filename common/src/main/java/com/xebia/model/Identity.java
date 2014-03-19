package com.xebia.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Identity implements Serializable {

  private String id;

  protected Identity(String anId) {
    this.setId(anId);
  }

  protected Identity() {
  }

  public String id() {
    return this.id;
  }

  @Override
  public boolean equals(Object object) {
    boolean equalObjects = false;

    if (object != null && this.getClass() == object.getClass()) {
      Identity typedObject = (Identity) object;
      equalObjects = this.id().equals(typedObject.id());
    }

    return equalObjects;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " [id=" + id + "]";
  }

  protected void validateId(String id) {
    // implemented by subclasses for validation.
    // throws a runtime exception if invalid.
  }

  private void setId(String anId) {
    this.validateId(anId);

    this.id = anId;
  }
}
