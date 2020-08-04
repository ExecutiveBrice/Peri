package com.bouyguestelecom.oss.repositories;


import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Calsse d'encpsulation de la couche DAO OltSlotPortPon
 */
@Repository
public interface OltSlotPortPonRepository extends JpaRepository<OltSlotPortPon, String> {
    /**
     * Retourne l'OltSlotPortPon à partir de son id
     * @param ospId id de l'OSP
     * @return un OltSlotPortPon
     */
    OltSlotPortPon findByOspId(String ospId);

    /**
     * Retourne le nombre d'OSP contenus dans la table FTTH_OSP
     * @return nombre d'OSP
     */
    long countAll();

    /**
     * Retourne l'ensemble des OSP contenus dans la table FTTH_OSP
     * @return liste d'OltSlotPortPon
     */
    List<OltSlotPortPon> findAll();

    /**
     * Retourne l'ensemble des OSP appartenant à l'OLT renseigné
     * @param pOlt nom de l'OLT
     * @return liste d'OltSlotPortPon
     */
    List<OltSlotPortPon> findByOspIdContaining(String pOlt);


    /**
     * Retourne l'ensemble des OLT contenus dans la table FTTH_OSP
     * @return liste des noms des OLTs
     */
    @Query(value = "SELECT DISTINCT(SUBSTR(osp.ospId,1, 7)) FROM OltSlotPortPon osp")
    //@Query(value = "SELECT DISTINCT(SUBSTR(osp.ftth_osp_id,1, 7)) FROM FTTH_OSP_ID osp", nativeQuery = true) // pour les tests
    List<String> getDistinctOlt();

}
