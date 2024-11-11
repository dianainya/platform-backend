/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.records;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

import jooq.sadiva.mpi.platformbackend.tables.PlatformHistory;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlatformHistoryRecord extends UpdatableRecordImpl<PlatformHistoryRecord> implements Record6<UUID, Integer, UUID, UUID, LocalDate, LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s283945.platform_history.platform_id</code>.
     */
    public PlatformHistoryRecord setPlatformId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.platform_id</code>.
     */
    public UUID getPlatformId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>s283945.platform_history.floor</code>.
     */
    public PlatformHistoryRecord setFloor(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.floor</code>.
     */
    @NotNull
    public Integer getFloor() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>s283945.platform_history.first_prisoner</code>.
     */
    public PlatformHistoryRecord setFirstPrisoner(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.first_prisoner</code>.
     */
    @NotNull
    public UUID getFirstPrisoner() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>s283945.platform_history.second_prisoner</code>.
     */
    public PlatformHistoryRecord setSecondPrisoner(UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.second_prisoner</code>.
     */
    public UUID getSecondPrisoner() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>s283945.platform_history.startdate</code>.
     */
    public PlatformHistoryRecord setStartdate(LocalDate value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.startdate</code>.
     */
    public LocalDate getStartdate() {
        return (LocalDate) get(4);
    }

    /**
     * Setter for <code>s283945.platform_history.enddate</code>.
     */
    public PlatformHistoryRecord setEnddate(LocalDate value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>s283945.platform_history.enddate</code>.
     */
    public LocalDate getEnddate() {
        return (LocalDate) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, Integer, UUID, UUID, LocalDate, LocalDate> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<UUID, Integer, UUID, UUID, LocalDate, LocalDate> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return PlatformHistory.PLATFORM_HISTORY.PLATFORM_ID;
    }

    @Override
    public Field<Integer> field2() {
        return PlatformHistory.PLATFORM_HISTORY.FLOOR;
    }

    @Override
    public Field<UUID> field3() {
        return PlatformHistory.PLATFORM_HISTORY.FIRST_PRISONER;
    }

    @Override
    public Field<UUID> field4() {
        return PlatformHistory.PLATFORM_HISTORY.SECOND_PRISONER;
    }

    @Override
    public Field<LocalDate> field5() {
        return PlatformHistory.PLATFORM_HISTORY.STARTDATE;
    }

    @Override
    public Field<LocalDate> field6() {
        return PlatformHistory.PLATFORM_HISTORY.ENDDATE;
    }

    @Override
    public UUID component1() {
        return getPlatformId();
    }

    @Override
    public Integer component2() {
        return getFloor();
    }

    @Override
    public UUID component3() {
        return getFirstPrisoner();
    }

    @Override
    public UUID component4() {
        return getSecondPrisoner();
    }

    @Override
    public LocalDate component5() {
        return getStartdate();
    }

    @Override
    public LocalDate component6() {
        return getEnddate();
    }

    @Override
    public UUID value1() {
        return getPlatformId();
    }

    @Override
    public Integer value2() {
        return getFloor();
    }

    @Override
    public UUID value3() {
        return getFirstPrisoner();
    }

    @Override
    public UUID value4() {
        return getSecondPrisoner();
    }

    @Override
    public LocalDate value5() {
        return getStartdate();
    }

    @Override
    public LocalDate value6() {
        return getEnddate();
    }

    @Override
    public PlatformHistoryRecord value1(UUID value) {
        setPlatformId(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord value2(Integer value) {
        setFloor(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord value3(UUID value) {
        setFirstPrisoner(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord value4(UUID value) {
        setSecondPrisoner(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord value5(LocalDate value) {
        setStartdate(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord value6(LocalDate value) {
        setEnddate(value);
        return this;
    }

    @Override
    public PlatformHistoryRecord values(UUID value1, Integer value2, UUID value3, UUID value4, LocalDate value5, LocalDate value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlatformHistoryRecord
     */
    public PlatformHistoryRecord() {
        super(PlatformHistory.PLATFORM_HISTORY);
    }

    /**
     * Create a detached, initialised PlatformHistoryRecord
     */
    public PlatformHistoryRecord(UUID platformId, Integer floor, UUID firstPrisoner, UUID secondPrisoner, LocalDate startdate, LocalDate enddate) {
        super(PlatformHistory.PLATFORM_HISTORY);

        setPlatformId(platformId);
        setFloor(floor);
        setFirstPrisoner(firstPrisoner);
        setSecondPrisoner(secondPrisoner);
        setStartdate(startdate);
        setEnddate(enddate);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PlatformHistoryRecord
     */
    public PlatformHistoryRecord(jooq.sadiva.mpi.platformbackend.tables.pojos.PlatformHistory value) {
        super(PlatformHistory.PLATFORM_HISTORY);

        if (value != null) {
            setPlatformId(value.getPlatformId());
            setFloor(value.getFloor());
            setFirstPrisoner(value.getFirstPrisoner());
            setSecondPrisoner(value.getSecondPrisoner());
            setStartdate(value.getStartdate());
            setEnddate(value.getEnddate());
            resetChangedOnNotNull();
        }
    }
}