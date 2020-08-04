package com.brice.corp.repositories;


import com.brice.corp.model.TRANS_ENTITY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Classe d'encapsulation de la couche DAO PM
 */
@Repository
public interface TransEntitiesRepository extends JpaRepository<TRANS_ENTITY, Integer> {


    /**
     * Retourne l'entité TRANS contenu dans la table TRANS_ENTITIES
     * @param entityId id de l'entitée
     * @return l'entité complete avec ses attributs
     */
    TRANS_ENTITY findByName(String entityId);
}
