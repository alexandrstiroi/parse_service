package org.shtiroy.parse_service.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "working_data", name = "t_company_json")
public class CompanyJSON {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyJsonId;
    @Column(name = "idno")
    private String idno;
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Column(name = "company_data")
    private String companyData;
    @Column(name = "resource")
    private String resource;

    public CompanyJSON() {
    }

    public CompanyJSON(String idno, Timestamp createTs, String companyData, String resource) {
        this.idno = idno;
        this.createTs = createTs;
        this.companyData = companyData;
        this.resource = resource;
    }

    public Integer getCompanyJsonId() {
        return companyJsonId;
    }

    public void setCompanyJsonId(Integer companyJsonId) {
        this.companyJsonId = companyJsonId;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public String getCompanyData() {
        return companyData;
    }

    public void setCompanyData(String companyData) {
        this.companyData = companyData;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "idno=" + idno + ", resource=" + resource;
    }
}
