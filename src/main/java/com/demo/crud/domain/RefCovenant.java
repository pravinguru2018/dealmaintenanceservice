package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefCovenant.
 */
@Entity
@Table(name = "ref_covenant")
public class RefCovenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "covenant_id")
    private Integer covenantId;

    @Column(name = "covenant_name")
    private String covenantName;

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

    public Integer getCovenantId() {
        return covenantId;
    }

    public RefCovenant covenantId(Integer covenantId) {
        this.covenantId = covenantId;
        return this;
    }

    public void setCovenantId(Integer covenantId) {
        this.covenantId = covenantId;
    }

    public String getCovenantName() {
        return covenantName;
    }

    public RefCovenant covenantName(String covenantName) {
        this.covenantName = covenantName;
        return this;
    }

    public void setCovenantName(String covenantName) {
        this.covenantName = covenantName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefCovenant isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefCovenant createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefCovenant createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefCovenant updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefCovenant updatedOn(Instant updatedOn) {
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
        RefCovenant refCovenant = (RefCovenant) o;
        if (refCovenant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refCovenant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefCovenant{" +
            "id=" + getId() +
            ", covenantId=" + getCovenantId() +
            ", covenantName='" + getCovenantName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
