package web;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebE2ETest extends DriverManager {
    web.StudyDriveWebPage studyDriveWebPage;

    @BeforeClass
    public void setup() {
        studyDriveWebPage = new StudyDriveWebPage(driver);
    }

    @Test(priority = 1)
    public void should_able_to_onboard_and_logout() throws Exception {
        studyDriveWebPage.clickOnAcceptCookies();
        studyDriveWebPage.SigningUp();
        studyDriveWebPage.verifyNewUseOnboardingr();
        studyDriveWebPage.logoutUser("Log out");
    }

    @Test(priority = 2)
    public void should_able_to_login_with_existing_user_and_post_comment() throws Exception {
        studyDriveWebPage.loginWithExistingUser();
        studyDriveWebPage.joinModuleAndPostComment();
    }
}
