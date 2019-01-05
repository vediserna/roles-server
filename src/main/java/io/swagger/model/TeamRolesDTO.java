package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Data Transfer Object for team roles
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

public class TeamRolesDTO {
    @JsonProperty("teamId")
    private Integer teamId = null;

    @JsonProperty("roles")
    private List<String> roles = null;

    public TeamRolesDTO teamId(Integer teamId) {
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

    public TeamRolesDTO roles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @ApiModelProperty(value = "")

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @ApiModelProperty(value="")

    public void addRole(String newRole) {
        if(this.roles == null) {
            this.roles = new ArrayList<String>();
        }

        this.roles.add(newRole);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamRolesDTO teamRolesDTO = (TeamRolesDTO) o;
        return Objects.equals(this.teamId, teamRolesDTO.teamId) &&
                Objects.equals(this.roles, teamRolesDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, roles);
    }

    @Override
    public String toString() {
        StringBuilder rolesString = new StringBuilder();
        rolesString.append("[");
        for (String role : roles) {
            rolesString.append("\"");
            rolesString.append(role);
            rolesString.append("\",");
        }
        rolesString.setCharAt(rolesString.length() - 1, ']');

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"teamId\": ").append(toIndentedString(teamId)).append(",\n");
        sb.append("\"roles\": ").append(toIndentedString(rolesString)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public boolean isRoleInTeam(String role) {
        if (this.roles.contains(role)) {
            return true;
        } else {
            return false;
        }
    }
}

