package com.brice.corp.repositories;

import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe d'encapsulation de la couche DAO PM
 */
@Repository
public interface PMRepository extends JpaRepository<PM, Integer> {

    /**
     * Retourne la liste des PM associés à l'OPS
     * @param pOsp l'OltslotPortPon
     * @return liste de PM
     */
    List<PM> findByOltSlotPortPon(OltSlotPortPon pOsp);


    /**
     * Retourne le total de PM par OLT
     * @param pOlt
     * @return nombre de pm
     */
    Integer countByOltSlotPortPonOspIdContaining(String pOlt);


    /**
     * Retourne la liste des OSPs parents d'un PM donné
     * @param pPmId id du PM
     * @return liste d'OSPs
     */
    @Query(value = "SELECT pm.oltSlotPortPon FROM PM pm WHERE pm.pmId = :pmId")
    List<OltSlotPortPon> findOltSlotPortPonByPmId(@Param("pmId") String pPmId);


    /**
     * Retourne le nombre de pm HS
     * cherche dans la table des IG les OLT et les PM qui ont leur OLT dans l'OSPName et remonte les pm
     * @param pOltName
     * @return le comptage des pm distinct HS
     */
    @Query(value ="SELECT count(DISTINCT(pm.id_pm)) FROM ftth_pm pm  INNER JOIN FTTH_EVENTS event ON event.ftth_osp_id = pm.ftth_osp_id AND pm.ftth_osp_id LIKE '%' || :oltName || '%'  WHERE event.clearance_timestamp is null and event.alarm_fullname LIKE '%' || pm.id_pm || '%' or event.alarm_fullname LIKE '%'  || :oltName   ||'%'"
            , nativeQuery = true)
    Integer findPmHsByOltName(@Param("oltName")String pOltName);


}
