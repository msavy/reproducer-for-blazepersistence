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

import javax.persistence.*;
import java.io.Serializable;

/**
 * Models a single version of a client "impl".  Every client in
 * APIMan has basic meta-data stored in {@link ClientBean}.  All
 * other specifics of the client, such as endpoint information
 * and configured policies are associated with a particular version
 * of that client.  This class represents that version.
 *
 * @author eric.wittmann@redhat.com
 */
@Entity
@Table(name = "client_versions",
       uniqueConstraints = {
               @UniqueConstraint(columnNames = { "client_id", "client_org_id", "version" })})
@JsonInclude(Include.NON_NULL)
public class ClientVersionBean implements Serializable, Cloneable {

    private static final long serialVersionUID = -2218697175049442690L;

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="client_id", referencedColumnName="id"),
        @JoinColumn(name="client_org_id", referencedColumnName="organization_id")
    })
    private ClientBean client;

    @Column(updatable=false, nullable=false)
    private String version;

    /**
     * Constructor.
     */
    public ClientVersionBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientBean getClient() {
        return client;
    }

    public void setClient(ClientBean client) {
        this.client = client;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        ClientVersionBean other = (ClientVersionBean) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
