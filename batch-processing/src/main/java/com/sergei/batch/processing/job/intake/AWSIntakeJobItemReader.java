package com.sergei.batch.processing.job.intake;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.sergei.batch.processing.jaxb.CustomerOrdersUnmarshaller;
import com.sergei.batch.xsd.dto.CustomersOrders;
import org.springframework.batch.item.ItemReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component(value = "intakeReader")
@ConditionalOnProperty(value = "file.storage", havingValue = "s3")
public class AWSIntakeJobItemReader implements ItemReader<CustomersOrders> {

    private final AmazonS3 s3Client;
    private final CustomerOrdersUnmarshaller customerOrdersUnmarshaller;

    public AWSIntakeJobItemReader(AmazonS3 s3Client, CustomerOrdersUnmarshaller customerOrdersUnmarshaller) {
        this.s3Client = s3Client;
        this.customerOrdersUnmarshaller = customerOrdersUnmarshaller;
    }

    @Override
    public CustomersOrders read() throws Exception {
        S3Object file = s3Client.getObject("batch-exercise", "CustomersOrders.xml");
        return customerOrdersUnmarshaller.unmarshal(file.getObjectContent().toString());
    }

}
