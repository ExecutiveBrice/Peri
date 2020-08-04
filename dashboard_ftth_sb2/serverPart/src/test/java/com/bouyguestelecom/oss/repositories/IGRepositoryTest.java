package com.bouyguestelecom.oss.repositories;

import com.bouyguestelecom.oss.configuration.JpaConfiguration;
import com.bouyguestelecom.oss.ftthig.common.entities.IG;
import com.bouyguestelecom.oss.ftthig.common.entities.ONT;
import com.bouyguestelecom.oss.ftthig.common.entities.OltSlotPortPon;
import com.bouyguestelecom.oss.ftthig.common.entities.PM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {JpaConfiguration.class})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IGRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private IGRepository igRepository;

    private static boolean initialized = false;

    private static Calendar calendar = Calendar.getInstance();
    private static Date rightNow = calendar.getTime();

    @BeforeEach
    public void initializeDB() {
        if (!initialized) {
            Calendar calendarMinusTenDays = Calendar.getInstance();
            calendarMinusTenDays.add(Calendar.DATE, -10);
            Date rightNowMinusTenDays = calendarMinusTenDays.getTime();

            // Ajout des OLTs
            OltSlotPortPon osp1 = new OltSlotPortPon("OLT0116", 5 ,6);
            osp1.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");
            testEntityManager.persist(osp1);

            OltSlotPortPon osp2 = new OltSlotPortPon("OLT0116", 5 ,3);
            osp2.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");
            testEntityManager.persist(osp2);

            OltSlotPortPon osp3 = new OltSlotPortPon("OLT0117", 5 ,6);
            osp3.setBatchId("f3b585bf-ec8b-4f22-8d86-32964d4a61a3");
            testEntityManager.persist(osp3);

            // Ajout des PMs
            // Ajout des PMs sous OLT0116-5-6
            PM pm1 = new PM("PM1", osp1);
            testEntityManager.persist(pm1);
            PM pm2 = new PM("PM2", osp1);
            testEntityManager.persist(pm2);
            PM pm3 = new PM("PM3", osp1);
            testEntityManager.persist(pm3);
            PM pm4 = new PM("PM4", osp1);
            testEntityManager.persist(pm4);

            // Ajout des PMs sous OLT0117-5-6
            PM pm5 = new PM("PM3", osp3);
            testEntityManager.persist(pm5);
            PM pm6 = new PM("PM4", osp3);
            testEntityManager.persist(pm6);
            PM pm7 = new PM("PM5", osp3);
            testEntityManager.persist(pm7);

            // Ajout des ONTs
            // Ajout des ONTs sous PM1
            // INACT depuis 10 jours
            ONT ont1 = new ONT(1, pm1);
            ont1.activateInact();
            ont1.setInactTimestamp(rightNowMinusTenDays);
            testEntityManager.persist(ont1);

            // INACT du jour
            ONT ont2 = new ONT(2, pm1);
            ont2.activateInact();
            ont2.setInactTimestamp(rightNow);
            testEntityManager.persist(ont2);

            // INACT du jour
            ONT ont3 = new ONT(3, pm1);
            ont3.activateInact();
            ont3.setInactTimestamp(rightNow);
            testEntityManager.persist(ont3);

            // DG actif
            ONT ont4 = new ONT(4, pm1);
            ont4.setInactive();
            testEntityManager.persist(ont4);

            // Ajout des ONTs sous PM2
            // INACT cleared
            ONT ont5 = new ONT(16, pm2);
            testEntityManager.persist(ont5);

            //Ajout des ONTs sous PM3
            // INACT du jour
            ONT ont6 = new ONT(32, pm3);
            ont6.activateInact();
            ont6.setInactTimestamp(rightNow);
            testEntityManager.persist(ont6);

            // INACT du jour
            ONT ont7 = new ONT(33, pm3);
            ont7.activateInact();
            ont7.setInactTimestamp(rightNow);
            testEntityManager.persist(ont7);

            // INACT du jour
            ONT ont8 = new ONT(34, pm3);
            ont8.activateInact();
            ont8.setInactTimestamp(rightNow);
            testEntityManager.persist(ont8);

            // Attention ici on est bien sous PM3 mais sous l'OLT0117
            ONT ont9 = new ONT(1, pm5); // ONT 1 sous PM3 different de ONT1 sous PM1
            testEntityManager.persist(ont9);

            // Ajout des ONTs sous PM4
            ONT ont10 = new ONT(64, pm4);
            testEntityManager.persist(ont10);

            ONT ont11 = new ONT(65, pm4);
            testEntityManager.persist(ont11);

            // INACT du jour
            // Attention ici on est bien sous PM4 mais sous l'OLT0116
            ONT ont12 = new ONT(2, pm6);
            ont12.activateInact();
            ont12.setInactTimestamp(rightNow);
            testEntityManager.persist(ont12);

            // Ajout des ONTs sous PM5
            //INACT depuis 10 jours
            ONT ont13 = new ONT(16, pm7);
            ont13.activateInact();
            ont13.setInactTimestamp(rightNowMinusTenDays);
            testEntityManager.persist(ont13);

            // Ajout des IGs
            // IG minor PM sur PM1
            IG ig1 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM1\"", "{2}", osp1, "minor", rightNow);
            testEntityManager.persist(ig1);

            // IG SNOW PM cleared sur PM2
            IG ig2 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM2\"", "{1}", osp1, "major", rightNowMinusTenDays);
            ig2.setClearanceTimestamp(rightNow);
            testEntityManager.persist(ig2);

            // IG SNOW PM sur PM3
            IG ig3 = new IG("networkTrans .OSS11.TRANS.IDF pm \"PM3\"", "{1}", osp1, "major", rightNow);
            testEntityManager.persist(ig3);

            testEntityManager.flush();
            initialized = true;
        }
    }

    @Test
    public void testFindByIdAlarmFullnameContainingAndOltSlotPortPon() {
        OltSlotPortPon osp1 = new OltSlotPortPon("OLT0116", 5 ,6);
        Integer igCount = igRepository.countByIdAlarmFullnameContainingAndOltSlotPortPon("PM1", osp1);
        assertEquals(1, Long.parseLong(igCount.toString()));
    }

    @Test
    public void testFindByOltSlotPortPon() {
        OltSlotPortPon osp1 = new OltSlotPortPon("OLT0116", 5 ,6);
        List<IG> igs = igRepository.findByOltSlotPortPon(osp1);
        assertEquals(3, igs.size());

    }
}
