package com.sergei.batch.processing.jaxb;

import java.io.IOException;

import com.sergei.batch.xsd.dto.CustomersOrders;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * Unit test for {@link CustomerOrdersUnmarshaller}.
 *
 * @author Sergei Visotsky
 * @since 1.0
 */
public class CustomerOrdersUnmarshallerTest {

    private final ClassLoader classLoader = this.getClass().getClassLoader();

    @Test
    public void shouldUnmarshal() throws IOException {

        CustomerOrdersUnmarshaller unmarshaller = new CustomerOrdersUnmarshaller();
        String xmlAsString = IOUtils.toString(classLoader.getResourceAsStream("xml/CustomersOrders.xml"));
        CustomersOrders customer = unmarshaller.unmarshal(xmlAsString);

        System.out.println(customer.getCustomers().get(0).getCustomerID());
    }
}
