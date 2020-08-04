package com.brice.corp.service;

import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.brice.corp.model.DashboardOlt;
import com.brice.corp.model.DashboardPm;

import java.util.List;
import java.util.Map;

/**
 * Classe des services portant sur les OPs
 */
public interface OltSlotPortPonService {

    /**
     * Retourne sur l'OSP en fonction de son identifiant
     * @param ospId id de l'OSP
     * @return un OSP
     */
    OltSlotPortPon findByOspId(String ospId);

    /**
     * Retroune le nombre d'OltSlotPortPon contenus dans la table FTTH_OSP
     * @return
     */
    long countAll();

    /**
     * Retourne l'ensemble des OltSlotPortPon contenus dans la table FTTH_OSP
     * @return
     */
    List<OltSlotPortPon> findAll();

    /**
     * Retourne l'ensemble des OLT à partir des OltSlotPortPon de la table FTTH_OSP
     * @return liste des noms des OLTs
     */
    List<DashboardOlt> getDistinctOlt();

    /**
     * Retourne l'ensemble des OSP appartenant à l'OLT renseigné
     * @param pOlt nom de l'OLT
     * @return liste d'OSPs
     */
    List<OltSlotPortPon> findOspContainingOltId(String pOlt);


    /**
     * Récupère les PM HS et OK du point de vue de l'OLT
     * @param pOltId
     * @return 2 lists of PM
     */
    Map<String, List<DashboardPm>> getPmOKHsByOlt(String pOltId);

    /**
     * Vide et recharge le cache de la liste des OLTs
     */
    void fillCache();
}
