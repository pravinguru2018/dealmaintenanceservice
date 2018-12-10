package com.demo.crud.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TrancheMargin.
 */
@Entity
@Table(name = "tranche_margin")
public class TrancheMargin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tranche_margin_id")
    private Integer trancheMarginId;

    @Column(name = "margin_spread_bps")
    private String marginSpreadBps;

    @Column(name = "floor_percentage")
    private Integer floorPercentage;

    @Column(name = "interest_payment_frequency")
    private String interestPaymentFrequency;

    @Column(name = "total_pricing_flex_bps_pa")
    private String totalPricingFlexBpsPa;

    @Column(name = "underwrite_fees_bps")
    private Integer underwriteFeesBps;

    @Column(name = "participation_fees")
    private Integer participationFees;

    @Column(name = "extension_fees_6_months")
    private Integer extensionFees6Months;

    @Column(name = "extension_fees_6_to_12_months")
    private Integer extensionFees6To12Months;

    @Column(name = "other_flex")
    private String otherFlex;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @OneToOne    @JoinColumn(unique = true)
    private Tranche trancheId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTrancheMarginId() {
        return trancheMarginId;
    }

    public TrancheMargin trancheMarginId(Integer trancheMarginId) {
        this.trancheMarginId = trancheMarginId;
        return this;
    }

    public void setTrancheMarginId(Integer trancheMarginId) {
        this.trancheMarginId = trancheMarginId;
    }

    public String getMarginSpreadBps() {
        return marginSpreadBps;
    }

    public TrancheMargin marginSpreadBps(String marginSpreadBps) {
        this.marginSpreadBps = marginSpreadBps;
        return this;
    }

    public void setMarginSpreadBps(String marginSpreadBps) {
        this.marginSpreadBps = marginSpreadBps;
    }

    public Integer getFloorPercentage() {
        return floorPercentage;
    }

    public TrancheMargin floorPercentage(Integer floorPercentage) {
        this.floorPercentage = floorPercentage;
        return this;
    }

    public void setFloorPercentage(Integer floorPercentage) {
        this.floorPercentage = floorPercentage;
    }

    public String getInterestPaymentFrequency() {
        return interestPaymentFrequency;
    }

    public TrancheMargin interestPaymentFrequency(String interestPaymentFrequency) {
        this.interestPaymentFrequency = interestPaymentFrequency;
        return this;
    }

    public void setInterestPaymentFrequency(String interestPaymentFrequency) {
        this.interestPaymentFrequency = interestPaymentFrequency;
    }

    public String getTotalPricingFlexBpsPa() {
        return totalPricingFlexBpsPa;
    }

    public TrancheMargin totalPricingFlexBpsPa(String totalPricingFlexBpsPa) {
        this.totalPricingFlexBpsPa = totalPricingFlexBpsPa;
        return this;
    }

    public void setTotalPricingFlexBpsPa(String totalPricingFlexBpsPa) {
        this.totalPricingFlexBpsPa = totalPricingFlexBpsPa;
    }

    public Integer getUnderwriteFeesBps() {
        return underwriteFeesBps;
    }

    public TrancheMargin underwriteFeesBps(Integer underwriteFeesBps) {
        this.underwriteFeesBps = underwriteFeesBps;
        return this;
    }

    public void setUnderwriteFeesBps(Integer underwriteFeesBps) {
        this.underwriteFeesBps = underwriteFeesBps;
    }

    public Integer getParticipationFees() {
        return participationFees;
    }

    public TrancheMargin participationFees(Integer participationFees) {
        this.participationFees = participationFees;
        return this;
    }

    public void setParticipationFees(Integer participationFees) {
        this.participationFees = participationFees;
    }

    public Integer getExtensionFees6Months() {
        return extensionFees6Months;
    }

    public TrancheMargin extensionFees6Months(Integer extensionFees6Months) {
        this.extensionFees6Months = extensionFees6Months;
        return this;
    }

    public void setExtensionFees6Months(Integer extensionFees6Months) {
        this.extensionFees6Months = extensionFees6Months;
    }

    public Integer getExtensionFees6To12Months() {
        return extensionFees6To12Months;
    }

    public TrancheMargin extensionFees6To12Months(Integer extensionFees6To12Months) {
        this.extensionFees6To12Months = extensionFees6To12Months;
        return this;
    }

    public void setExtensionFees6To12Months(Integer extensionFees6To12Months) {
        this.extensionFees6To12Months = extensionFees6To12Months;
    }

    public String getOtherFlex() {
        return otherFlex;
    }

    public TrancheMargin otherFlex(String otherFlex) {
        this.otherFlex = otherFlex;
        return this;
    }

    public void setOtherFlex(String otherFlex) {
        this.otherFlex = otherFlex;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TrancheMargin createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public TrancheMargin createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public TrancheMargin updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public TrancheMargin updatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Tranche getTrancheId() {
        return trancheId;
    }

    public TrancheMargin trancheId(Tranche tranche) {
        this.trancheId = tranche;
        return this;
    }

    public void setTrancheId(Tranche tranche) {
        this.trancheId = tranche;
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
        TrancheMargin trancheMargin = (TrancheMargin) o;
        if (trancheMargin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trancheMargin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrancheMargin{" +
            "id=" + getId() +
            ", trancheMarginId=" + getTrancheMarginId() +
            ", marginSpreadBps='" + getMarginSpreadBps() + "'" +
            ", floorPercentage=" + getFloorPercentage() +
            ", interestPaymentFrequency='" + getInterestPaymentFrequency() + "'" +
            ", totalPricingFlexBpsPa='" + getTotalPricingFlexBpsPa() + "'" +
            ", underwriteFeesBps=" + getUnderwriteFeesBps() +
            ", participationFees=" + getParticipationFees() +
            ", extensionFees6Months=" + getExtensionFees6Months() +
            ", extensionFees6To12Months=" + getExtensionFees6To12Months() +
            ", otherFlex='" + getOtherFlex() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
