/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Platform implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String code;
    private String name;
    private String description;
    private Integer floorAmount;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Platform() {}

    public Platform(Platform value) {
        this.id = value.id;
        this.code = value.code;
        this.name = value.name;
        this.description = value.description;
        this.floorAmount = value.floorAmount;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Platform(
        UUID id,
        String code,
        String name,
        String description,
        Integer floorAmount,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.floorAmount = floorAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>s283945.platform.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>s283945.platform.id</code>.
     */
    public Platform setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.code</code>.
     */
    @NotNull
    @Size(max = 20)
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>s283945.platform.code</code>.
     */
    public Platform setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.name</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>s283945.platform.name</code>.
     */
    public Platform setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>s283945.platform.description</code>.
     */
    public Platform setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.floor_amount</code>.
     */
    @NotNull
    public Integer getFloorAmount() {
        return this.floorAmount;
    }

    /**
     * Setter for <code>s283945.platform.floor_amount</code>.
     */
    public Platform setFloorAmount(Integer floorAmount) {
        this.floorAmount = floorAmount;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>s283945.platform.created_at</code>.
     */
    public Platform setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>s283945.platform.updated_at</code>.
     */
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>s283945.platform.updated_at</code>.
     */
    public Platform setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
        final Platform other = (Platform) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.code == null) {
            if (other.code != null)
                return false;
        }
        else if (!this.code.equals(other.code))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.floorAmount == null) {
            if (other.floorAmount != null)
                return false;
        }
        else if (!this.floorAmount.equals(other.floorAmount))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.floorAmount == null) ? 0 : this.floorAmount.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Platform (");

        sb.append(id);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(floorAmount);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}