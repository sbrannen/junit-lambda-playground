
package org.junit.lambda.launch;

/**
 * @author Sam Brannen
 * @since 5.0
 */
public class TestRun {

	TestRun() {
	}

	public void start() {
		System.out.println("Starting JUnit test run");
	}

	public void stop() {
		System.out.println("Stopping JUnit test run");
	}

	public void pause() {
		System.out.println("Pausing JUnit test run");
	}

	public void restart() {
		System.out.println("Restarting JUnit test run");
	}

}
