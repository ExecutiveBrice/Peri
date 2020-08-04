package com.brice.corp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "TRANS_ENTITIES")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "TRANS_ENTITY.countAll", query = "SELECT COUNT(x) FROM TRANS_ENTITY x"),
        @NamedQuery(name = "TRANS_ENTITY.countById", query = "SELECT COUNT(x) FROM TRANS_ENTITY x WHERE x.id = ?1 ")
})
public class TRANS_ENTITY implements java.io.Serializable {
    @Id
    @SequenceGenerator(name = "pk_TE_seq", sequenceName = "TRANS_ENTITIES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_TE_seq")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "PARENT_ID", nullable = false)
    private String parentId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "T_CLASS", nullable = false)
    private String tClass;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENTITY_ID")
    private Set<TRANS_ATTRIBUTE> attributeList;

    /**
     * Constructeur par défaut pour Hibernate
     */
    public TRANS_ENTITY() {
    }


    /**
     * Constructeur
     *
     * @param entityId ID de l'entité
     */
    public TRANS_ENTITY(final String entityId) {
        this.id = entityId;
        attributeList = new HashSet<>();

    }

    public String getEntityId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String gettClass() {
        return tClass;
    }

    public Set<TRANS_ATTRIBUTE> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(Set<TRANS_ATTRIBUTE> attributeList) {
        this.attributeList = attributeList;
    }

    public void setEntityId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void settClass(String tClass) {
        this.tClass = tClass;
    }



    @Override
    public boolean equals(Object pPmObject) {
        if (pPmObject == null) return false;
        if (!(pPmObject instanceof TRANS_ENTITY)) return false;

        final TRANS_ENTITY lPM = (TRANS_ENTITY) pPmObject;
        return this.id.equalsIgnoreCase(lPM.getEntityId());
    }

    @Override
    public String toString() {
        return "TRANS_ENTITY[name: " + name + " - Number of Attributes: " + getAttributeList().size() + "]";
    }
}
