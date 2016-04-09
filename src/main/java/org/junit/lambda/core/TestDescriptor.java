/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.lambda.core;

import java.util.List;

/**
 * @author Sam Brannen
 * @since 5.0
 */
public interface TestDescriptor {

	enum Type {
		ROOT, NODE, LEAF
	};


	Type getType();

	boolean isDynamic();

	String getId();

	String getDisplayName();

	TestDescriptor getParent();

	List<TestDescriptor> getChildren();

}
