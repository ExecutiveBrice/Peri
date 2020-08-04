package com.bouyguestelecom.oss.util;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.ONT;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.bouyguestelecom.oss.model.TRANS_ENTITY;

import java.util.Set;

public class Common {


    /**
     * Parse le full name de l'entité TRANS pour obtenir la région de cette entité
     * @param oltEntity
     * @return the short region name
     */
    public static String getRegionFromEntity(TRANS_ENTITY oltEntity){
        String regionName = "";
        if (oltEntity.getFullName() != null && !oltEntity.getFullName().isEmpty()) {
            String[] fullNameParts = oltEntity.getFullName().split(" ");
            regionName = fullNameParts[1].replaceAll(".OSS11.TRANS.", "");
        }
        return regionName;
    }



    /**
     * Renvoi l'IG qui concerne le PM en paramètre
     * @param pm le PM sur lequel on souhaite savoir s'il y a un IG ou non
     * @param igs la liste d'IG liée à l'OSP parent du PM
     * @return
     */
    public static IG getIg(PM pm, Set<IG> igs) {
        for (IG ig : igs) {
            if (ig.getId().getAlarmFullname().contains(pm.getId())) {
                return ig;
            }
        }
        return null;
    }

    /**
     * Renvoie le nombre d'ONT avec INACT sans DG
     *
     * @param onts la liste des ONTs à vérifier
     * @return le nombre de client HS
     */
    public static int getNbOntWithInact(Set<ONT> onts) {
        int nbClientsHs = 0;
        for (ONT ont : onts) {
            if (ont.isInact() && ont.isActive()) {
                nbClientsHs++;
            }
        }
        return nbClientsHs;
    }
}
