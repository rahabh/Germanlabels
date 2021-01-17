package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

	public static WebDriver driver;
	public static Properties properties;

	TestBase() throws FileNotFoundException {
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\rabhishek\\Documents"
					+ "\\GermanyLabels\\Germany\\src\\main\\java\\configFiles\\config.properties");

			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void driverInitialize() {
		String browserName = properties.getProperty("browser");
		String languageName = properties.getProperty("language");
		String channelName = properties.getProperty("channel");
		String url = properties.getProperty("URL");

		switch (browserName) {
		case "firefox":
			if (null == driver) {
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\rabhishek"
						+ "\\eclipse-workspace\\GermanyNew\\drivers\\geckodriver\\geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver();
			}
			break;

		case "chrome":
			if (null == driver) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\rabhishek"
						+ "\\eclipse-workspace\\GermanyNew\\drivers\\chromedriver\\chromedriver_87.exe");
				driver = new ChromeDriver();
			}
			break;
		case "edge":
			if (null == driver) {
				System.setProperty("webdriver.edge.driver", "C:\\Users\\rabhishek"
						+ "\\eclipse-workspace\\GermanyNew\\drivers\\edgedriver\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			break;
		default:
			System.out.println("------- Please enter valid browser name" + " such as chrome, edge or firefox");
		}
		
		driver.get(url);
		
		String currentURL =driver.getCurrentUrl();
		if (languageName.equalsIgnoreCase("german")) {
			currentURL = "https://www.bwin.de/de";
		}
		else if (languageName.equalsIgnoreCase("english")) {
			currentURL = "https://www.bwin.de/en";
		}
		driver.get(currentURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
	}
	
	

}