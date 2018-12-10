package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefDealStatus.
 */
@Entity
@Table(name = "ref_deal_status")
public class RefDealStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deal_status_id")
    private Integer dealStatusId;

    @Column(name = "deal_status_name")
    private String dealStatusName;

    @Column(name = "credit_approval_status")
    private String creditApprovalStatus;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "display_key")
    private String displayKey;

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

    public Integer getDealStatusId() {
        return dealStatusId;
    }

    public RefDealStatus dealStatusId(Integer dealStatusId) {
        this.dealStatusId = dealStatusId;
        return this;
    }

    public void setDealStatusId(Integer dealStatusId) {
        this.dealStatusId = dealStatusId;
    }

    public String getDealStatusName() {
        return dealStatusName;
    }

    public RefDealStatus dealStatusName(String dealStatusName) {
        this.dealStatusName = dealStatusName;
        return this;
    }

    public void setDealStatusName(String dealStatusName) {
        this.dealStatusName = dealStatusName;
    }

    public String getCreditApprovalStatus() {
        return creditApprovalStatus;
    }

    public RefDealStatus creditApprovalStatus(String creditApprovalStatus) {
        this.creditApprovalStatus = creditApprovalStatus;
        return this;
    }

    public void setCreditApprovalStatus(String creditApprovalStatus) {
        this.creditApprovalStatus = creditApprovalStatus;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public RefDealStatus displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDisplayKey() {
        return displayKey;
    }

    public RefDealStatus displayKey(String displayKey) {
        this.displayKey = displayKey;
        return this;
    }

    public void setDisplayKey(String displayKey) {
        this.displayKey = displayKey;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefDealStatus isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefDealStatus createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefDealStatus createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefDealStatus updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefDealStatus updatedOn(Instant updatedOn) {
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
        RefDealStatus refDealStatus = (RefDealStatus) o;
        if (refDealStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refDealStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefDealStatus{" +
            "id=" + getId() +
            ", dealStatusId=" + getDealStatusId() +
            ", dealStatusName='" + getDealStatusName() + "'" +
            ", creditApprovalStatus='" + getCreditApprovalStatus() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", displayKey='" + getDisplayKey() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
