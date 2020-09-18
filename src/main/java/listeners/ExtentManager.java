package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {
	public static Logger log=LogManager.getLogger(ExtentManager.class.getName());
	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null)

			createInstance(".//test-output//ReportHTML.html");

		return extent;

	}

	public static ExtentReports createInstance(String fileName) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);


		htmlReporter.config().setTheme(Theme.DARK);

		htmlReporter.config().setDocumentTitle(fileName);

		htmlReporter.config().setEncoding("utf-8");

		htmlReporter.config().setReportName(fileName);
		htmlReporter.getAuthorContextInfo();
		htmlReporter.getReporterName();
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		return extent;

	}

}