/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
				// TODO from TestDescriptor ID
				.build();

		testRun.start();

		testRun.stop();
	}

}
