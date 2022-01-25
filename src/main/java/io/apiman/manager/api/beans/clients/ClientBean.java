/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apiman.manager.api.beans.clients;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.apiman.manager.api.beans.orgs.OrganizationBasedCompositeId;
import io.apiman.manager.api.beans.orgs.OrganizationBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Models a client.
 *
 * @author eric.wittmann@redhat.com
 */
@Entity
@Table(name = "clients")
@IdClass(OrganizationBasedCompositeId.class)
@JsonInclude(Include.NON_NULL)
public class ClientBean implements Serializable, Cloneable {

    private static final long serialVersionUID = -197129444021040365L;

    @Id
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="organization_id", referencedColumnName="id")
    })
    private OrganizationBean organization;

    @Id
    @Column(name="id", nullable=false)
    private String id;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval=true, fetch=FetchType.LAZY, mappedBy="client")
    private Set<ClientVersionBean> clientVersions = new LinkedHashSet<>();

    public ClientBean() {}

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the organization
     */
    public OrganizationBean getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(OrganizationBean organization) {
        this.organization = organization;
    }


    public Set<ClientVersionBean> getClientVersions() {
        return clientVersions;
    }

    public ClientBean setClientVersions(
        Set<ClientVersionBean> clientVersionSet) {
        this.clientVersions = clientVersionSet;
        return this;
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
