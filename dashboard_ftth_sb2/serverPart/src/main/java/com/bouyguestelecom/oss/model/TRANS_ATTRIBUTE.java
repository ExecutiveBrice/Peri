package com.bouyguestelecom.oss.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;


@Entity
@Table(name = "TRANS_ATTRIBUTES")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "TRANS_ATTRIBUTE.countAll", query = "SELECT COUNT(x) FROM TRANS_ATTRIBUTE x"),
        @NamedQuery(name = "TRANS_ATTRIBUTE.countById", query = "SELECT COUNT(x) FROM TRANS_ATTRIBUTE x WHERE x.id = ?1 ")
})
public class TRANS_ATTRIBUTE implements java.io.Serializable {
    @Id
    @SequenceGenerator(name = "pk_TA_seq", sequenceName = "TRANS_ATTRIBUTES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_TA_seq")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "ENTITY_ID", nullable = false)
    private String entityId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private String value;

    @Column(name = "T_ID", nullable = false)
    private String tId;

    @Column(name = "T_CLASS", nullable = false)
    private String tClass;


    /**
     * Constructeur par défaut pour Hibernate
     */
    public TRANS_ATTRIBUTE() {
    }


    /**
     * Constructeur
     *
     * @param id ID de l'entité
     */
    public TRANS_ATTRIBUTE(final String id) {
        this.id = id;

    }

    public String getEntityId() {
        return entityId;
    }

    public String getId() {
        return id;
    }

    public String gettId() {
        return tId;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String gettClass() {
        return tClass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void settClass(String tClass) {
        this.tClass = tClass;
    }



    @Override
    public boolean equals(Object pPmObject) {
        if (pPmObject == null) return false;
        if (!(pPmObject instanceof TRANS_ATTRIBUTE)) return false;

        final TRANS_ATTRIBUTE lPM = (TRANS_ATTRIBUTE) pPmObject;
        return this.id.equalsIgnoreCase(lPM.getId());
    }

    @Override
    public String toString() {
        return "TRANS_ATTRIBUTE[id: " + id  + "]";
    }
}
