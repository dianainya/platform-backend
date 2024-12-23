/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlatformActiveFloor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer activeFloor;

    public PlatformActiveFloor() {}

    public PlatformActiveFloor(PlatformActiveFloor value) {
        this.activeFloor = value.activeFloor;
    }

    public PlatformActiveFloor(
        Integer activeFloor
    ) {
        this.activeFloor = activeFloor;
    }

    /**
     * Getter for <code>s283945.platform_active_floor.active_floor</code>.
     */
    public Integer getActiveFloor() {
        return this.activeFloor;
    }

    /**
     * Setter for <code>s283945.platform_active_floor.active_floor</code>.
     */
    public PlatformActiveFloor setActiveFloor(Integer activeFloor) {
        this.activeFloor = activeFloor;
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
        final PlatformActiveFloor other = (PlatformActiveFloor) obj;
        if (this.activeFloor == null) {
            if (other.activeFloor != null)
                return false;
        }
        else if (!this.activeFloor.equals(other.activeFloor))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.activeFloor == null) ? 0 : this.activeFloor.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlatformActiveFloor (");

        sb.append(activeFloor);

        sb.append(")");
        return sb.toString();
    }
}
