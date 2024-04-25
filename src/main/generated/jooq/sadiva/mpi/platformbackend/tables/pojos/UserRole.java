/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String role;

    public UserRole() {}

    public UserRole(UserRole value) {
        this.userId = value.userId;
        this.role = value.role;
    }

    public UserRole(
        UUID userId,
        String role
    ) {
        this.userId = userId;
        this.role = role;
    }

    /**
     * Getter for <code>public.user_role.user_id</code>.
     */
    @NotNull
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.user_role.user_id</code>.
     */
    public UserRole setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>public.user_role.role</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for <code>public.user_role.role</code>.
     */
    public UserRole setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserRole other = (UserRole) obj;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.role == null) {
            if (other.role != null)
                return false;
        }
        else if (!this.role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserRole (");

        sb.append(userId);
        sb.append(", ").append(role);

        sb.append(")");
        return sb.toString();
    }
}
