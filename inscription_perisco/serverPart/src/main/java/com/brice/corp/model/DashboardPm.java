package com.brice.corp.model;

import java.util.Date;

/**
 * Classe représentant un PM du point de vue du Dashboard
 */
public class DashboardPm {

    /**
     * Région de l'OLT parent du PM
     */
    private String region;

    /**
     * Id du PM
     */
    private String idPm;

    /**
     * Partenaire adduction du PM
     */
    private String partenaireAdduction;

    /**
     * Date de création de l'IG impactant le PM
     */
    private Date creationDate;

    /**
     * Nombre de clients (ONT) sous le PM
     */
    private int nbClients;

    /**
     * Nombre de clients du PM impactés par l'IG
     */
    private int nbClientsHs;

    /**
     * Triplet OltSlotPortPon parent du PM
     */
    private String osp;

    /**
     * GCR de l'IG impactant le PM
     */
    private String gcr;

    /**
     * TT de l'IG impactant le PM
     */
    private String tt;

    /**
     * Date de clear de l'IG impactant le PM
     */
    private String resolutionDate;

    // CONSTRUCTEURS //
    public DashboardPm() {}
    public DashboardPm(final String pRegion, final String pIdPm, final String pPartenanireAdduction, final Date pCreationDate,
              final int pNbClients, final int pNbClientsHs, final String pOsp, final String pGcr, final String pTt,
              final String pResolutionDate) {
        this.region = pRegion;
        this.idPm = pIdPm;
        this.partenaireAdduction = pPartenanireAdduction;
        this.creationDate = pCreationDate;
        this.nbClients = pNbClients;
        this.nbClientsHs = pNbClientsHs;
        this.osp = pOsp;
        this.gcr = pGcr;
        this.tt = pTt;
        this.resolutionDate = pResolutionDate;
    }

    // GETTERS AND SETTERS //

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIdPm() {
        return idPm;
    }

    public void setIdPm(String idPm) {
        this.idPm = idPm;
    }

    public String getPartenaireAdduction() {
        return partenaireAdduction;
    }

    public void setPartenaireAdduction(String partenaireAdduction) {
        this.partenaireAdduction = partenaireAdduction;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public int getNbClientsHs() {
        return nbClientsHs;
    }

    public void setNbClientsHs(int nbClientsHs) {
        this.nbClientsHs = nbClientsHs;
    }

    public String getOsp() {
        return osp;
    }

    public void setOsp(String osp) {
        this.osp = osp;
    }

    public String getGcr() {
        return gcr;
    }

    public void setGcr(String gcr) {
        this.gcr = gcr;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(String resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    @Override
    public String toString() {
        return "OLT[Region: " + this.getRegion()
                + " - IdPm: " + this.getIdPm()
                + " - PartenaireAdduction: " + this.getPartenaireAdduction()
                + " - CreationDate: " + this.getCreationDate()
                + " - NbClients: " + this.getNbClients()
                + " - NbClientsHs: " + this.getNbClientsHs()
                + " - OSP: " + this.getOsp()
                + " - GCR: " + this.getGcr()
                + " - TT: " + this.getTt()
                + " - ResolutionDate: " + this.getResolutionDate()
                + "]";
    }
}
