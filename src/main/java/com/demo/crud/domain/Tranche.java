package com.demo.crud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Tranche.
 */
@Entity
@Table(name = "tranche")
public class Tranche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tranche_id")
    private Integer trancheId;

    @Column(name = "tranche_name")
    private String trancheName;

    @Column(name = "funded_during_syndication")
    private String fundedDuringSyndication;

    @Column(name = "debt_takeout")
    private String debtTakeout;

    @Column(name = "tenor_years")
    private Integer tenorYears;

    @Column(name = "tenor_months")
    private Integer tenorMonths;

    @Column(name = "bridge_takeout_date")
    private Instant bridgeTakeoutDate;

    @Column(name = "credit_approved_lcym_structure")
    private Integer creditApprovedLcymStructure;

    @Column(name = "credit_approved_lcym_commit")
    private Integer creditApprovedLcymCommit;

    @Column(name = "credit_approved_lcym_hold")
    private Integer creditApprovedLcymHold;

    @Column(name = "final_approved_lcym_structure")
    private Integer finalApprovedLcymStructure;

    @Column(name = "final_approved_lcym_commit")
    private Integer finalApprovedLcymCommit;

    @Column(name = "final_approved_lcym_hold")
    private Integer finalApprovedLcymHold;

    @Column(name = "market_risk_lcym_economic")
    private Integer marketRiskLcymEconomic;

    @Column(name = "market_risk_lcym_legal")
    private Integer marketRiskLcymLegal;

    @Column(name = "market_risk_lcym_settlement")
    private Integer marketRiskLcymSettlement;

    @Column(name = "current_bridge_hold_lcym")
    private Integer currentBridgeHoldLcym;

    @Column(name = "tenor_high_yield_bond_years")
    private Integer tenorHighYieldBondYears;

    @Column(name = "tenor_high_yield_bond_months")
    private Integer tenorHighYieldBondMonths;

    @Column(name = "bond_cap_rate")
    private Integer bondCapRate;

    @Column(name = "ref_margin_rate_name")
    private String refMarginRateName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @OneToOne    @JoinColumn(unique = true)
    private RefFacilityType facilityTypeId;

    @OneToOne    @JoinColumn(unique = true)
    private RefBookingEntity bookingEntityId;

    @OneToOne    @JoinColumn(unique = true)
    private RefSeniority seniorityId;

    @OneToOne    @JoinColumn(unique = true)
    private RefCovenant covenantId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingMoodys ratingMoodysId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingSAndP ratingSAndPId;

    @OneToOne    @JoinColumn(unique = true)
    private RefRatingFitch ratingFitchId;

    @OneToMany(mappedBy = "tranche")
    private Set<Deal> dealIds = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTrancheId() {
        return trancheId;
    }

    public Tranche trancheId(Integer trancheId) {
        this.trancheId = trancheId;
        return this;
    }

    public void setTrancheId(Integer trancheId) {
        this.trancheId = trancheId;
    }

    public String getTrancheName() {
        return trancheName;
    }

    public Tranche trancheName(String trancheName) {
        this.trancheName = trancheName;
        return this;
    }

    public void setTrancheName(String trancheName) {
        this.trancheName = trancheName;
    }

    public String getFundedDuringSyndication() {
        return fundedDuringSyndication;
    }

    public Tranche fundedDuringSyndication(String fundedDuringSyndication) {
        this.fundedDuringSyndication = fundedDuringSyndication;
        return this;
    }

    public void setFundedDuringSyndication(String fundedDuringSyndication) {
        this.fundedDuringSyndication = fundedDuringSyndication;
    }

    public String getDebtTakeout() {
        return debtTakeout;
    }

    public Tranche debtTakeout(String debtTakeout) {
        this.debtTakeout = debtTakeout;
        return this;
    }

    public void setDebtTakeout(String debtTakeout) {
        this.debtTakeout = debtTakeout;
    }

    public Integer getTenorYears() {
        return tenorYears;
    }

    public Tranche tenorYears(Integer tenorYears) {
        this.tenorYears = tenorYears;
        return this;
    }

    public void setTenorYears(Integer tenorYears) {
        this.tenorYears = tenorYears;
    }

    public Integer getTenorMonths() {
        return tenorMonths;
    }

    public Tranche tenorMonths(Integer tenorMonths) {
        this.tenorMonths = tenorMonths;
        return this;
    }

    public void setTenorMonths(Integer tenorMonths) {
        this.tenorMonths = tenorMonths;
    }

    public Instant getBridgeTakeoutDate() {
        return bridgeTakeoutDate;
    }

    public Tranche bridgeTakeoutDate(Instant bridgeTakeoutDate) {
        this.bridgeTakeoutDate = bridgeTakeoutDate;
        return this;
    }

    public void setBridgeTakeoutDate(Instant bridgeTakeoutDate) {
        this.bridgeTakeoutDate = bridgeTakeoutDate;
    }

    public Integer getCreditApprovedLcymStructure() {
        return creditApprovedLcymStructure;
    }

    public Tranche creditApprovedLcymStructure(Integer creditApprovedLcymStructure) {
        this.creditApprovedLcymStructure = creditApprovedLcymStructure;
        return this;
    }

    public void setCreditApprovedLcymStructure(Integer creditApprovedLcymStructure) {
        this.creditApprovedLcymStructure = creditApprovedLcymStructure;
    }

    public Integer getCreditApprovedLcymCommit() {
        return creditApprovedLcymCommit;
    }

    public Tranche creditApprovedLcymCommit(Integer creditApprovedLcymCommit) {
        this.creditApprovedLcymCommit = creditApprovedLcymCommit;
        return this;
    }

    public void setCreditApprovedLcymCommit(Integer creditApprovedLcymCommit) {
        this.creditApprovedLcymCommit = creditApprovedLcymCommit;
    }

    public Integer getCreditApprovedLcymHold() {
        return creditApprovedLcymHold;
    }

    public Tranche creditApprovedLcymHold(Integer creditApprovedLcymHold) {
        this.creditApprovedLcymHold = creditApprovedLcymHold;
        return this;
    }

    public void setCreditApprovedLcymHold(Integer creditApprovedLcymHold) {
        this.creditApprovedLcymHold = creditApprovedLcymHold;
    }

    public Integer getFinalApprovedLcymStructure() {
        return finalApprovedLcymStructure;
    }

    public Tranche finalApprovedLcymStructure(Integer finalApprovedLcymStructure) {
        this.finalApprovedLcymStructure = finalApprovedLcymStructure;
        return this;
    }

    public void setFinalApprovedLcymStructure(Integer finalApprovedLcymStructure) {
        this.finalApprovedLcymStructure = finalApprovedLcymStructure;
    }

    public Integer getFinalApprovedLcymCommit() {
        return finalApprovedLcymCommit;
    }

    public Tranche finalApprovedLcymCommit(Integer finalApprovedLcymCommit) {
        this.finalApprovedLcymCommit = finalApprovedLcymCommit;
        return this;
    }

    public void setFinalApprovedLcymCommit(Integer finalApprovedLcymCommit) {
        this.finalApprovedLcymCommit = finalApprovedLcymCommit;
    }

    public Integer getFinalApprovedLcymHold() {
        return finalApprovedLcymHold;
    }

    public Tranche finalApprovedLcymHold(Integer finalApprovedLcymHold) {
        this.finalApprovedLcymHold = finalApprovedLcymHold;
        return this;
    }

    public void setFinalApprovedLcymHold(Integer finalApprovedLcymHold) {
        this.finalApprovedLcymHold = finalApprovedLcymHold;
    }

    public Integer getMarketRiskLcymEconomic() {
        return marketRiskLcymEconomic;
    }

    public Tranche marketRiskLcymEconomic(Integer marketRiskLcymEconomic) {
        this.marketRiskLcymEconomic = marketRiskLcymEconomic;
        return this;
    }

    public void setMarketRiskLcymEconomic(Integer marketRiskLcymEconomic) {
        this.marketRiskLcymEconomic = marketRiskLcymEconomic;
    }

    public Integer getMarketRiskLcymLegal() {
        return marketRiskLcymLegal;
    }

    public Tranche marketRiskLcymLegal(Integer marketRiskLcymLegal) {
        this.marketRiskLcymLegal = marketRiskLcymLegal;
        return this;
    }

    public void setMarketRiskLcymLegal(Integer marketRiskLcymLegal) {
        this.marketRiskLcymLegal = marketRiskLcymLegal;
    }

    public Integer getMarketRiskLcymSettlement() {
        return marketRiskLcymSettlement;
    }

    public Tranche marketRiskLcymSettlement(Integer marketRiskLcymSettlement) {
        this.marketRiskLcymSettlement = marketRiskLcymSettlement;
        return this;
    }

    public void setMarketRiskLcymSettlement(Integer marketRiskLcymSettlement) {
        this.marketRiskLcymSettlement = marketRiskLcymSettlement;
    }

    public Integer getCurrentBridgeHoldLcym() {
        return currentBridgeHoldLcym;
    }

    public Tranche currentBridgeHoldLcym(Integer currentBridgeHoldLcym) {
        this.currentBridgeHoldLcym = currentBridgeHoldLcym;
        return this;
    }

    public void setCurrentBridgeHoldLcym(Integer currentBridgeHoldLcym) {
        this.currentBridgeHoldLcym = currentBridgeHoldLcym;
    }

    public Integer getTenorHighYieldBondYears() {
        return tenorHighYieldBondYears;
    }

    public Tranche tenorHighYieldBondYears(Integer tenorHighYieldBondYears) {
        this.tenorHighYieldBondYears = tenorHighYieldBondYears;
        return this;
    }

    public void setTenorHighYieldBondYears(Integer tenorHighYieldBondYears) {
        this.tenorHighYieldBondYears = tenorHighYieldBondYears;
    }

    public Integer getTenorHighYieldBondMonths() {
        return tenorHighYieldBondMonths;
    }

    public Tranche tenorHighYieldBondMonths(Integer tenorHighYieldBondMonths) {
        this.tenorHighYieldBondMonths = tenorHighYieldBondMonths;
        return this;
    }

    public void setTenorHighYieldBondMonths(Integer tenorHighYieldBondMonths) {
        this.tenorHighYieldBondMonths = tenorHighYieldBondMonths;
    }

    public Integer getBondCapRate() {
        return bondCapRate;
    }

    public Tranche bondCapRate(Integer bondCapRate) {
        this.bondCapRate = bondCapRate;
        return this;
    }

    public void setBondCapRate(Integer bondCapRate) {
        this.bondCapRate = bondCapRate;
    }

    public String getRefMarginRateName() {
        return refMarginRateName;
    }

    public Tranche refMarginRateName(String refMarginRateName) {
        this.refMarginRateName = refMarginRateName;
        return this;
    }

    public void setRefMarginRateName(String refMarginRateName) {
        this.refMarginRateName = refMarginRateName;
    }

    public String getCurrency() {
        return currency;
    }

    public Tranche currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Tranche sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Tranche createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Tranche createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Tranche updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public Tranche updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public RefFacilityType getFacilityTypeId() {
        return facilityTypeId;
    }

    public Tranche facilityTypeId(RefFacilityType refFacilityType) {
        this.facilityTypeId = refFacilityType;
        return this;
    }

    public void setFacilityTypeId(RefFacilityType refFacilityType) {
        this.facilityTypeId = refFacilityType;
    }

    public RefBookingEntity getBookingEntityId() {
        return bookingEntityId;
    }

    public Tranche bookingEntityId(RefBookingEntity refBookingEntity) {
        this.bookingEntityId = refBookingEntity;
        return this;
    }

    public void setBookingEntityId(RefBookingEntity refBookingEntity) {
        this.bookingEntityId = refBookingEntity;
    }

    public RefSeniority getSeniorityId() {
        return seniorityId;
    }

    public Tranche seniorityId(RefSeniority refSeniority) {
        this.seniorityId = refSeniority;
        return this;
    }

    public void setSeniorityId(RefSeniority refSeniority) {
        this.seniorityId = refSeniority;
    }

    public RefCovenant getCovenantId() {
        return covenantId;
    }

    public Tranche covenantId(RefCovenant refCovenant) {
        this.covenantId = refCovenant;
        return this;
    }

    public void setCovenantId(RefCovenant refCovenant) {
        this.covenantId = refCovenant;
    }

    public RefRatingMoodys getRatingMoodysId() {
        return ratingMoodysId;
    }

    public Tranche ratingMoodysId(RefRatingMoodys refRatingMoodys) {
        this.ratingMoodysId = refRatingMoodys;
        return this;
    }

    public void setRatingMoodysId(RefRatingMoodys refRatingMoodys) {
        this.ratingMoodysId = refRatingMoodys;
    }

    public RefRatingSAndP getRatingSAndPId() {
        return ratingSAndPId;
    }

    public Tranche ratingSAndPId(RefRatingSAndP refRatingSAndP) {
        this.ratingSAndPId = refRatingSAndP;
        return this;
    }

    public void setRatingSAndPId(RefRatingSAndP refRatingSAndP) {
        this.ratingSAndPId = refRatingSAndP;
    }

    public RefRatingFitch getRatingFitchId() {
        return ratingFitchId;
    }

    public Tranche ratingFitchId(RefRatingFitch refRatingFitch) {
        this.ratingFitchId = refRatingFitch;
        return this;
    }

    public void setRatingFitchId(RefRatingFitch refRatingFitch) {
        this.ratingFitchId = refRatingFitch;
    }

    public Set<Deal> getDealIds() {
        return dealIds;
    }

    public Tranche dealIds(Set<Deal> deals) {
        this.dealIds = deals;
        return this;
    }

    public Tranche addDealId(Deal deal) {
        this.dealIds.add(deal);
        deal.setTranche(this);
        return this;
    }

    public Tranche removeDealId(Deal deal) {
        this.dealIds.remove(deal);
        deal.setTranche(null);
        return this;
    }

    public void setDealIds(Set<Deal> deals) {
        this.dealIds = deals;
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
        Tranche tranche = (Tranche) o;
        if (tranche.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tranche.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tranche{" +
            "id=" + getId() +
            ", trancheId=" + getTrancheId() +
            ", trancheName='" + getTrancheName() + "'" +
            ", fundedDuringSyndication='" + getFundedDuringSyndication() + "'" +
            ", debtTakeout='" + getDebtTakeout() + "'" +
            ", tenorYears=" + getTenorYears() +
            ", tenorMonths=" + getTenorMonths() +
            ", bridgeTakeoutDate='" + getBridgeTakeoutDate() + "'" +
            ", creditApprovedLcymStructure=" + getCreditApprovedLcymStructure() +
            ", creditApprovedLcymCommit=" + getCreditApprovedLcymCommit() +
            ", creditApprovedLcymHold=" + getCreditApprovedLcymHold() +
            ", finalApprovedLcymStructure=" + getFinalApprovedLcymStructure() +
            ", finalApprovedLcymCommit=" + getFinalApprovedLcymCommit() +
            ", finalApprovedLcymHold=" + getFinalApprovedLcymHold() +
            ", marketRiskLcymEconomic=" + getMarketRiskLcymEconomic() +
            ", marketRiskLcymLegal=" + getMarketRiskLcymLegal() +
            ", marketRiskLcymSettlement=" + getMarketRiskLcymSettlement() +
            ", currentBridgeHoldLcym=" + getCurrentBridgeHoldLcym() +
            ", tenorHighYieldBondYears=" + getTenorHighYieldBondYears() +
            ", tenorHighYieldBondMonths=" + getTenorHighYieldBondMonths() +
            ", bondCapRate=" + getBondCapRate() +
            ", refMarginRateName='" + getRefMarginRateName() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
