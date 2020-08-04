package com.bouyguestelecom.oss.service;


import com.bouyguestelecom.oss.ftthig.common.entities.IG;

import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.bouyguestelecom.oss.model.DashboardOlt;
import com.bouyguestelecom.oss.model.DashboardPm;
import com.bouyguestelecom.oss.model.TRANS_ATTRIBUTE;
import com.bouyguestelecom.oss.model.TRANS_ENTITY;
import com.bouyguestelecom.oss.repositories.OltSlotPortPonRepository;
import com.bouyguestelecom.oss.repositories.TransEntitiesRepository;

import com.bouyguestelecom.oss.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Classe implémentant les services sur les OSPs
 */
@Service("oltSlotPortPonService")
@Transactional
public class OltSlotPortPonServiceImpl implements OltSlotPortPonService {

    public static final Logger logger = LoggerFactory.getLogger(OltSlotPortPonServiceImpl.class);

    private List<DashboardOlt> oltsCache = new ArrayList<>();
    @Autowired
    private OltSlotPortPonRepository oltSlotPortPonRepository;

    @Autowired
    private TransEntitiesRepository transEntitiesRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public OltSlotPortPon findByOspId(String ospId) {
        return oltSlotPortPonRepository.findByOspId(ospId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countAll() {
        return oltSlotPortPonRepository.countAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OltSlotPortPon> findAll() {
        return oltSlotPortPonRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DashboardOlt> getDistinctOlt() {
        return oltsCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillCache() {
        List<String> distinctsOlts = oltSlotPortPonRepository.getDistinctOlt();
        oltsCache.clear();
        for (String oltId : distinctsOlts) {
            DashboardOlt olt = new DashboardOlt(null, null, null, oltId, -1, -1);
            getAttributes(olt);
            oltsCache.add(olt);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OltSlotPortPon> findOspContainingOltId(String pOlt) {
        return oltSlotPortPonRepository.findByOspIdContaining(pOlt);
    }


    /**
     * Set les attributs utilisé par dashboard à partir de l'entité TRANS
     * @param olt
     */
    public void getAttributes(DashboardOlt olt) {
       TRANS_ENTITY oltEntity = transEntitiesRepository.findByName(olt.getId());
        if(oltEntity != null){
           olt.setRegion(Common.getRegionFromEntity(oltEntity));


        if(oltEntity.getAttributeList() != null) {
            for (TRANS_ATTRIBUTE attribute : oltEntity.getAttributeList()) {

                if (attribute.getName().equalsIgnoreCase("siteRef")) {
                    String[] siteNameParts = attribute.getValue().split(" ");

                    olt.setSiteNro(siteNameParts[siteNameParts.length-1]);
                }
                if (attribute.getName().equalsIgnoreCase("operatorTiers")) {
                    olt.setPartenaire(attribute.getValue());
                }
            }
        }else{
            logger.debug("getAttributes getAttributes none ");
        }
        }else{
            logger.debug("getAttributes entity not exist ");
        }

    }



    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<DashboardPm>> getPmOKHsByOlt(String pOltId) {
        Map<String, List<DashboardPm>> pmsOkAndHs = new HashMap<>();
        List<DashboardPm> pmOk = new ArrayList<>();
        List<DashboardPm> pmHs = new ArrayList<>();
        List<OltSlotPortPon> osps = findOspContainingOltId(pOltId);

        for(OltSlotPortPon osp :osps){
            Set<PM> pms = osp.getPmList();
            Set<IG> igs = osp.getIgList();
            for (PM pm : pms) {
                DashboardPm dashboardPm = new DashboardPm();
                dashboardPm.setOsp(osp.getOspId());
                dashboardPm.setIdPm(pm.getId());
                dashboardPm.setNbClients(pm.getNumberOfOnt());

                dashboardPm.setPartenaireAdduction(pm.getPartenaireAdd());
                TRANS_ENTITY oltEntity = transEntitiesRepository.findByName(pOltId);
                if(oltEntity != null) {
                    dashboardPm.setRegion(Common.getRegionFromEntity(oltEntity));
                }
                IG ig = Common.getIg(pm, igs);
                if (ig == null) {
                    dashboardPm.setNbClientsHs(0);
                    pmOk.add(dashboardPm);
                } else {
                    dashboardPm.setCreationDate(ig.getCreationTimestamp());
                    dashboardPm.setGcr(ig.getGcr());
                    dashboardPm.setTt(ig.getTt());
                    // Cas IG PONLOSS ==> tous les clients sont HS
                    if (ig.getId().getAlarmFullname().contains("olt")) {
                        dashboardPm.setNbClientsHs(dashboardPm.getNbClients());

                        // l'IG PONLOSS est cleared
                        if (ig.getClearanceTimestamp() != null) {
                            dashboardPm.setResolutionDate(ig.getClearanceTimestamp().toString());
                            pmOk.add(dashboardPm);
                        } else {
                            pmHs.add(dashboardPm);
                        }

                        // Cas IG PM ==>
                    } else {
                        dashboardPm.setNbClientsHs(Common.getNbOntWithInact(pm.getOntList()));
                        // l'IG PM est cleared
                        if (ig.getClearanceTimestamp() != null) {
                            dashboardPm.setResolutionDate(ig.getClearanceTimestamp().toString());
                            pmOk.add(dashboardPm);
                        } else {
                            pmHs.add(dashboardPm);
                        }

                    }

                }
            }
        }

        pmsOkAndHs.put("pmOk",pmOk);
        pmsOkAndHs.put("pmHs",pmHs);
        return pmsOkAndHs;
    }


}
