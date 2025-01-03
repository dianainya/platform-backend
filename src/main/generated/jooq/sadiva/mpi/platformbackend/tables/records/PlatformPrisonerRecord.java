/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.records;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import jooq.sadiva.mpi.platformbackend.tables.PlatformPrisoner;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlatformPrisonerRecord extends UpdatableRecordImpl<PlatformPrisonerRecord> implements Record3<Integer, UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s283945.platform_prisoner.floor</code>.
     */
    public PlatformPrisonerRecord setFloor(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_prisoner.floor</code>.
     */
    @NotNull
    public Integer getFloor() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>s283945.platform_prisoner.first_prisoner</code>.
     */
    public PlatformPrisonerRecord setFirstPrisoner(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_prisoner.first_prisoner</code>.
     */
    @NotNull
    public UUID getFirstPrisoner() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>s283945.platform_prisoner.second_prisoner</code>.
     */
    public PlatformPrisonerRecord setSecondPrisoner(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_prisoner.second_prisoner</code>.
     */
    public UUID getSecondPrisoner() {
        return (UUID) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, UUID, UUID> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PlatformPrisoner.PLATFORM_PRISONER.FLOOR;
    }

    @Override
    public Field<UUID> field2() {
        return PlatformPrisoner.PLATFORM_PRISONER.FIRST_PRISONER;
    }

    @Override
    public Field<UUID> field3() {
        return PlatformPrisoner.PLATFORM_PRISONER.SECOND_PRISONER;
    }

    @Override
    public Integer component1() {
        return getFloor();
    }

    @Override
    public UUID component2() {
        return getFirstPrisoner();
    }

    @Override
    public UUID component3() {
        return getSecondPrisoner();
    }

    @Override
    public Integer value1() {
        return getFloor();
    }

    @Override
    public UUID value2() {
        return getFirstPrisoner();
    }

    @Override
    public UUID value3() {
        return getSecondPrisoner();
    }

    @Override
    public PlatformPrisonerRecord value1(Integer value) {
        setFloor(value);
        return this;
    }

    @Override
    public PlatformPrisonerRecord value2(UUID value) {
        setFirstPrisoner(value);
        return this;
    }

    @Override
    public PlatformPrisonerRecord value3(UUID value) {
        setSecondPrisoner(value);
        return this;
    }

    @Override
    public PlatformPrisonerRecord values(Integer value1, UUID value2, UUID value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlatformPrisonerRecord
     */
    public PlatformPrisonerRecord() {
        super(PlatformPrisoner.PLATFORM_PRISONER);
    }

    /**
     * Create a detached, initialised PlatformPrisonerRecord
     */
    public PlatformPrisonerRecord(Integer floor, UUID firstPrisoner, UUID secondPrisoner) {
        super(PlatformPrisoner.PLATFORM_PRISONER);

        setFloor(floor);
        setFirstPrisoner(firstPrisoner);
        setSecondPrisoner(secondPrisoner);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PlatformPrisonerRecord
     */
    public PlatformPrisonerRecord(jooq.sadiva.mpi.platformbackend.tables.pojos.PlatformPrisoner value) {
        super(PlatformPrisoner.PLATFORM_PRISONER);

        if (value != null) {
            setFloor(value.getFloor());
            setFirstPrisoner(value.getFirstPrisoner());
            setSecondPrisoner(value.getSecondPrisoner());
            resetChangedOnNotNull();
        }
    }
}
