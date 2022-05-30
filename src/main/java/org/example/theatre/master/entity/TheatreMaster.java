package org.example.theatre.master.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "theater_master", schema = "movie_booking", catalog = "")
public class TheatreMaster {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "theatre_id", nullable = false)
  private Integer theatreId;

  @Basic
  @Column(name = "theatre_unique_id", nullable = true, length = 255)
  private String theatreUniqueId;

  @Basic
  @Column(name = "theatre_name", nullable = false, length = 255)
  private String theatreName;

  public Integer getTheatreId() {
    return theatreId;
  }

  public void setTheatreId(Integer theatreId) {
    this.theatreId = theatreId;
  }

  public String getTheatreUniqueId() {
    return theatreUniqueId;
  }

  public void setTheatreUniqueId(String theatreUniqueId) {
    this.theatreUniqueId = theatreUniqueId;
  }

  public String getTheatreName() {
    return theatreName;
  }

  public void setTheatreName(String theatreName) {
    this.theatreName = theatreName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TheatreMaster that = (TheatreMaster) o;
    return Objects.equals(theatreId, that.theatreId)
        && Objects.equals(theatreUniqueId, that.theatreUniqueId)
        && Objects.equals(theatreName, that.theatreName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(theatreId, theatreUniqueId, theatreName);
  }
}
