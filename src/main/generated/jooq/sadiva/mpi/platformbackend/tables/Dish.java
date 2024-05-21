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
import jooq.sadiva.mpi.platformbackend.tables.records.DishRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class Dish extends TableImpl<DishRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.dish</code>
     */
    public static final Dish DISH = new Dish();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DishRecord> getRecordType() {
        return DishRecord.class;
    }

    /**
     * The column <code>public.dish.id</code>.
     */
    public final TableField<DishRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field(DSL.raw("gen_random_uuid()"), SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.dish.name</code>.
     */
    public final TableField<DishRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.dish.description</code>.
     */
    public final TableField<DishRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    private Dish(Name alias, Table<DishRecord> aliased) {
        this(alias, aliased, null);
    }

    private Dish(Name alias, Table<DishRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.dish</code> table reference
     */
    public Dish(String alias) {
        this(DSL.name(alias), DISH);
    }

    /**
     * Create an aliased <code>public.dish</code> table reference
     */
    public Dish(Name alias) {
        this(alias, DISH);
    }

    /**
     * Create a <code>public.dish</code> table reference
     */
    public Dish() {
        this(DSL.name("dish"), null);
    }

    public <O extends Record> Dish(Table<O> child, ForeignKey<O, DishRecord> key) {
        super(child, key, DISH);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DishRecord> getPrimaryKey() {
        return Keys.DISH_PKEY;
    }

    @Override
    public List<UniqueKey<DishRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.DISH_NAME_KEY);
    }

    @Override
    public Dish as(String alias) {
        return new Dish(DSL.name(alias), this);
    }

    @Override
    public Dish as(Name alias) {
        return new Dish(alias, this);
    }

    @Override
    public Dish as(Table<?> alias) {
        return new Dish(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Dish rename(String name) {
        return new Dish(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Dish rename(Name name) {
        return new Dish(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Dish rename(Table<?> name) {
        return new Dish(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super UUID, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super UUID, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
