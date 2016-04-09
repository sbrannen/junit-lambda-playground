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

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Proof of concept for dynamic test registration whereby tests are registered
 * as serialized lambda expressions with full support for method parameter
 * resolution including support for reflectively introspecting the parameters
 * passed to such lambda expressions.
 *
 * @author Sam Brannen
 * @since 5.0
 */
public class DynamicTestRegistrar {

	private final List<LambdaInvoker> lambdaInvokers = new ArrayList<>();


	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda0 lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda1<?> lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda2<?, ?> lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda3<?, ?, ?> lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda4<?, ?, ?, ?> lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@code lambda} expression as a test with the given
	 * {@code displayName}.
	 */
	public final void test(String displayName, Lambda5<?, ?, ?, ?, ?> lambda) {
		test(displayName, (Serializable) lambda);
	}

	/**
	 * Register the supplied {@link Serializable} {@code lambda} expression as
	 * a test with the given {@code displayName}.
	 *
	 * @see SerializedLambda
	 * @see LambdaUtils#getImplementationMethod(Serializable)
	 */
	private final void test(String displayName, Serializable lambda) {
		Method method = LambdaUtils.getImplementationMethod(lambda);
		this.lambdaInvokers.add(new LambdaInvoker(displayName, method));
	}

	// This will become obsolete once dynamic tests are registered in the TestEngine.
	public void executeTests() {
		for (LambdaInvoker lambdaInvoker : this.lambdaInvokers) {
			try {
				lambdaInvoker.invoke();
				String message = String.format("Test succeeded: %s (%s)", lambdaInvoker.getDisplayName(),
					lambdaInvoker.getName());
				System.out.println(message);
			}
			catch (Throwable ex) {
				String message = String.format("Test failed:    %s (%s) --> %s", lambdaInvoker.getDisplayName(),
					lambdaInvoker.getName(), ex);
				System.err.println(message);

				if (ex instanceof RuntimeException) {
					throw (RuntimeException) ex;
				}
				if (ex instanceof Error) {
					throw (Error) ex;
				}
				throw new RuntimeException(ex);
			}
		}
	}

}
