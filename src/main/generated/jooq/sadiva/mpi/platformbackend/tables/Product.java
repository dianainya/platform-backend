/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import jooq.sadiva.mpi.platformbackend.Keys;
import jooq.sadiva.mpi.platformbackend.Public;
import jooq.sadiva.mpi.platformbackend.tables.records.ProductRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Product extends TableImpl<ProductRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.product</code>
     */
    public static final Product PRODUCT = new Product();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductRecord> getRecordType() {
        return ProductRecord.class;
    }

    /**
     * The column <code>public.product.id</code>.
     */
    public final TableField<ProductRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field(DSL.raw("gen_random_uuid()"), SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.product.name</code>.
     */
    public final TableField<ProductRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.product.calories</code>.
     */
    public final TableField<ProductRecord, Integer> CALORIES = createField(DSL.name("calories"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.product.proteins</code>.
     */
    public final TableField<ProductRecord, Integer> PROTEINS = createField(DSL.name("proteins"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.product.fats</code>.
     */
    public final TableField<ProductRecord, Integer> FATS = createField(DSL.name("fats"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.product.carbohydrates</code>.
     */
    public final TableField<ProductRecord, Integer> CARBOHYDRATES = createField(DSL.name("carbohydrates"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.product.available_weight</code>.
     */
    public final TableField<ProductRecord, Double> AVAILABLE_WEIGHT = createField(DSL.name("available_weight"), SQLDataType.DOUBLE.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.DOUBLE)), this, "");

    private Product(Name alias, Table<ProductRecord> aliased) {
        this(alias, aliased, null);
    }

    private Product(Name alias, Table<ProductRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.product</code> table reference
     */
    public Product(String alias) {
        this(DSL.name(alias), PRODUCT);
    }

    /**
     * Create an aliased <code>public.product</code> table reference
     */
    public Product(Name alias) {
        this(alias, PRODUCT);
    }

    /**
     * Create a <code>public.product</code> table reference
     */
    public Product() {
        this(DSL.name("product"), null);
    }

    public <O extends Record> Product(Table<O> child, ForeignKey<O, ProductRecord> key) {
        super(child, key, PRODUCT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ProductRecord> getPrimaryKey() {
        return Keys.PRODUCT_PKEY;
    }

    @Override
    public List<UniqueKey<ProductRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.PRODUCT_NAME_KEY);
    }

    @Override
    public Product as(String alias) {
        return new Product(DSL.name(alias), this);
    }

    @Override
    public Product as(Name alias) {
        return new Product(alias, this);
    }

    @Override
    public Product as(Table<?> alias) {
        return new Product(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Product rename(String name) {
        return new Product(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Product rename(Name name) {
        return new Product(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Product rename(Table<?> name) {
        return new Product(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, String, Integer, Integer, Integer, Integer, Double> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super UUID, ? super String, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Double, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super UUID, ? super String, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Double, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
