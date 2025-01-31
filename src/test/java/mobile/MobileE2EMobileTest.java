package mobile;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileE2EMobileTest extends MobileTestBase {
    private StudyDriveMobilePage studyDrivePage;

    @Test(testName = "End to End test flow StudyDrive App")
    public void test_should_verify_e2e_flow() throws Exception {
//        startAppiumServer();
        studyDrivePage = new StudyDriveMobilePage(drivers);
        studyDrivePage.acceptTermsAndConditions();
        boolean elementVisibility = studyDrivePage.verifyElementOnboardingScreen();
        Assert.assertEquals(true, elementVisibility);
        studyDrivePage.completeOnboadingProcess();
        studyDrivePage.searchModule();
        studyDrivePage.selectModule();
        studyDrivePage.commentOnPost();
//        stopAppiumServer();
    }
}
