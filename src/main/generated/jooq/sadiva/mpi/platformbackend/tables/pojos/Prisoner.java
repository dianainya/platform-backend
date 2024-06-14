/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Prisoner implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String firstName;
    private String patronymic;
    private String lastName;
    private Double weight;
    private LocalDate birthDate;
    private String passport;
    private UUID favoriteDish;

    public Prisoner() {}

    public Prisoner(Prisoner value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.patronymic = value.patronymic;
        this.lastName = value.lastName;
        this.weight = value.weight;
        this.birthDate = value.birthDate;
        this.passport = value.passport;
        this.favoriteDish = value.favoriteDish;
    }

    public Prisoner(
        UUID id,
        String firstName,
        String patronymic,
        String lastName,
        Double weight,
        LocalDate birthDate,
        String passport,
        UUID favoriteDish
    ) {
        this.id = id;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.weight = weight;
        this.birthDate = birthDate;
        this.passport = passport;
        this.favoriteDish = favoriteDish;
    }

    /**
     * Getter for <code>s283945.prisoner.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>s283945.prisoner.id</code>.
     */
    public Prisoner setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.first_name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for <code>s283945.prisoner.first_name</code>.
     */
    public Prisoner setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.patronymic</code>.
     */
    @Size(max = 255)
    public String getPatronymic() {
        return this.patronymic;
    }

    /**
     * Setter for <code>s283945.prisoner.patronymic</code>.
     */
    public Prisoner setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.last_name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for <code>s283945.prisoner.last_name</code>.
     */
    public Prisoner setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.weight</code>.
     */
    @NotNull
    public Double getWeight() {
        return this.weight;
    }

    /**
     * Setter for <code>s283945.prisoner.weight</code>.
     */
    public Prisoner setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.birth_date</code>.
     */
    @NotNull
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Setter for <code>s283945.prisoner.birth_date</code>.
     */
    public Prisoner setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.passport</code>.
     */
    @NotNull
    public String getPassport() {
        return this.passport;
    }

    /**
     * Setter for <code>s283945.prisoner.passport</code>.
     */
    public Prisoner setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    /**
     * Getter for <code>s283945.prisoner.favorite_dish</code>.
     */
    public UUID getFavoriteDish() {
        return this.favoriteDish;
    }

    /**
     * Setter for <code>s283945.prisoner.favorite_dish</code>.
     */
    public Prisoner setFavoriteDish(UUID favoriteDish) {
        this.favoriteDish = favoriteDish;
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
        final Prisoner other = (Prisoner) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.firstName == null) {
            if (other.firstName != null)
                return false;
        }
        else if (!this.firstName.equals(other.firstName))
            return false;
        if (this.patronymic == null) {
            if (other.patronymic != null)
                return false;
        }
        else if (!this.patronymic.equals(other.patronymic))
            return false;
        if (this.lastName == null) {
            if (other.lastName != null)
                return false;
        }
        else if (!this.lastName.equals(other.lastName))
            return false;
        if (this.weight == null) {
            if (other.weight != null)
                return false;
        }
        else if (!this.weight.equals(other.weight))
            return false;
        if (this.birthDate == null) {
            if (other.birthDate != null)
                return false;
        }
        else if (!this.birthDate.equals(other.birthDate))
            return false;
        if (this.passport == null) {
            if (other.passport != null)
                return false;
        }
        else if (!this.passport.equals(other.passport))
            return false;
        if (this.favoriteDish == null) {
            if (other.favoriteDish != null)
                return false;
        }
        else if (!this.favoriteDish.equals(other.favoriteDish))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.patronymic == null) ? 0 : this.patronymic.hashCode());
        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
        result = prime * result + ((this.birthDate == null) ? 0 : this.birthDate.hashCode());
        result = prime * result + ((this.passport == null) ? 0 : this.passport.hashCode());
        result = prime * result + ((this.favoriteDish == null) ? 0 : this.favoriteDish.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Prisoner (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(patronymic);
        sb.append(", ").append(lastName);
        sb.append(", ").append(weight);
        sb.append(", ").append(birthDate);
        sb.append(", ").append(passport);
        sb.append(", ").append(favoriteDish);

        sb.append(")");
        return sb.toString();
    }
}
