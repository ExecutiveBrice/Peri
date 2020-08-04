package com.brice.corp.service;

import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.brice.corp.model.DashboardPm;

import java.util.List;
import java.util.Map;

/**
 * Classe des services portant sur les PM
 */
public interface PMService {

    /**
     * Retourne l'ensemble des PM associés à l'OSP renseigné
     * @param pOsp OltSlotPortPon
     * @return liste de PM
     */
    List<PM> findByOltSlotPortPon(OltSlotPortPon pOsp);


    /**
     * Retourne les contage des pmHS et pmOK pour l'olt
     * @param pOltId
     * @return 2 integer OK and HS PM
     */
    List<Integer> getPmOkAndHs(String pOltId);

    /**
     * Retourne les IG pour le pmdédié sous la forme d'un object front
     * @param pPmId
     * @return
     */
    Map<String, List<DashboardPm>> getDashboardPm(String pPmId);
}
