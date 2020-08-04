package com.bouyguestelecom.oss.controller;

import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.ONT;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;
import com.bouyguestelecom.oss.service.OltSlotPortPonService;
import com.bouyguestelecom.oss.service.PMService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PmController.class)
public class DetailViewApiControllerTest {

    private static Calendar calendar = Calendar.getInstance();
    private static Date rightNow = calendar.getTime();
    private static List<OltSlotPortPon> ospsByOlt = new ArrayList<>();
    private static List<OltSlotPortPon> ospsByPm = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OltSlotPortPonService oltSlotPortPonService;
    @MockBean
    private PMService pmService;

    @BeforeAll
    public static void initializeDB() {

        Calendar calendarMinusTenDays = Calendar.getInstance();
        calendarMinusTenDays.add(Calendar.DATE, -10);
        Date rightNowMinusTenDays = calendarMinusTenDays.getTime();

        // Ajout des OLTs
        OltSlotPortPon osp1 = new OltSlotPortPon("OLT0116", 5, 6);
        osp1.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");

        OltSlotPortPon osp2 = new OltSlotPortPon("OLT0116", 5, 3);
        osp2.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");

        OltSlotPortPon osp3 = new OltSlotPortPon("OLT0117", 5, 6);
        osp3.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");

        // Ajout des PMs
        // Ajout des PMs sous OLT0116-5-6
        PM pm1 = new PM("PM1", osp1);
        osp1.addPM(pm1);
        PM pm2 = new PM("PM2", osp1);
        osp1.addPM(pm2);
        PM pm3 = new PM("PM3", osp1);
        osp1.addPM(pm3);
        PM pm4 = new PM("PM4", osp1);
        osp1.addPM(pm4);

        // Ajout des PMs sous OLT0117-5-6
        PM pm5 = new PM("PM3", osp3);
        osp3.addPM(pm5);
        PM pm6 = new PM("PM4", osp3);
        osp3.addPM(pm6);
        PM pm7 = new PM("PM5", osp3);
        osp3.addPM(pm7);

        // Ajout des ONTs
        // Ajout des ONTs sous PM1
        // INACT depuis 10 jours
        ONT ont1 = new ONT(1, pm1);
        ont1.activateInact();
        ont1.setInactTimestamp(rightNowMinusTenDays);
        pm1.addOnt(ont1);

        // INACT du jour
        ONT ont2 = new ONT(2, pm1);
        ont2.activateInact();
        ont2.setInactTimestamp(rightNow);
        pm1.addOnt(ont2);

        // INACT du jour
        ONT ont3 = new ONT(3, pm1);
        ont3.activateInact();
        ont3.setInactTimestamp(rightNow);
        pm1.addOnt(ont3);

        // DG actif
        ONT ont4 = new ONT(4, pm1);
        ont4.setInactive();
        pm1.addOnt(ont4);

        // Ajout des ONTs sous PM2
        // INACT cleared
        ONT ont5 = new ONT(16, pm2);
        pm2.addOnt(ont5);

        //Ajout des ONTs sous PM3
        // INACT du jour
        ONT ont6 = new ONT(32, pm3);
        ont6.activateInact();
        ont6.setInactTimestamp(rightNow);
        pm3.addOnt(ont6);

        // INACT du jour
        ONT ont7 = new ONT(33, pm3);
        ont7.activateInact();
        ont7.setInactTimestamp(rightNow);
        pm3.addOnt(ont7);

        // INACT du jour
        ONT ont8 = new ONT(34, pm3);
        ont8.activateInact();
        ont8.setInactTimestamp(rightNow);
        pm3.addOnt(ont8);

        // Attention ici on est bien sous PM3 mais sous l'OLT0117
        ONT ont9 = new ONT(1, pm5); // ONT 1 sous PM3 different de ONT1 sous PM1
        pm5.addOnt(ont9);

        // Ajout des ONTs sous PM4
        ONT ont10 = new ONT(64, pm4);
        pm4.addOnt(ont10);
        ONT ont11 = new ONT(65, pm4);
        pm4.addOnt(ont11);

        // INACT du jour
        // Attention ici on est bien sous PM4 mais sous l'OLT0116
        ONT ont12 = new ONT(2, pm6);
        ont12.activateInact();
        ont12.setInactTimestamp(rightNow);
        pm6.addOnt(ont12);

        // Ajout des ONTs sous PM5
        //INACT depuis 10 jours
        ONT ont13 = new ONT(16, pm7);
        ont13.activateInact();
        ont13.setInactTimestamp(rightNowMinusTenDays);
        pm7.addOnt(ont13);

        // Ajout des IGs
        // IG minor PM sur PM1
        IG ig1 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM1\"", "{2}", osp1, "minor", rightNow);
        osp1.addIG(ig1);

        // IG SNOW PM cleared sur PM2
        IG ig2 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM2\"", "{1}", osp1, "major", rightNowMinusTenDays);
        ig2.setClearanceTimestamp(rightNow);
        osp1.addIG(ig2);

        // IG SNOW PM sur PM3
        IG ig3 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM3\"", "{1}", osp1, "major", rightNow);
        osp1.addIG(ig3);

        // testGetOltDetailView
        ospsByOlt.add(osp1);
        ospsByOlt.add(osp2);

        // testGetPmDetailView
        ospsByPm.add(osp1);
        ospsByPm.add(osp3);
    }

    @Test
    public void testGetOltDetailView() throws Exception {
        when(oltSlotPortPonService.findOspContainingOltId("OLT0116")).thenReturn(ospsByOlt);
        this.mockMvc.perform(get("/detailView/olt?pOltId=OLT0116")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pmHs", hasSize(2)))
                .andExpect(jsonPath("$.pmOk", hasSize(2)));

    }


}
