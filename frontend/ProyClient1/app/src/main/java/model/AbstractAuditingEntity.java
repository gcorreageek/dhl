package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gustavo on 29/11/16.
 */

public abstract class AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private String createdBy;
 
    private Date createdDate = new Date();
 
    private String lastModifiedBy;
 
    private Date lastModifiedDate = new Date();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}