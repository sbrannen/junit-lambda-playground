package org.junit5.poc;

import java.util.HashMap;

import org.junit.lambda.launch.JUnitLauncher;
import org.junit.lambda.launch.TestRun;

/**
 * @author Sam Brannen
 * @since 5.0
 */
public class JUnit5Demo {

	@SuppressWarnings("serial")
	public static void main(String... args) {

		TestRun testRun = new JUnitLauncher()
				.parameters(new HashMap<String, String>(){{
					put("category", "smoke");
				}})
				.packageNames("org.junit5.poc")
				.include("*Tests")
				.build();

		testRun.start();

		testRun.stop();
	}

}
