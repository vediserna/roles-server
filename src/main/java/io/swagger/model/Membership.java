package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

public class Membership   {
  @JsonProperty("memberId")
  private Integer memberId = null;

  @JsonProperty("teamId")
  private Integer teamId = null;

  @JsonProperty("role")
  private String role = null;

  public Membership memberId(Integer memberId) {
    this.memberId = memberId;
    return this;
  }

  @ApiModelProperty(value = "")

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  public Membership teamId(Integer teamId) {
    this.teamId = teamId;
    return this;
  }

  @ApiModelProperty(value = "")

  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

  public Membership role(String role) {
    this.role = role;
    return this;
  }

  @ApiModelProperty(value = "")

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Membership membership = (Membership) o;
    return Objects.equals(this.memberId, membership.memberId) &&
        Objects.equals(this.teamId, membership.teamId) &&
        Objects.equals(this.role, membership.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, teamId, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    
    sb.append("\"memberId\": ").append(toIndentedString(memberId)).append(",\n");
    sb.append("\"teamId\": ").append(toIndentedString(teamId)).append(",\n");
    sb.append("\"role\": \"").append(toIndentedString(role)).append("\"\n");
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

