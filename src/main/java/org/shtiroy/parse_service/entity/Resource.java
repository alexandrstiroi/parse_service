package org.shtiroy.parse_service.entity;

import javax.persistence.*;

@Entity
@Table(schema = "working_data", name = "t_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;

    @Column(name = "resource_url")
    private String resourceUrl;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_is_active")
    private Boolean resourceIsActive;

    public Resource() {
    }

    public Resource(String resourceUrl, String resourceName, Boolean resourceIsActive) {
        this.resourceUrl = resourceUrl;
        this.resourceName = resourceName;
        this.resourceIsActive = resourceIsActive;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Boolean getResourceIsActive() {
        return resourceIsActive;
    }

    public void setResourceIsActive(Boolean resourceIsActive) {
        this.resourceIsActive = resourceIsActive;
    }

    @Override
    public String toString() {
        return  resourceName + ", is Active=" + resourceIsActive;
    }
}
