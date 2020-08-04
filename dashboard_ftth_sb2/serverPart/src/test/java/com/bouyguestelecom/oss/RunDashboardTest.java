package com.bouyguestelecom.oss;

import com.bouyguestelecom.oss.controller.DetailViewApiControllerTest;
import com.bouyguestelecom.oss.controller.GlobalViewApiControllerTest;
import com.bouyguestelecom.oss.repositories.IGRepositoryTest;
import com.bouyguestelecom.oss.repositories.OltSlotPortPonRepositoryTest;
import com.bouyguestelecom.oss.repositories.PMRepositoryTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        OltSlotPortPonRepositoryTest.class,
        PMRepositoryTest.class,
        IGRepositoryTest.class,
        GlobalViewApiControllerTest.class,
        DetailViewApiControllerTest.class
})

public class RunDashboardTest {

}
