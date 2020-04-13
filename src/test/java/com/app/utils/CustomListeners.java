package com.app.utils;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;

import java.io.IOException;

public class CustomListeners extends BaseClass implements ITestListener, ISuiteListener {

    public String messageBody;

    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub
    }

    public void onStart(ITestContext arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestFailure(ITestResult arg0) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            ScreenshotUtils.captureScreenshot();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " Failed with exception : " + arg0.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(ScreenshotUtils.screenshotName));
        Reporter.log("Click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtils.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtils.screenshotName + "><img src=" + ScreenshotUtils.screenshotName + " height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();
    }

    public void onTestSkipped(ITestResult arg0) {
        test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " SKIPPED");
        rep.endTest(test);
        rep.flush();
    }

    public void onTestStart(ITestResult arg0) {
        test = rep.startTest(arg0.getName().toUpperCase());
    }

    public void onTestSuccess(ITestResult arg0) {
        test.log(LogStatus.PASS, arg0.getName().toUpperCase() + " PASS");
        rep.endTest(test);
        rep.flush();
    }

    public void onFinish(ISuite arg0) {
//        MailUtils mail = new MailUtils();
//        try {
//            messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
//                    + ":8080/job/DataDrivenLiveProject/Extent_Reports/";
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            String[] to = config.getProperty("to").split(",");
//            mail.sendMail(config.getProperty("server"), config.getProperty("from"), to,
//                    config.getProperty("subject"), messageBody);
//        } catch (AddressException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public void onStart(ISuite arg0) {
        // TODO Auto-generated method stub
    }
}
