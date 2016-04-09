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

/**
 * Functional interface for lambda-based tests that accept 0 parameters.
 *
 * @author Sam Brannen
 * @since 5.0
 */
@FunctionalInterface
public interface Lambda0 extends Serializable {

	void execute();

}