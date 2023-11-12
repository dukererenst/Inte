/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernest.inte.model.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ernest
 */

@Setter
@Getter
@MappedSuperclass
public class EntityModel extends Object implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "updated")
    private boolean updated;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    public EntityModel() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + (this.deleted ? 1 : 0);
        hash = 19 * hash + (this.updated ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.createdOn);
        hash = 19 * hash + Objects.hashCode(this.createdBy);
        hash = 19 * hash + Objects.hashCode(this.updatedOn);
        hash = 19 * hash + Objects.hashCode(this.updatedBy);
        hash = 19 * hash + Objects.hashCode(this.deletedBy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntityModel other = (EntityModel) obj;
        if (this.deleted != other.deleted) {
            return false;
        }
        if (this.updated != other.updated) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.deletedBy, other.deletedBy)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        return Objects.equals(this.updatedOn, other.updatedOn);
    }

}
