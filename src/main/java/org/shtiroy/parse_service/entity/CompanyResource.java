package org.shtiroy.parse_service.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "working_data", name = "t_company_resource")
public class CompanyResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyResourceId;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company companyID;
    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "resource_id")
    private Resource resourceId;
    @Column(name = "check_ts")
    private Timestamp checkTs;

    public CompanyResource() {
    }

    public CompanyResource(Company companyID, Resource resourceId, Timestamp checkTs) {
        this.companyID = companyID;
        this.resourceId = resourceId;
        this.checkTs = checkTs;
    }

    public CompanyResource(Integer companyResourceId, Company companyID, Resource resourceId, Timestamp checkTs) {
        this.companyResourceId = companyResourceId;
        this.companyID = companyID;
        this.resourceId = resourceId;
        this.checkTs = checkTs;
    }

    public Integer getCompanyResourceId() {
        return companyResourceId;
    }

    public void setCompanyResourceId(Integer companyResourceId) {
        this.companyResourceId = companyResourceId;
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
