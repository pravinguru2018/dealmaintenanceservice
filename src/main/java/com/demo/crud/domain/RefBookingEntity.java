package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefBookingEntity.
 */
@Entity
@Table(name = "ref_booking_entity")
public class RefBookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_entity_id")
    private Integer bookingEntityId;

    @Column(name = "booking_entity_name")
    private String bookingEntityName;

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

    public Integer getBookingEntityId() {
        return bookingEntityId;
    }

    public RefBookingEntity bookingEntityId(Integer bookingEntityId) {
        this.bookingEntityId = bookingEntityId;
        return this;
    }

    public void setBookingEntityId(Integer bookingEntityId) {
        this.bookingEntityId = bookingEntityId;
    }

    public String getBookingEntityName() {
        return bookingEntityName;
    }

    public RefBookingEntity bookingEntityName(String bookingEntityName) {
        this.bookingEntityName = bookingEntityName;
        return this;
    }

    public void setBookingEntityName(String bookingEntityName) {
        this.bookingEntityName = bookingEntityName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefBookingEntity isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefBookingEntity createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefBookingEntity createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefBookingEntity updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefBookingEntity updatedOn(Instant updatedOn) {
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
        RefBookingEntity refBookingEntity = (RefBookingEntity) o;
        if (refBookingEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refBookingEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefBookingEntity{" +
            "id=" + getId() +
            ", bookingEntityId=" + getBookingEntityId() +
            ", bookingEntityName='" + getBookingEntityName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
