package com.bouyguestelecom.oss.controller;


import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.model.DashboardOlt;
import com.bouyguestelecom.oss.model.DashboardPm;
import com.bouyguestelecom.oss.service.OltSlotPortPonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * Controller de la vue globale
 */
@RestController
@RequestMapping("/olt")
public class OltController {

    public static final Logger logger = LoggerFactory.getLogger(OltController.class);

    @Autowired
    private OltSlotPortPonService oltSlotPortPonService;





    /**
     * Retourne l'esemble des OLTs présents en bdd
     * @return liste d'OLTs
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<DashboardOlt>> getAllOlts() {
        logger.debug("getAllOlts ");
        List<DashboardOlt> distinctsOlts = oltSlotPortPonService.getDistinctOlt();


        if(distinctsOlts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(distinctsOlts, HttpStatus.OK);
    }



    @RequestMapping(value = "/osps", method = RequestMethod.GET)
    public ResponseEntity<DashboardOlt> getOspContainingOltId(@RequestParam String id) {
        logger.debug("getOspContainingOltId "+id);

        List<OltSlotPortPon> olts = oltSlotPortPonService.findOspContainingOltId(id);
        ResponseEntity resp = null;
        if(olts.isEmpty()){
            resp =  new ResponseEntity<>(olts, HttpStatus.NO_CONTENT);
        }else{
            resp =  new ResponseEntity<>(olts, HttpStatus.OK);
        }
        return resp;
    }




}
