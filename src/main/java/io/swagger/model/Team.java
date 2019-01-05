package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

public class Team   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("lead")
  private Integer lead = null;

  public Team id(Integer id) {
    this.id = id;
    return this;
  }

  @ApiModelProperty(value = "")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Team name(String name) {
    this.name = name;
    return this;
  }

  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Team lead(Integer lead) {
    this.lead = lead;
    return this;
  }

  @ApiModelProperty(value = "")

  public Integer getLead() {
    return lead;
  }

  public void setLead(Integer lead) {
    this.lead = lead;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Team team = (Team) o;
    return Objects.equals(this.id, team.id) &&
        Objects.equals(this.name, team.name) &&
        Objects.equals(this.lead, team.lead);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lead);
  }

  @Override
  public String toString() {
    StringBuilder rolesString = new StringBuilder();

    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("\"id\": ").append(toIndentedString(id)).append(",\n");
    sb.append("\"name\": \"").append(toIndentedString(name)).append("\",\n");
    sb.append("\"lead\": ").append(toIndentedString(lead)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

