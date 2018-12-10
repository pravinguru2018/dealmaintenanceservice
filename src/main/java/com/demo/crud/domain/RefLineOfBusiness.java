package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefLineOfBusiness.
 */
@Entity
@Table(name = "ref_line_of_business")
public class RefLineOfBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line_of_business_id")
    private Integer lineOfBusinessId;

    @Column(name = "line_of_business_name")
    private String lineOfBusinessName;

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

    public Integer getLineOfBusinessId() {
        return lineOfBusinessId;
    }

    public RefLineOfBusiness lineOfBusinessId(Integer lineOfBusinessId) {
        this.lineOfBusinessId = lineOfBusinessId;
        return this;
    }

    public void setLineOfBusinessId(Integer lineOfBusinessId) {
        this.lineOfBusinessId = lineOfBusinessId;
    }

    public String getLineOfBusinessName() {
        return lineOfBusinessName;
    }

    public RefLineOfBusiness lineOfBusinessName(String lineOfBusinessName) {
        this.lineOfBusinessName = lineOfBusinessName;
        return this;
    }

    public void setLineOfBusinessName(String lineOfBusinessName) {
        this.lineOfBusinessName = lineOfBusinessName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefLineOfBusiness isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefLineOfBusiness createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefLineOfBusiness createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefLineOfBusiness updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefLineOfBusiness updatedOn(Instant updatedOn) {
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
        RefLineOfBusiness refLineOfBusiness = (RefLineOfBusiness) o;
        if (refLineOfBusiness.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refLineOfBusiness.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefLineOfBusiness{" +
            "id=" + getId() +
            ", lineOfBusinessId=" + getLineOfBusinessId() +
            ", lineOfBusinessName='" + getLineOfBusinessName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
