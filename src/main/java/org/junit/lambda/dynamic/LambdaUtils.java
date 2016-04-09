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
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

/**
 * Collection of utilities for working with lambda expressions.
 *
 * @author Sam Brannen
 * @since 5.0
 */
public final class LambdaUtils {

	private LambdaUtils() {
		/* no-op */
	}

	/**
	 * Get the underlying, static method that serves as the implementation of the
	 * supplied {@link Serializable} lambda.
	 *
	 * <p>The returned method allows one to introspect the {@linkplain Parameter
	 * explicit parameters} supplied to the original lambda expression and to
	 * invoke the lambda expression reflectively.
	 *
	 * <p>A special "thank you" goes out to Benji Weber for authoring the original
	 * code that inspired this method.
	 *
	 * @see SerializedLambda
	 */
	public static Method getImplementationMethod(Serializable lambda) {
		Class<?> clazz = lambda.getClass();

		SerializedLambda serializedLambda;
		try {
			Method replaceMethod = clazz.getDeclaredMethod("writeReplace");
			replaceMethod.setAccessible(true);
			serializedLambda = (SerializedLambda) replaceMethod.invoke(lambda);
		}
		catch (Exception ex) {
			String message = String.format("Failed to resolve SerializedLambda for lambda [%s].", clazz.getName());
			throw new IllegalStateException(message, ex);
		}

		Class<?> implementationClass;
		try {
			String className = serializedLambda.getImplClass().replace('/', '.');
			implementationClass = Class.forName(className);
		}
		catch (Exception ex) {
			String message = String.format("Failed to resolve implementation class for lambda [%s].", serializedLambda);
			throw new IllegalStateException(message, ex);
		}

		String implMethodName = serializedLambda.getImplMethodName();

		// @formatter:off
		Optional<Method> method = Arrays.stream(implementationClass.getDeclaredMethods())
				.filter(m -> m.getName().equals(implMethodName))
				.findFirst();
		// @formatter:on

		if (method.isPresent()) {
			return method.get();
		}

		// else
		throw new IllegalStateException(
			String.format("Failed to resolve implementation method for lambda [%s] in implementation class [%s].",
				serializedLambda, implementationClass.getName()));
	}

}
