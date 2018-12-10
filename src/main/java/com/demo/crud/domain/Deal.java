package com.demo.crud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Deal.
 */
@Entity
@Table(name = "deal")
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deal_id")
    private Integer dealId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "client_sponsor")
    private String clientSponsor;

    @Column(name = "target_asset")
    private String targetAsset;

    @Column(name = "syndicate_owner")
    private String syndicateOwner;

    @Column(name = "deal_pipeline_id")
    private String dealPipelineId;

    @Column(name = "origination_contact")
    private String originationContact;

    @Column(name = "relationship_manager")
    private String relationshipManager;

    @Column(name = "llf")
    private String llf;

    @Column(name = "regulatory_approval_required")
    private String regulatoryApprovalRequired;

    @Column(name = "regulatory_approval_obtained")
    private String regulatoryApprovalObtained;

    @Column(name = "country_of_incorporation")
    private String countryOfIncorporation;

    @Column(name = "regulatory_approval_date")
    private Instant regulatoryApprovalDate;

    @Column(name = "deal_description")
    private String dealDescription;

    @Column(name = "update_note")
    private String updateNote;

    @Column(name = "credit_approval_date")
    private Instant creditApprovalDate;

    @Column(name = "allocation_date")
    private Instant allocationDate;

    @Column(name = "syndication_launch_date")
    private Instant syndicationLaunchDate;

    @Column(name = "commitment_date")
    private Instant commitmentDate;

    @Column(name = "credit_approval_off_risk_date")
    private Instant creditApprovalOffRiskDate;

    @Column(name = "credit_officer")
    private String creditOfficer;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_by")
    private Instant updatedBy;

    @OneToOne    @JoinColumn(unique = true)
    private RefSyndicationTeam syndicationTeamId;

    @OneToOne    @JoinColumn(unique = true)
    private RefDealStatus dealStatusId;

    @OneToOne    @JoinColumn(unique = true)
    private RefOriginationTeam originationTeamId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRecourseToClient recourseToClientId;

    @OneToOne    @JoinColumn(unique = true)
    private RefLineOfBusiness lineOfBusinessId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingCrr ratingCrrId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingMoodys ratingMoodysId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingSAndP ratingSAndPId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingFitch ratingFitchId;

    @OneToOne    @JoinColumn(unique = true)
    private RefCountry countryId;

    @OneToOne    @JoinColumn(unique = true)
    private RefPraSector praSectorId;

    @ManyToOne
    @JsonIgnoreProperties("dealIds")
    private Tranche tranche;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDealId() {
        return dealId;
    }

    public Deal dealId(Integer dealId) {
        this.dealId = dealId;
        return this;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Deal projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientSponsor() {
        return clientSponsor;
    }

    public Deal clientSponsor(String clientSponsor) {
        this.clientSponsor = clientSponsor;
        return this;
    }

    public void setClientSponsor(String clientSponsor) {
        this.clientSponsor = clientSponsor;
    }

    public String getTargetAsset() {
        return targetAsset;
    }

    public Deal targetAsset(String targetAsset) {
        this.targetAsset = targetAsset;
        return this;
    }

    public void setTargetAsset(String targetAsset) {
        this.targetAsset = targetAsset;
    }

    public String getSyndicateOwner() {
        return syndicateOwner;
    }

    public Deal syndicateOwner(String syndicateOwner) {
        this.syndicateOwner = syndicateOwner;
        return this;
    }

    public void setSyndicateOwner(String syndicateOwner) {
        this.syndicateOwner = syndicateOwner;
    }

    public String getDealPipelineId() {
        return dealPipelineId;
    }

    public Deal dealPipelineId(String dealPipelineId) {
        this.dealPipelineId = dealPipelineId;
        return this;
    }

    public void setDealPipelineId(String dealPipelineId) {
        this.dealPipelineId = dealPipelineId;
    }

    public String getOriginationContact() {
        return originationContact;
    }

    public Deal originationContact(String originationContact) {
        this.originationContact = originationContact;
        return this;
    }

    public void setOriginationContact(String originationContact) {
        this.originationContact = originationContact;
    }

    public String getRelationshipManager() {
        return relationshipManager;
    }

    public Deal relationshipManager(String relationshipManager) {
        this.relationshipManager = relationshipManager;
        return this;
    }

    public void setRelationshipManager(String relationshipManager) {
        this.relationshipManager = relationshipManager;
    }

    public String getLlf() {
        return llf;
    }

    public Deal llf(String llf) {
        this.llf = llf;
        return this;
    }

    public void setLlf(String llf) {
        this.llf = llf;
    }

    public String getRegulatoryApprovalRequired() {
        return regulatoryApprovalRequired;
    }

    public Deal regulatoryApprovalRequired(String regulatoryApprovalRequired) {
        this.regulatoryApprovalRequired = regulatoryApprovalRequired;
        return this;
    }

    public void setRegulatoryApprovalRequired(String regulatoryApprovalRequired) {
        this.regulatoryApprovalRequired = regulatoryApprovalRequired;
    }

    public String getRegulatoryApprovalObtained() {
        return regulatoryApprovalObtained;
    }

    public Deal regulatoryApprovalObtained(String regulatoryApprovalObtained) {
        this.regulatoryApprovalObtained = regulatoryApprovalObtained;
        return this;
    }

    public void setRegulatoryApprovalObtained(String regulatoryApprovalObtained) {
        this.regulatoryApprovalObtained = regulatoryApprovalObtained;
    }

    public String getCountryOfIncorporation() {
        return countryOfIncorporation;
    }

    public Deal countryOfIncorporation(String countryOfIncorporation) {
        this.countryOfIncorporation = countryOfIncorporation;
        return this;
    }

    public void setCountryOfIncorporation(String countryOfIncorporation) {
        this.countryOfIncorporation = countryOfIncorporation;
    }

    public Instant getRegulatoryApprovalDate() {
        return regulatoryApprovalDate;
    }

    public Deal regulatoryApprovalDate(Instant regulatoryApprovalDate) {
        this.regulatoryApprovalDate = regulatoryApprovalDate;
        return this;
    }

    public void setRegulatoryApprovalDate(Instant regulatoryApprovalDate) {
        this.regulatoryApprovalDate = regulatoryApprovalDate;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public Deal dealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
        return this;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public String getUpdateNote() {
        return updateNote;
    }

    public Deal updateNote(String updateNote) {
        this.updateNote = updateNote;
        return this;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }

    public Instant getCreditApprovalDate() {
        return creditApprovalDate;
    }

    public Deal creditApprovalDate(Instant creditApprovalDate) {
        this.creditApprovalDate = creditApprovalDate;
        return this;
    }

    public void setCreditApprovalDate(Instant creditApprovalDate) {
        this.creditApprovalDate = creditApprovalDate;
    }

    public Instant getAllocationDate() {
        return allocationDate;
    }

    public Deal allocationDate(Instant allocationDate) {
        this.allocationDate = allocationDate;
        return this;
    }

    public void setAllocationDate(Instant allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Instant getSyndicationLaunchDate() {
        return syndicationLaunchDate;
    }

    public Deal syndicationLaunchDate(Instant syndicationLaunchDate) {
        this.syndicationLaunchDate = syndicationLaunchDate;
        return this;
    }

    public void setSyndicationLaunchDate(Instant syndicationLaunchDate) {
        this.syndicationLaunchDate = syndicationLaunchDate;
    }

    public Instant getCommitmentDate() {
        return commitmentDate;
    }

    public Deal commitmentDate(Instant commitmentDate) {
        this.commitmentDate = commitmentDate;
        return this;
    }

    public void setCommitmentDate(Instant commitmentDate) {
        this.commitmentDate = commitmentDate;
    }

    public Instant getCreditApprovalOffRiskDate() {
        return creditApprovalOffRiskDate;
    }

    public Deal creditApprovalOffRiskDate(Instant creditApprovalOffRiskDate) {
        this.creditApprovalOffRiskDate = creditApprovalOffRiskDate;
        return this;
    }

    public void setCreditApprovalOffRiskDate(Instant creditApprovalOffRiskDate) {
        this.creditApprovalOffRiskDate = creditApprovalOffRiskDate;
    }

    public String getCreditOfficer() {
        return creditOfficer;
    }

    public Deal creditOfficer(String creditOfficer) {
        this.creditOfficer = creditOfficer;
        return this;
    }

    public void setCreditOfficer(String creditOfficer) {
        this.creditOfficer = creditOfficer;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Deal isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Deal createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Deal createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedBy() {
        return updatedBy;
    }

    public Deal updatedBy(Instant updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(Instant updatedBy) {
        this.updatedBy = updatedBy;
    }

    public RefSyndicationTeam getSyndicationTeamId() {
        return syndicationTeamId;
    }

    public Deal syndicationTeamId(RefSyndicationTeam refSyndicationTeam) {
        this.syndicationTeamId = refSyndicationTeam;
        return this;
    }

    public void setSyndicationTeamId(RefSyndicationTeam refSyndicationTeam) {
        this.syndicationTeamId = refSyndicationTeam;
    }

    public RefDealStatus getDealStatusId() {
        return dealStatusId;
    }

    public Deal dealStatusId(RefDealStatus refDealStatus) {
        this.dealStatusId = refDealStatus;
        return this;
    }

    public void setDealStatusId(RefDealStatus refDealStatus) {
        this.dealStatusId = refDealStatus;
    }

    public RefOriginationTeam getOriginationTeamId() {
        return originationTeamId;
    }

    public Deal originationTeamId(RefOriginationTeam refOriginationTeam) {
        this.originationTeamId = refOriginationTeam;
        return this;
    }

    public void setOriginationTeamId(RefOriginationTeam refOriginationTeam) {
        this.originationTeamId = refOriginationTeam;
    }

    public RefRecourseToClient getRecourseToClientId() {
        return recourseToClientId;
    }

    public Deal recourseToClientId(RefRecourseToClient refRecourseToClient) {
        this.recourseToClientId = refRecourseToClient;
        return this;
    }

    public void setRecourseToClientId(RefRecourseToClient refRecourseToClient) {
        this.recourseToClientId = refRecourseToClient;
    }

    public RefLineOfBusiness getLineOfBusinessId() {
        return lineOfBusinessId;
    }

    public Deal lineOfBusinessId(RefLineOfBusiness refLineOfBusiness) {
        this.lineOfBusinessId = refLineOfBusiness;
        return this;
    }

    public void setLineOfBusinessId(RefLineOfBusiness refLineOfBusiness) {
        this.lineOfBusinessId = refLineOfBusiness;
    }

    public RefRatingCrr getRatingCrrId() {
        return ratingCrrId;
    }

    public Deal ratingCrrId(RefRatingCrr refRatingCrr) {
        this.ratingCrrId = refRatingCrr;
        return this;
    }

    public void setRatingCrrId(RefRatingCrr refRatingCrr) {
        this.ratingCrrId = refRatingCrr;
    }

    public RefRatingMoodys getRatingMoodysId() {
        return ratingMoodysId;
    }

    public Deal ratingMoodysId(RefRatingMoodys refRatingMoodys) {
        this.ratingMoodysId = refRatingMoodys;
        return this;
    }

    public void setRatingMoodysId(RefRatingMoodys refRatingMoodys) {
        this.ratingMoodysId = refRatingMoodys;
    }

    public RefRatingSAndP getRatingSAndPId() {
        return ratingSAndPId;
    }

    public Deal ratingSAndPId(RefRatingSAndP refRatingSAndP) {
        this.ratingSAndPId = refRatingSAndP;
        return this;
    }

    public void setRatingSAndPId(RefRatingSAndP refRatingSAndP) {
        this.ratingSAndPId = refRatingSAndP;
    }

    public RefRatingFitch getRatingFitchId() {
        return ratingFitchId;
    }

    public Deal ratingFitchId(RefRatingFitch refRatingFitch) {
        this.ratingFitchId = refRatingFitch;
        return this;
    }

    public void setRatingFitchId(RefRatingFitch refRatingFitch) {
        this.ratingFitchId = refRatingFitch;
    }

    public RefCountry getCountryId() {
        return countryId;
    }

    public Deal countryId(RefCountry refCountry) {
        this.countryId = refCountry;
        return this;
    }

    public void setCountryId(RefCountry refCountry) {
        this.countryId = refCountry;
    }

    public RefPraSector getPraSectorId() {
        return praSectorId;
    }

    public Deal praSectorId(RefPraSector refPraSector) {
        this.praSectorId = refPraSector;
        return this;
    }

    public void setPraSectorId(RefPraSector refPraSector) {
        this.praSectorId = refPraSector;
    }

    public Tranche getTranche() {
        return tranche;
    }

    public Deal tranche(Tranche tranche) {
        this.tranche = tranche;
        return this;
    }

    public void setTranche(Tranche tranche) {
        this.tranche = tranche;
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
        Deal deal = (Deal) o;
        if (deal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Deal{" +
            "id=" + getId() +
            ", dealId=" + getDealId() +
            ", projectName='" + getProjectName() + "'" +
            ", clientSponsor='" + getClientSponsor() + "'" +
            ", targetAsset='" + getTargetAsset() + "'" +
            ", syndicateOwner='" + getSyndicateOwner() + "'" +
            ", dealPipelineId='" + getDealPipelineId() + "'" +
            ", originationContact='" + getOriginationContact() + "'" +
            ", relationshipManager='" + getRelationshipManager() + "'" +
            ", llf='" + getLlf() + "'" +
            ", regulatoryApprovalRequired='" + getRegulatoryApprovalRequired() + "'" +
            ", regulatoryApprovalObtained='" + getRegulatoryApprovalObtained() + "'" +
            ", countryOfIncorporation='" + getCountryOfIncorporation() + "'" +
            ", regulatoryApprovalDate='" + getRegulatoryApprovalDate() + "'" +
            ", dealDescription='" + getDealDescription() + "'" +
            ", updateNote='" + getUpdateNote() + "'" +
            ", creditApprovalDate='" + getCreditApprovalDate() + "'" +
            ", allocationDate='" + getAllocationDate() + "'" +
            ", syndicationLaunchDate='" + getSyndicationLaunchDate() + "'" +
            ", commitmentDate='" + getCommitmentDate() + "'" +
            ", creditApprovalOffRiskDate='" + getCreditApprovalOffRiskDate() + "'" +
            ", creditOfficer='" + getCreditOfficer() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
