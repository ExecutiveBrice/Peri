package com.bouyguestelecom.oss.service;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;

import java.util.List;

/**
 * Classe des services portant sur les IG
 */
public interface IGService {

    /**
     * Retourne l'ensemble des IG concernant le PM associé à l'OSP renseigné
     * @param pIdPm id du PM
     * @param pOsp OltSlotPortPon parent du PM
     * @return liste d'IG
     */
    Integer countByIdAlarmFullnameContainingAndOltSlotPortPon(String pIdPm, OltSlotPortPon pOsp);

    /**
     * Retourne l'ensemble des IG porté par l'OltSlotPortPon
     * @param pOsp OltSlotPortPon
     * @return liste d'IG
     */
    List<IG> findByOltSlotPortPon(OltSlotPortPon pOsp);
}
