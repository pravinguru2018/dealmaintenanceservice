package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefRatingCrr.
 */
@Entity
@Table(name = "ref_rating_crr")
public class RefRatingCrr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_crr_id")
    private Integer ratingCrrId;

    @Column(name = "rating_crr_name")
    private String ratingCrrName;

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

    public Integer getRatingCrrId() {
        return ratingCrrId;
    }

    public RefRatingCrr ratingCrrId(Integer ratingCrrId) {
        this.ratingCrrId = ratingCrrId;
        return this;
    }

    public void setRatingCrrId(Integer ratingCrrId) {
        this.ratingCrrId = ratingCrrId;
    }

    public String getRatingCrrName() {
        return ratingCrrName;
    }

    public RefRatingCrr ratingCrrName(String ratingCrrName) {
        this.ratingCrrName = ratingCrrName;
        return this;
    }

    public void setRatingCrrName(String ratingCrrName) {
        this.ratingCrrName = ratingCrrName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefRatingCrr isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefRatingCrr createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefRatingCrr createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefRatingCrr updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefRatingCrr updatedOn(Instant updatedOn) {
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
        RefRatingCrr refRatingCrr = (RefRatingCrr) o;
        if (refRatingCrr.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRatingCrr.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRatingCrr{" +
            "id=" + getId() +
            ", ratingCrrId=" + getRatingCrrId() +
            ", ratingCrrName='" + getRatingCrrName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
