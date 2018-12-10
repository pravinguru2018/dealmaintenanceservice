package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefRatingFitch.
 */
@Entity
@Table(name = "ref_rating_fitch")
public class RefRatingFitch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_fitch_id")
    private Integer ratingFitchId;

    @Column(name = "rating_fitch_name")
    private String ratingFitchName;

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

    public Integer getRatingFitchId() {
        return ratingFitchId;
    }

    public RefRatingFitch ratingFitchId(Integer ratingFitchId) {
        this.ratingFitchId = ratingFitchId;
        return this;
    }

    public void setRatingFitchId(Integer ratingFitchId) {
        this.ratingFitchId = ratingFitchId;
    }

    public String getRatingFitchName() {
        return ratingFitchName;
    }

    public RefRatingFitch ratingFitchName(String ratingFitchName) {
        this.ratingFitchName = ratingFitchName;
        return this;
    }

    public void setRatingFitchName(String ratingFitchName) {
        this.ratingFitchName = ratingFitchName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefRatingFitch isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefRatingFitch createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefRatingFitch createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefRatingFitch updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefRatingFitch updatedOn(Instant updatedOn) {
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
        RefRatingFitch refRatingFitch = (RefRatingFitch) o;
        if (refRatingFitch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRatingFitch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRatingFitch{" +
            "id=" + getId() +
            ", ratingFitchId=" + getRatingFitchId() +
            ", ratingFitchName='" + getRatingFitchName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
