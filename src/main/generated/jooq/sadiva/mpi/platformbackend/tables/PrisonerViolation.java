/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables;


import java.math.BigDecimal;
import java.util.function.Function;

import jooq.sadiva.mpi.platformbackend.Keys;
import jooq.sadiva.mpi.platformbackend.S283945;
import jooq.sadiva.mpi.platformbackend.tables.records.PrisonerViolationRecord;

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
public class PrisonerViolation extends TableImpl<PrisonerViolationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s283945.prisoner_violation</code>
     */
    public static final PrisonerViolation PRISONER_VIOLATION = new PrisonerViolation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PrisonerViolationRecord> getRecordType() {
        return PrisonerViolationRecord.class;
    }

    /**
     * The column <code>s283945.prisoner_violation.code</code>.
     */
    public final TableField<PrisonerViolationRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>s283945.prisoner_violation.name</code>.
     */
    public final TableField<PrisonerViolationRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>s283945.prisoner_violation.score</code>.
     */
    public final TableField<PrisonerViolationRecord, BigDecimal> SCORE = createField(DSL.name("score"), SQLDataType.NUMERIC.nullable(false).defaultValue(DSL.field(DSL.raw("5"), SQLDataType.NUMERIC)), this, "");

    private PrisonerViolation(Name alias, Table<PrisonerViolationRecord> aliased) {
        this(alias, aliased, null);
    }

    private PrisonerViolation(Name alias, Table<PrisonerViolationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s283945.prisoner_violation</code> table reference
     */
    public PrisonerViolation(String alias) {
        this(DSL.name(alias), PRISONER_VIOLATION);
    }

    /**
     * Create an aliased <code>s283945.prisoner_violation</code> table reference
     */
    public PrisonerViolation(Name alias) {
        this(alias, PRISONER_VIOLATION);
    }

    /**
     * Create a <code>s283945.prisoner_violation</code> table reference
     */
    public PrisonerViolation() {
        this(DSL.name("prisoner_violation"), null);
    }

    public <O extends Record> PrisonerViolation(Table<O> child, ForeignKey<O, PrisonerViolationRecord> key) {
        super(child, key, PRISONER_VIOLATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : S283945.S283945;
    }

    @Override
    public UniqueKey<PrisonerViolationRecord> getPrimaryKey() {
        return Keys.PRISONER_VIOLATION_PKEY;
    }

    @Override
    public PrisonerViolation as(String alias) {
        return new PrisonerViolation(DSL.name(alias), this);
    }

    @Override
    public PrisonerViolation as(Name alias) {
        return new PrisonerViolation(alias, this);
    }

    @Override
    public PrisonerViolation as(Table<?> alias) {
        return new PrisonerViolation(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PrisonerViolation rename(String name) {
        return new PrisonerViolation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PrisonerViolation rename(Name name) {
        return new PrisonerViolation(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PrisonerViolation rename(Table<?> name) {
        return new PrisonerViolation(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, BigDecimal> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super BigDecimal, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super BigDecimal, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
