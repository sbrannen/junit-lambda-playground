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

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Simple invoker for a static lambda implementation method for a {@link SerializedLambda}.
 *
 * @author Sam Brannen
 * @since 5.0
 * @see SerializedLambda
 */
class LambdaInvoker {

	private final String displayName;
	private final Method lambda;


	LambdaInvoker(String displayName, Method lambda) {
		this.displayName = displayName;
		this.lambda = lambda;
	}

	String getDisplayName() {
		return this.displayName;
	}

	String getName() {
		return this.lambda.toGenericString();
	}

	void invoke() throws Exception {
		try {
			this.lambda.setAccessible(true);
			this.lambda.invoke(null, resolveParameters(this.lambda));
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// TODO Replace demo with official MethodInvoker/MethodParameterResolver support.
	private Object[] resolveParameters(Method method) {
		Parameter[] parameters = method.getParameters();
		Object[] args = new Object[parameters.length];

		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			if (parameter.isAnnotationPresent(Text.class) && parameter.getType() == String.class) {
				args[i] = "enigma";
			}
			else if (parameter.getType() == Boolean.class) {
				args[i] = Boolean.TRUE;
			}
			else {
				args[i] = new Long(42);
			}
		}
		return args;
	}

}
