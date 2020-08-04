package com.brice.corp.controller;

import com.brice.corp.service.OltSlotPortPonService;
import com.brice.corp.util.Resynchronisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller de la resynchronisation
 */
@RestController
@RequestMapping("/resynch")
public class ResynchronisationController {

    @Autowired
    private OltSlotPortPonService oltSlotPortPonService;


    /**
     * Mise à jour du statut de la resynchronisation et met à jour le cache des OLTs
     * @param resynch booléen true si la resynchronisation est en cours, false si elle est terminée
     * @param srBuilder uri à construire
     * @return l'état de resynchronisation
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> setResynch(@RequestBody boolean resynch, UriComponentsBuilder srBuilder) {
        Resynchronisation resynchronisation = Resynchronisation.getInstance();
        if(resynchronisation.isResynch() == resynch) {
            // pas ure que ce soit le bon retour, mais en gros je veux dire que l'état de la resynch est déjà celui qu'on a
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        if(!resynch){
            oltSlotPortPonService.fillCache();
        }
        resynchronisation.setResynch(resynch);


        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(srBuilder.path("/resynch/setresych/{resycnh}").buildAndExpand(resynchronisation.isResynch()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    /**
     * Retourne l'état de resynchronisation
     * @return true si une resynchronisation est en cours, false sinon
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getResynch() {
        Resynchronisation resynchronisation = Resynchronisation.getInstance();

        return new ResponseEntity<>(resynchronisation, HttpStatus.OK);
    }

}
