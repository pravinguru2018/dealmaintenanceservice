package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefOriginationTeam.
 */
@Entity
@Table(name = "ref_origination_team")
public class RefOriginationTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origination_team_id")
    private Integer originationTeamId;

    @Column(name = "origination_team_name")
    private String originationTeamName;

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

    public Integer getOriginationTeamId() {
        return originationTeamId;
    }

    public RefOriginationTeam originationTeamId(Integer originationTeamId) {
        this.originationTeamId = originationTeamId;
        return this;
    }

    public void setOriginationTeamId(Integer originationTeamId) {
        this.originationTeamId = originationTeamId;
    }

    public String getOriginationTeamName() {
        return originationTeamName;
    }

    public RefOriginationTeam originationTeamName(String originationTeamName) {
        this.originationTeamName = originationTeamName;
        return this;
    }

    public void setOriginationTeamName(String originationTeamName) {
        this.originationTeamName = originationTeamName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefOriginationTeam isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefOriginationTeam createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefOriginationTeam createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefOriginationTeam updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefOriginationTeam updatedOn(Instant updatedOn) {
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
        RefOriginationTeam refOriginationTeam = (RefOriginationTeam) o;
        if (refOriginationTeam.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refOriginationTeam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefOriginationTeam{" +
            "id=" + getId() +
            ", originationTeamId=" + getOriginationTeamId() +
            ", originationTeamName='" + getOriginationTeamName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
