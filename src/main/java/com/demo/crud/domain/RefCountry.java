package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefCountry.
 */
@Entity
@Table(name = "ref_country")
public class RefCountry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_pra")
    private String countryPra;

    @Column(name = "region_pra")
    private String regionPra;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Instant updatedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public RefCountry countryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public RefCountry countryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryPra() {
        return countryPra;
    }

    public RefCountry countryPra(String countryPra) {
        this.countryPra = countryPra;
        return this;
    }

    public void setCountryPra(String countryPra) {
        this.countryPra = countryPra;
    }

    public String getRegionPra() {
        return regionPra;
    }

    public RefCountry regionPra(String regionPra) {
        this.regionPra = regionPra;
        return this;
    }

    public void setRegionPra(String regionPra) {
        this.regionPra = regionPra;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefCountry isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefCountry createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefCountry createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefCountry updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefCountry updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefCountry refCountry = (RefCountry) o;
        if (refCountry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refCountry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefCountry{" +
            "id=" + getId() +
            ", countryId=" + getCountryId() +
            ", countryName='" + getCountryName() + "'" +
            ", countryPra='" + getCountryPra() + "'" +
            ", regionPra='" + getRegionPra() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
