package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefRatingSAndP.
 */
@Entity
@Table(name = "ref_rating_s_and_p")
public class RefRatingSAndP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_s_and_p_id")
    private Integer ratingSAndPId;

    @Column(name = "rating_s_and_p_name")
    private String ratingSAndPName;

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

    public Integer getRatingSAndPId() {
        return ratingSAndPId;
    }

    public RefRatingSAndP ratingSAndPId(Integer ratingSAndPId) {
        this.ratingSAndPId = ratingSAndPId;
        return this;
    }

    public void setRatingSAndPId(Integer ratingSAndPId) {
        this.ratingSAndPId = ratingSAndPId;
    }

    public String getRatingSAndPName() {
        return ratingSAndPName;
    }

    public RefRatingSAndP ratingSAndPName(String ratingSAndPName) {
        this.ratingSAndPName = ratingSAndPName;
        return this;
    }

    public void setRatingSAndPName(String ratingSAndPName) {
        this.ratingSAndPName = ratingSAndPName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefRatingSAndP isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefRatingSAndP createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefRatingSAndP createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefRatingSAndP updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefRatingSAndP updatedOn(Instant updatedOn) {
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
        RefRatingSAndP refRatingSAndP = (RefRatingSAndP) o;
        if (refRatingSAndP.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRatingSAndP.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRatingSAndP{" +
            "id=" + getId() +
            ", ratingSAndPId=" + getRatingSAndPId() +
            ", ratingSAndPName='" + getRatingSAndPName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
