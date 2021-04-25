package com.sergei.batch.processing.jaxb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.sergei.batch.xsd.dto.CustomersOrders;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component
public class CustomerOrdersUnmarshaller implements BatchUnmarshaller<CustomersOrders> {

    @Override
    public CustomersOrders unmarshal(String xml) {
        CustomersOrders customer;

        try (InputStream xmlStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))) {
            JAXBContext jaxbContext = JAXBContext.newInstance();

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            ClassLoader classLoader = this.getClass().getClassLoader();
            Schema schema = factory.newSchema(classLoader.getResource("xsd/CustomersOrders.xsd"));

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);

            customer = (CustomersOrders) unmarshaller.unmarshal(xmlStream);
        } catch (IOException | SAXException | JAXBException e) {
            throw new IllegalStateException("Unable to unmarshal XML due to the unexpected issue.", e);
        }

        return customer;
    }
}
