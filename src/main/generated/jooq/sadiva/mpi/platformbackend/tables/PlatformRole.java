/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables;


import java.util.function.Function;

import jooq.sadiva.mpi.platformbackend.Keys;
import jooq.sadiva.mpi.platformbackend.Public;
import jooq.sadiva.mpi.platformbackend.tables.records.PlatformRoleRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function1;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row1;
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
public class PlatformRole extends TableImpl<PlatformRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.platform_role</code>
     */
    public static final PlatformRole PLATFORM_ROLE = new PlatformRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlatformRoleRecord> getRecordType() {
        return PlatformRoleRecord.class;
    }

    /**
     * The column <code>public.platform_role.name</code>.
     */
    public final TableField<PlatformRoleRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private PlatformRole(Name alias, Table<PlatformRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private PlatformRole(Name alias, Table<PlatformRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.platform_role</code> table reference
     */
    public PlatformRole(String alias) {
        this(DSL.name(alias), PLATFORM_ROLE);
    }

    /**
     * Create an aliased <code>public.platform_role</code> table reference
     */
    public PlatformRole(Name alias) {
        this(alias, PLATFORM_ROLE);
    }

    /**
     * Create a <code>public.platform_role</code> table reference
     */
    public PlatformRole() {
        this(DSL.name("platform_role"), null);
    }

    public <O extends Record> PlatformRole(Table<O> child, ForeignKey<O, PlatformRoleRecord> key) {
        super(child, key, PLATFORM_ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<PlatformRoleRecord> getPrimaryKey() {
        return Keys.PLATFORM_ROLE_PKEY;
    }

    @Override
    public PlatformRole as(String alias) {
        return new PlatformRole(DSL.name(alias), this);
    }

    @Override
    public PlatformRole as(Name alias) {
        return new PlatformRole(alias, this);
    }

    @Override
    public PlatformRole as(Table<?> alias) {
        return new PlatformRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PlatformRole rename(String name) {
        return new PlatformRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlatformRole rename(Name name) {
        return new PlatformRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlatformRole rename(Table<?> name) {
        return new PlatformRole(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function1<? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function1<? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
