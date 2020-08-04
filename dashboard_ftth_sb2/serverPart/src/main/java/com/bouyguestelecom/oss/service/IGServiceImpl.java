package com.bouyguestelecom.oss.service;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.repositories.IGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe implémentant les services IG
 */
@Service("IGService")
@Transactional
public class IGServiceImpl implements IGService {

    @Autowired
    private IGRepository igRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer countByIdAlarmFullnameContainingAndOltSlotPortPon(String pIdPm, OltSlotPortPon pOsp) {
        return igRepository.countByIdAlarmFullnameContainingAndOltSlotPortPon(pIdPm, pOsp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IG> findByOltSlotPortPon(OltSlotPortPon pOsp) {
        return igRepository.findByOltSlotPortPon(pOsp);
    }
}
