package org.shtiroy.parse_service.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "working_data", name = "t_verified_company")
public class VerifiedCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer verifiedId;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company companyID;
    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "resource_id")
    private Resource resourceId;
    @Column(name = "check_ts")
    private Timestamp checkTs;

    public VerifiedCompany() {
    }

    public VerifiedCompany(Company companyID, Resource resourceId, Timestamp checkTs) {
        this.companyID = companyID;
        this.resourceId = resourceId;
        this.checkTs = checkTs;
    }

    public VerifiedCompany(Integer verifiedId, Company companyID, Resource resourceId, Timestamp checkTs) {
        this.verifiedId = verifiedId;
        this.companyID = companyID;
        this.resourceId = resourceId;
        this.checkTs = checkTs;
    }

    public Integer getVerifiedId() {
        return verifiedId;
    }

    public void setVerifiedId(Integer verifiedId) {
        this.verifiedId = verifiedId;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    public Resource getResourceId() {
        return resourceId;
    }

    public void setResourceId(Resource resourceId) {
        this.resourceId = resourceId;
    }

    public Timestamp getCheckTs() {
        return checkTs;
    }

    public void setCheckTs(Timestamp checkTs) {
        this.checkTs = checkTs;
    }
}
