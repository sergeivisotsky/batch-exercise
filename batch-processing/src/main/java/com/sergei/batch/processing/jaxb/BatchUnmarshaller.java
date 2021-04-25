package com.sergei.batch.processing.jaxb;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
public interface BatchUnmarshaller<T> {

    T unmarshal(String xml);

}
