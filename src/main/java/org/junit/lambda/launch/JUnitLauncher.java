
package org.junit.lambda.launch;

import java.nio.file.Path;
import java.util.Map;

/**
 * @author Sam Brannen
 * @since 5.0
 */
public class JUnitLauncher {

	public JUnitLauncher parameters(Map<String, String> launchParameters) {
		return this;
	}

	public JUnitLauncher classes(Class<?>... classes) {
		return this;
	}

	public JUnitLauncher classNames(String... classNames) {
		return this;
	}

	public JUnitLauncher packages(Package... packages) {
		return this;
	}

	public JUnitLauncher packageNames(String... packageNames) {
		return this;
	}

	public JUnitLauncher paths(Path... paths) {
		return this;
	}

	public JUnitLauncher fileNames(String... fileNames) {
		return this;
	}

	public JUnitLauncher include(String... patterns) {
		return this;
	}

	public JUnitLauncher exclude(String... patterns) {
		return this;
	}

	public TestRun build() {
		return new TestRun();
	}

}
