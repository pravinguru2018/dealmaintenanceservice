package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefRatingMoodys.
 */
@Entity
@Table(name = "ref_rating_moodys")
public class RefRatingMoodys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_moodys_id")
    private Integer ratingMoodysId;

    @Column(name = "rating_moodys_name")
    private String ratingMoodysName;

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

    public Integer getRatingMoodysId() {
        return ratingMoodysId;
    }

    public RefRatingMoodys ratingMoodysId(Integer ratingMoodysId) {
        this.ratingMoodysId = ratingMoodysId;
        return this;
    }

    public void setRatingMoodysId(Integer ratingMoodysId) {
        this.ratingMoodysId = ratingMoodysId;
    }

    public String getRatingMoodysName() {
        return ratingMoodysName;
    }

    public RefRatingMoodys ratingMoodysName(String ratingMoodysName) {
        this.ratingMoodysName = ratingMoodysName;
        return this;
    }

    public void setRatingMoodysName(String ratingMoodysName) {
        this.ratingMoodysName = ratingMoodysName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefRatingMoodys isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefRatingMoodys createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefRatingMoodys createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefRatingMoodys updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefRatingMoodys updatedOn(Instant updatedOn) {
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
        RefRatingMoodys refRatingMoodys = (RefRatingMoodys) o;
        if (refRatingMoodys.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRatingMoodys.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRatingMoodys{" +
            "id=" + getId() +
            ", ratingMoodysId=" + getRatingMoodysId() +
            ", ratingMoodysName='" + getRatingMoodysName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
