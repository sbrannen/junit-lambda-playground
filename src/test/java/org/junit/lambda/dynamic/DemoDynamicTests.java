/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.lambda.dynamic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.lambda.dynamic.DynamicTestRegistrar;
import org.junit.lambda.dynamic.Text;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Demo test class for tests that are registered dynamically as lambda
 * expressions including support for method parameter resolution and
 * annotated parameters.
 *
 * @author Sam Brannen
 * @since 5.0
 */
public class DemoDynamicTests {

	@Test
	public void proofOfConcept() {

		$.test("no argument", () -> {
			assertTrue(true);
		});

		$.test("fallback parameter resolution without type", obj -> {
			assertEquals(NUM, obj);
		});

		$.test("fallback parameter resolution with explicit type", (Long num) -> {
			assertEquals(NUM, num);
		});

		$.test("boolean argument", (Boolean b) -> {
			assertTrue(b);
		});

		$.test("annotated string", (@Text String text) -> {
			assertEquals(TEXT, text);
		});

		$.test("2 arguments", (@Text String text, Long num) -> {
			assertEquals(TEXT, text);
			assertEquals(NUM, num);
		});

		$.test("3 arguments", (Long num, @Text String text, Boolean b) -> {
			assertEquals(NUM, num);
			assertEquals(TEXT, text);
			assertTrue(b);
		});

		$.test("4 arguments", (Long num1, @Text String text, Long num2, Boolean b) -> {
			assertEquals(NUM, num1);
			assertEquals(NUM, num2);
			assertEquals(TEXT, text);
			assertTrue(b);
		});

		$.test("5 arguments", (Long num1, @Text String text1, Long num2, @Text String text2, Long num3) -> {
			assertEquals(TEXT, text1);
			assertEquals(TEXT, text2);
			assertEquals(NUM, num1);
			assertEquals(NUM, num2);
			assertEquals(NUM, num3);
		});

		// This will become obsolete once tests are registered in the TestEngine.
		$.executeTests();
	}

	@Test
	public void missingRequiredTextAnnotationOnStringParameter() {
		// The demo attempts to inject a Long instead of a String since @Text is absent.
		exception.expect(RuntimeException.class);
		exception.expectMessage("argument type mismatch");

		$.test("non-annotated string", (String text) -> {
			assertEquals(TEXT, text);
		});

		// This will become obsolete once tests are registered in the TestEngine.
		$.executeTests();
	}


	private static final String TEXT = "enigma";
	private static final Long NUM = new Long(42);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// TODO Inject the registrar -- for example, as a method parameter.
	private final DynamicTestRegistrar $ = new DynamicTestRegistrar();

}
