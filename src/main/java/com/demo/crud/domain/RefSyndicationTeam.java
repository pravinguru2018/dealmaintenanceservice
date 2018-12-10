package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RefSyndicationTeam.
 */
@Entity
@Table(name = "ref_syndication_team")
public class RefSyndicationTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_syndication_team_id")
    private Integer refSyndicationTeamId;

    @Column(name = "ref_syndication_team_name")
    private String refSyndicationTeamName;

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

    public Integer getRefSyndicationTeamId() {
        return refSyndicationTeamId;
    }

    public RefSyndicationTeam refSyndicationTeamId(Integer refSyndicationTeamId) {
        this.refSyndicationTeamId = refSyndicationTeamId;
        return this;
    }

    public void setRefSyndicationTeamId(Integer refSyndicationTeamId) {
        this.refSyndicationTeamId = refSyndicationTeamId;
    }

    public String getRefSyndicationTeamName() {
        return refSyndicationTeamName;
    }

    public RefSyndicationTeam refSyndicationTeamName(String refSyndicationTeamName) {
        this.refSyndicationTeamName = refSyndicationTeamName;
        return this;
    }

    public void setRefSyndicationTeamName(String refSyndicationTeamName) {
        this.refSyndicationTeamName = refSyndicationTeamName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RefSyndicationTeam isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RefSyndicationTeam createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public RefSyndicationTeam createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public RefSyndicationTeam updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public RefSyndicationTeam updatedOn(Instant updatedOn) {
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
        RefSyndicationTeam refSyndicationTeam = (RefSyndicationTeam) o;
        if (refSyndicationTeam.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refSyndicationTeam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefSyndicationTeam{" +
            "id=" + getId() +
            ", refSyndicationTeamId=" + getRefSyndicationTeamId() +
            ", refSyndicationTeamName='" + getRefSyndicationTeamName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
