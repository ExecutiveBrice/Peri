package com.brice.corp.model;

/**
 * Classe représentant un OLT du point de vue du Dashboard
 * @author CPINSARD
 */
public class DashboardOlt {

    /**
     * Région de l'OLT
     */
    private String region;

    /**
     * Site NRO de l'OLT
     */
    private String siteNro;

    /**
     * Opérateur tiers de l'OLT
     */
    private String partenaire;

    /**
     * Id de l'OLT
     */
    private String id;

    /**
     * Nombre de PM HS de l'OLT
     */
    private int pmHs;

    /**
     * Nombre de PM OK de l'OLT
     */
    private int pmOk;

    // CONSTRUCTEURS //
    public DashboardOlt() {}
    public DashboardOlt(final String pRegion, final String pSiteNro, final String pPartenaire, final String pId, final int pPmHs, final int pPmOk) {
        this.region = pRegion;
        this.siteNro = pSiteNro;
        this.partenaire = pPartenaire;
        this.id = pId;
        this.pmHs = pPmHs;
        this.pmOk = pPmOk;
    }

    // GETTERS ET SETTERS //

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSiteNro() {
        return siteNro;
    }

    public void setSiteNro(String siteNro) {
        this.siteNro = siteNro;
    }

    public String getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(String partenaire) {
        this.partenaire = partenaire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPmHs() {
        return pmHs;
    }

    public void setPmHs(int pmHs) {
        this.pmHs = pmHs;
    }

    public int getPmOk() {
        return pmOk;
    }

    public void setPmOk(int pmOk) {
        this.pmOk = pmOk;
    }

    public String toString() {
        return "OLT[Region: " + this.getRegion()
                + " - SiteNro: " + this.getSiteNro()
                + " - Partenaire: " + this.getPartenaire()
                + " - Id: " + this.getId()
                + " - PmHs: " + this.getPmHs()
                + " - PmOk: " + this.getPmOk()
                + "]";
    }
}
