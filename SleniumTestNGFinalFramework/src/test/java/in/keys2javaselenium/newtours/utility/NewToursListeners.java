package in.keys2javaselenium.newtours.utility;

import java.io.IOException;

import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class NewToursListeners extends TestListenerAdapter {

	static int passCount = 0;
	static int failCount = 0;
	static int skipCount = 0;

	@Override
	public void onTestStart(ITestResult tr) {
		System.out.println(tr.getName() + "....started");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		log("Test '" + tr.getName() + "' PASSED");
		log(tr.getTestClass());
		System.out.println("-----------------");
		passCount++;
	}

	@Override
	public void onTestFailure(ITestResult tr) {

		log("Test '" + tr.getName() + "' FAILED");
		log(tr.getTestClass());
		System.out.println("------------------");
		try {
			BrowserSelection.getScreenShot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		failCount++;
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		skipCount++;
	}

	private void log(String methodname) {
		System.out.println(methodname);
	}

	private void log(IClass testClass) {
		System.out.println(testClass);
	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {
		System.out.println("Total Test Passed " + passCount);
		System.out.println("Total Test Failed " + failCount);
		System.out.println("Total Test skipped " + skipCount);
	}
}
