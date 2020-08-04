package com.brice.corp.repositories;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.IgId;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe d'encapsulation de la couche DAO IG
 */
@Repository
public interface IGRepository extends JpaRepository <IG, IgId> {

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
