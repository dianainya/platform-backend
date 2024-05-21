/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DishReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID productId;
    private UUID dishId;
    private Integer weight;
    private Integer amount;

    public DishReceipt() {}

    public DishReceipt(DishReceipt value) {
        this.productId = value.productId;
        this.dishId = value.dishId;
        this.weight = value.weight;
        this.amount = value.amount;
    }

    public DishReceipt(
        UUID productId,
        UUID dishId,
        Integer weight,
        Integer amount
    ) {
        this.productId = productId;
        this.dishId = dishId;
        this.weight = weight;
        this.amount = amount;
    }

    /**
     * Getter for <code>public.dish_receipt.product_id</code>.
     */
    @NotNull
    public UUID getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>public.dish_receipt.product_id</code>.
     */
    public DishReceipt setProductId(UUID productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Getter for <code>public.dish_receipt.dish_id</code>.
     */
    @NotNull
    public UUID getDishId() {
        return this.dishId;
    }

    /**
     * Setter for <code>public.dish_receipt.dish_id</code>.
     */
    public DishReceipt setDishId(UUID dishId) {
        this.dishId = dishId;
        return this;
    }

    /**
     * Getter for <code>public.dish_receipt.weight</code>.
     */
    @NotNull
    public Integer getWeight() {
        return this.weight;
    }

    /**
     * Setter for <code>public.dish_receipt.weight</code>.
     */
    public DishReceipt setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * Getter for <code>public.dish_receipt.amount</code>.
     */
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>public.dish_receipt.amount</code>.
     */
    public DishReceipt setAmount(Integer amount) {
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
        final DishReceipt other = (DishReceipt) obj;
        if (this.productId == null) {
            if (other.productId != null)
                return false;
        }
        else if (!this.productId.equals(other.productId))
            return false;
        if (this.dishId == null) {
            if (other.dishId != null)
                return false;
        }
        else if (!this.dishId.equals(other.dishId))
            return false;
        if (this.weight == null) {
            if (other.weight != null)
                return false;
        }
        else if (!this.weight.equals(other.weight))
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
        result = prime * result + ((this.productId == null) ? 0 : this.productId.hashCode());
        result = prime * result + ((this.dishId == null) ? 0 : this.dishId.hashCode());
        result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DishReceipt (");

        sb.append(productId);
        sb.append(", ").append(dishId);
        sb.append(", ").append(weight);
        sb.append(", ").append(amount);

        sb.append(")");
        return sb.toString();
    }
}
