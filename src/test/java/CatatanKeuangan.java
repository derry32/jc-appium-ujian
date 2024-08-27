import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CatatanKeuangan {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName","Pixel 2 API 30");
        dc.setCapability("udid","emulator-5554");
        dc.setCapability("platformName","android");
        dc.setCapability("platformVersion","11");
        dc.setCapability("appPackage","com.chad.financialrecord");
        dc.setCapability("appActivity","com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
    }

    @AfterClass
    public void finish(){
        delay(3);
        driver.quit();
    }

    @Test(priority = 1)
    public void testAddPertama(){
        delay(10);
        driver.findElementById("com.chad.financialrecord:id/fabMenu").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btnIncome").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etAmount").sendKeys("200000");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Lemburan");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btSave").click();
        delay(10);

        //test kategori berbeda
        driver.findElementById("com.chad.financialrecord:id/fabMenu").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btnIncome").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/spCategory").click();
        delay(2);
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvName\" and @text=\"Penghargaan\"]").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etAmount").sendKeys("500000");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Bonus");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btSave").click();
        delay(3);

        String actual = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvIncome\"])[1]").getText();
        Assert.assertEquals(actual,"700.000");
        String Kat1 = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvDesc\" and @text=\"Bonus\"])").getText();
        delay(2);
        String Kat2 = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvDesc\" and @text=\"Lemburan\"])").getText();
        System.out.println(Kat1 + " " + Kat2);
    }

    @Test(priority = 2)
    public void testKedua(){
        delay(10);
        driver.findElementById("com.chad.financialrecord:id/fabMenu").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etAmount").sendKeys("100000");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Nasi Padang");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btSave").click();
        delay(10);

        //test kategori berbeda
        driver.findElementById("com.chad.financialrecord:id/fabMenu").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/spCategory").click();
        delay(2);
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvName\" and @text=\"Pulsa\"]").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etAmount").sendKeys("100000");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Buat Paket Data");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btSave").click();
        delay(3);

        //test kategori berbeda
        driver.findElementById("com.chad.financialrecord:id/fabMenu").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/spCategory").click();
        delay(2);
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvName\" and @text=\"Motor\"]").click();
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etAmount").sendKeys("100000");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Ganti Oli");
        delay(2);
        driver.findElementById("com.chad.financialrecord:id/btSave").click();
        delay(3);

        String actual = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvExpense\"])[1]").getText();
        Assert.assertEquals(actual,"300.000");
        String Kat1 = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvDesc\" and @text=\"Nasi Padang\"])").getText();
        delay(2);
        String Kat2 = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvDesc\" and @text=\"Buat Paket Data\"])").getText();
        delay(2);
        String Kat3 = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvDesc\" and @text=\"Ganti Oli\"])").getText();
        System.out.println(Kat1 + " " + Kat2 + " " + Kat3);
    }

    @Test(priority = 3)
        public void testKetiga(){
        delay(10);
        driver.findElementByXPath("//android.widget.ExpandableListView[@resource-id=\"com.chad.financialrecord:id/elTransaction\"]/android.widget.RelativeLayout/android.widget.LinearLayout").click();
        delay(3);
        String actual = driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvBalance\"]").getText();
        delay(3);
        
        Assert.assertEquals(actual, "400.000");
        System.out.println("Saldo Rp. :" + actual);

    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
