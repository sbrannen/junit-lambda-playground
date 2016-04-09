/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
