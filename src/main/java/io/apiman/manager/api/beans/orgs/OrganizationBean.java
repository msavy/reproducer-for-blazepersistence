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
package io.apiman.manager.api.beans.orgs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.apiman.manager.api.beans.clients.ClientBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An APIMan Organization.  This is an important top level entity in the APIMan
 * data model.  It contains the APIs, Plans, etc.
 *
 * @author eric.wittmann@redhat.com
 */
@Entity
@Table(name = "organizations")
@JsonInclude(Include.NON_NULL)
public class OrganizationBean implements Serializable {

    private static final long serialVersionUID = -506427154633682906L;

    @Id
    @Column(nullable=false)
    private String id;

    @OneToMany(fetch=FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.REMOVE}, mappedBy="organization")
    @JsonIgnore
    private Set<ClientBean> clients = new LinkedHashSet<>();

    /**
     * Constructor.
     */
    public OrganizationBean() {
    }

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

    public Set<ClientBean> getClients() {
        return clients;
    }

    public void setClients(Set<ClientBean> clientSet) {
        this.clients = clientSet;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrganizationBean other = (OrganizationBean) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
