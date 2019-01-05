package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Membership
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

public class UserRole {
    @JsonProperty("userId")
    private Integer userId = null;

    @JsonProperty("role")
    private String role = null;

    public UserRole userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty(value = "")

    public Integer getuserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserRole role(String role) {
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
        UserRole userRole = (UserRole) o;
        return Objects.equals(this.userId, userRole.userId) &&
                Objects.equals(this.role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        sb.append("\"userId\": ").append(toIndentedString(userId)).append(",\n");
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

