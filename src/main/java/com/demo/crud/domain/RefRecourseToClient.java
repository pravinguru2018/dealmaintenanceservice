package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefRecourseToClient.
 */
@Entity
@Table(name = "ref_recourse_to_client")
public class RefRecourseToClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recourse_to_client_id")
    private Integer recourseToClientId;

    @Column(name = "recourse_to_client_name")
    private String recourseToClientName;

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

    public Integer getRecourseToClientId() {
        return recourseToClientId;
    }

    public RefRecourseToClient recourseToClientId(Integer recourseToClientId) {
        this.recourseToClientId = recourseToClientId;
        return this;
    }

    public void setRecourseToClientId(Integer recourseToClientId) {
        this.recourseToClientId = recourseToClientId;
    }

    public String getRecourseToClientName() {
        return recourseToClientName;
    }

    public RefRecourseToClient recourseToClientName(String recourseToClientName) {
        this.recourseToClientName = recourseToClientName;
        return this;
    }

    public void setRecourseToClientName(String recourseToClientName) {
        this.recourseToClientName = recourseToClientName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefRecourseToClient isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefRecourseToClient createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefRecourseToClient createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefRecourseToClient updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefRecourseToClient updatedOn(Instant updatedOn) {
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
        RefRecourseToClient refRecourseToClient = (RefRecourseToClient) o;
        if (refRecourseToClient.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRecourseToClient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRecourseToClient{" +
            "id=" + getId() +
            ", recourseToClientId=" + getRecourseToClientId() +
            ", recourseToClientName='" + getRecourseToClientName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
