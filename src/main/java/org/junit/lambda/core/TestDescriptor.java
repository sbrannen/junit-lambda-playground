
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
