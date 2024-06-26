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
public class DishIngredients implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID productId;
    private UUID dishId;
    private Integer amount;

    public DishIngredients() {}

    public DishIngredients(DishIngredients value) {
        this.productId = value.productId;
        this.dishId = value.dishId;
        this.amount = value.amount;
    }

    public DishIngredients(
        UUID productId,
        UUID dishId,
        Integer amount
    ) {
        this.productId = productId;
        this.dishId = dishId;
        this.amount = amount;
    }

    /**
     * Getter for <code>s283945.dish_ingredients.product_id</code>.
     */
    @NotNull
    public UUID getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>s283945.dish_ingredients.product_id</code>.
     */
    public DishIngredients setProductId(UUID productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Getter for <code>s283945.dish_ingredients.dish_id</code>.
     */
    @NotNull
    public UUID getDishId() {
        return this.dishId;
    }

    /**
     * Setter for <code>s283945.dish_ingredients.dish_id</code>.
     */
    public DishIngredients setDishId(UUID dishId) {
        this.dishId = dishId;
        return this;
    }

    /**
     * Getter for <code>s283945.dish_ingredients.amount</code>.
     */
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>s283945.dish_ingredients.amount</code>.
     */
    public DishIngredients setAmount(Integer amount) {
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
        final DishIngredients other = (DishIngredients) obj;
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
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DishIngredients (");

        sb.append(productId);
        sb.append(", ").append(dishId);
        sb.append(", ").append(amount);

        sb.append(")");
        return sb.toString();
    }
}
