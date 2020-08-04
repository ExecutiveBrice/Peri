package com.brice.corp.service;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.brice.corp.model.DashboardPm;
import com.brice.corp.model.TRANS_ENTITY;
import com.brice.corp.repositories.PMRepository;
import com.brice.corp.repositories.TransEntitiesRepository;
import com.brice.corp.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Classe implémentant les services portant sur les PM
 */
@Service("PMService")
@Transactional
public class PMServiceImpl implements PMService {

    public static final Logger logger = LoggerFactory.getLogger(PMServiceImpl.class);

    @Autowired
    private PMRepository pmRepository;

    @Autowired
    private OltSlotPortPonService oltSlotPortPonService;


    @Autowired
    private TransEntitiesRepository transEntitiesRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PM> findByOltSlotPortPon(OltSlotPortPon pOsp) {
        return pmRepository.findByOltSlotPortPon(pOsp);
    }


    public List<Integer> getPmOkAndHs(String pOltId) {
        List<Integer> pmsOkAndHs = new ArrayList<>();
        Integer pms = pmRepository.countByOltSlotPortPonOspIdContaining(pOltId);
        Integer pmHs = pmRepository.findPmHsByOltName(pOltId);
        Integer pmOk = pms - pmHs;
        pmsOkAndHs.add(pmOk);
        pmsOkAndHs.add(pmHs);
        return pmsOkAndHs;
    }

    /**
     * Récupère les PM HS et OK du point de vue du PM
     * @param pPmId
     * @return 2 lists of PM
     */
    public Map<String, List<DashboardPm>> getDashboardPm(String pPmId) {
        Map<String, List<DashboardPm>> pmsOkAndHs = new HashMap<>();
        List<DashboardPm> pmOk = new ArrayList<>();
        List<DashboardPm> pmHs = new ArrayList<>();

        List<OltSlotPortPon> osps = pmRepository.findOltSlotPortPonByPmId(pPmId);

        for(OltSlotPortPon osp :osps){
            Set<IG> igs = osp.getIgList();
            Set<PM> pms = osp.getPmList();

            for (PM pm : pms) {
                DashboardPm dashboardPm = new DashboardPm();
                dashboardPm.setOsp(osp.getOspId());
                dashboardPm.setIdPm(pm.getId());
                dashboardPm.setNbClients(pm.getNumberOfOnt());

                dashboardPm.setPartenaireAdduction(pm.getPartenaireAdd());
                TRANS_ENTITY oltEntity = transEntitiesRepository.findByName(osp.getOltName());
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
