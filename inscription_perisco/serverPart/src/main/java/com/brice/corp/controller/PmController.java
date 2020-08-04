package com.brice.corp.controller;

import com.brice.corp.model.DashboardPm;
import com.brice.corp.service.OltSlotPortPonService;
import com.brice.corp.service.PMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Controller des vues détaillées (PM/OLT)
 */
@RestController
@RequestMapping("/pm")
public class PmController {

    private static final Logger logger = LoggerFactory.getLogger(PmController.class);

    @Autowired
    private PMService pmService;

    @Autowired
    private OltSlotPortPonService oltSlotPortPonService;

    /**
     * Retourne le nombre PM ok et PM hs pour un OLT donné
     * @param id id de l'OLT
     * @return Liste du nombre de pmOk (0) et pmHs (1)
     */
    @RequestMapping(value = "/PmOkpmHsforOlt", method = RequestMethod.GET)
    public ResponseEntity<List<Integer>> getPmOkAndHs(@RequestParam String id)  {
        logger.debug("getPmOkAndHs for "+id);
        List<Integer> map = pmService.getPmOkAndHs(id);
        ResponseEntity resp = null;
        if(map.isEmpty()){
            resp =  new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }else{
            resp =  new ResponseEntity<>(map, HttpStatus.OK);
        }
        return resp;
    }

    // ------------------- Vue détaillée pour un OLT donné ---------------------------------------------

    /**
     * Retourne pour un OLT donné l'ensemble de ses PM selon deux catégorie : pmHs et pmOK
     * La Map est construit tel que clé = pmHs ou pmOk, valeur liste d'entités DashboardPm
     * @param id id de l'OLT
     * @return map de pmHs et de pmOk
     */
    @RequestMapping(value = "/PmListForOltId", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<DashboardPm>>> getPmListForOltId(@RequestParam String id) {
        logger.debug("getPmListForOltId for "+id);

        Map<String, List<DashboardPm>> map = oltSlotPortPonService.getPmOKHsByOlt(id);
        ResponseEntity resp = null;
        if(map.isEmpty()){
            resp =  new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }else{
            resp =  new ResponseEntity<>(map, HttpStatus.OK);
        }
        return resp;
    }


    // ------------------- Vue détaillée pour un PM donné ---------------------------------------------

    /**
     * Retourne pour un PM donné son état en fonction de son/ses OSP parent(s).
     * Un PM peut être OK pour l'OSP 1 tandis qu'il peut être HS pour l'OSP 2.
     * Cela dépend des ONT en faute qui eux ne peuvent avoir q'un unique OSP pour parent.
     * @param id id du PM
     * @return map de pmHs et pmOk
     */
    @RequestMapping(value = "/PmListForPmId", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<DashboardPm>>> getPmListForPmId(@RequestParam String id) {
        logger.debug("getPmListForPmId for "+id);
        Map<String, List<DashboardPm>> map = pmService.getDashboardPm(id);
        ResponseEntity resp = null;
        if(map.isEmpty()){
            resp =  new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }else{
            resp =  new ResponseEntity<>(map, HttpStatus.OK);
        }
        return resp;
    }




}
