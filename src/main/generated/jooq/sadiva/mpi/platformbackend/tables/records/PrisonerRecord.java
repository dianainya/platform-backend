/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

import jooq.sadiva.mpi.platformbackend.tables.Prisoner;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PrisonerRecord extends UpdatableRecordImpl<PrisonerRecord> implements Record8<UUID, String, String, String, Double, LocalDate, String, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s283945.prisoner.id</code>.
     */
    public PrisonerRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>s283945.prisoner.first_name</code>.
     */
    public PrisonerRecord setFirstName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.first_name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>s283945.prisoner.patronymic</code>.
     */
    public PrisonerRecord setPatronymic(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.patronymic</code>.
     */
    @Size(max = 255)
    public String getPatronymic() {
        return (String) get(2);
    }

    /**
     * Setter for <code>s283945.prisoner.last_name</code>.
     */
    public PrisonerRecord setLastName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.last_name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s283945.prisoner.weight</code>.
     */
    public PrisonerRecord setWeight(Double value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.weight</code>.
     */
    @NotNull
    public Double getWeight() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>s283945.prisoner.birth_date</code>.
     */
    public PrisonerRecord setBirthDate(LocalDate value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.birth_date</code>.
     */
    @NotNull
    public LocalDate getBirthDate() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>s283945.prisoner.passport</code>.
     */
    public PrisonerRecord setPassport(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.passport</code>.
     */
    @NotNull
    public String getPassport() {
        return (String) get(6);
    }

    /**
     * Setter for <code>s283945.prisoner.favorite_dish</code>.
     */
    public PrisonerRecord setFavoriteDish(UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.favorite_dish</code>.
     */
    public UUID getFavoriteDish() {
        return (UUID) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, String, String, String, Double, LocalDate, String, UUID> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<UUID, String, String, String, Double, LocalDate, String, UUID> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Prisoner.PRISONER.ID;
    }

    @Override
    public Field<String> field2() {
        return Prisoner.PRISONER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Prisoner.PRISONER.PATRONYMIC;
    }

    @Override
    public Field<String> field4() {
        return Prisoner.PRISONER.LAST_NAME;
    }

    @Override
    public Field<Double> field5() {
        return Prisoner.PRISONER.WEIGHT;
    }

    @Override
    public Field<LocalDate> field6() {
        return Prisoner.PRISONER.BIRTH_DATE;
    }

    @Override
    public Field<String> field7() {
        return Prisoner.PRISONER.PASSPORT;
    }

    @Override
    public Field<UUID> field8() {
        return Prisoner.PRISONER.FAVORITE_DISH;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getPatronymic();
    }

    @Override
    public String component4() {
        return getLastName();
    }

    @Override
    public Double component5() {
        return getWeight();
    }

    @Override
    public LocalDate component6() {
        return getBirthDate();
    }

    @Override
    public String component7() {
        return getPassport();
    }

    @Override
    public UUID component8() {
        return getFavoriteDish();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getPatronymic();
    }

    @Override
    public String value4() {
        return getLastName();
    }

    @Override
    public Double value5() {
        return getWeight();
    }

    @Override
    public LocalDate value6() {
        return getBirthDate();
    }

    @Override
    public String value7() {
        return getPassport();
    }

    @Override
    public UUID value8() {
        return getFavoriteDish();
    }

    @Override
    public PrisonerRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public PrisonerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public PrisonerRecord value3(String value) {
        setPatronymic(value);
        return this;
    }

    @Override
    public PrisonerRecord value4(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public PrisonerRecord value5(Double value) {
        setWeight(value);
        return this;
    }

    @Override
    public PrisonerRecord value6(LocalDate value) {
        setBirthDate(value);
        return this;
    }

    @Override
    public PrisonerRecord value7(String value) {
        setPassport(value);
        return this;
    }

    @Override
    public PrisonerRecord value8(UUID value) {
        setFavoriteDish(value);
        return this;
    }

    @Override
    public PrisonerRecord values(UUID value1, String value2, String value3, String value4, Double value5, LocalDate value6, String value7, UUID value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PrisonerRecord
     */
    public PrisonerRecord() {
        super(Prisoner.PRISONER);
    }

    /**
     * Create a detached, initialised PrisonerRecord
     */
    public PrisonerRecord(UUID id, String firstName, String patronymic, String lastName, Double weight, LocalDate birthDate, String passport, UUID favoriteDish) {
        super(Prisoner.PRISONER);

        setId(id);
        setFirstName(firstName);
        setPatronymic(patronymic);
        setLastName(lastName);
        setWeight(weight);
        setBirthDate(birthDate);
        setPassport(passport);
        setFavoriteDish(favoriteDish);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PrisonerRecord
     */
    public PrisonerRecord(jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner value) {
        super(Prisoner.PRISONER);

        if (value != null) {
            setId(value.getId());
            setFirstName(value.getFirstName());
            setPatronymic(value.getPatronymic());
            setLastName(value.getLastName());
            setWeight(value.getWeight());
            setBirthDate(value.getBirthDate());
            setPassport(value.getPassport());
            setFavoriteDish(value.getFavoriteDish());
            resetChangedOnNotNull();
        }
    }
}