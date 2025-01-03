/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrentMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID dishId;
    private Integer amount;

    public CurrentMenu() {}

    public CurrentMenu(CurrentMenu value) {
        this.dishId = value.dishId;
        this.amount = value.amount;
    }

    public CurrentMenu(
        UUID dishId,
        Integer amount
    ) {
        this.dishId = dishId;
        this.amount = amount;
    }

    /**
     * Getter for <code>s283945.current_menu.dish_id</code>.
     */
    public UUID getDishId() {
        return this.dishId;
    }

    /**
     * Setter for <code>s283945.current_menu.dish_id</code>.
     */
    public CurrentMenu setDishId(UUID dishId) {
        this.dishId = dishId;
        return this;
    }

    /**
     * Getter for <code>s283945.current_menu.amount</code>.
     */
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>s283945.current_menu.amount</code>.
     */
    public CurrentMenu setAmount(Integer amount) {
        this.amount = amount;
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
        final CurrentMenu other = (CurrentMenu) obj;
        if (this.dishId == null) {
            if (other.dishId != null)
                return false;
        }
        else if (!this.dishId.equals(other.dishId))
            return false;
        if (this.amount == null) {
            if (other.amount != null)
                return false;
        }
        else if (!this.amount.equals(other.amount))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dishId == null) ? 0 : this.dishId.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CurrentMenu (");

        sb.append(dishId);
        sb.append(", ").append(amount);

        sb.append(")");
        return sb.toString();
    }
}
