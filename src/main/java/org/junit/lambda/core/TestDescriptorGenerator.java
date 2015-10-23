package org.junit.lambda.core;

/**
 * @author Tadaya Tsuyukubo
 * @since 5.0
 */
public interface TestDescriptorGenerator {

	Iterable<TestDescriptor> generate();

}
