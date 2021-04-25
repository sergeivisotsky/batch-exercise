package com.sergei.batch.processing.job.intake;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.sergei.batch.processing.jaxb.CustomerOrdersUnmarshaller;
import com.sergei.batch.xsd.dto.CustomersOrders;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.batch.item.ItemReader;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.stereotype.Component;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component(value = "intakeReader")
public class IntakeJobItemReader implements ItemReader<CustomersOrders> {

    private final Session<FTPFile> ftpSession;
    private final CustomerOrdersUnmarshaller customerOrdersUnmarshaller;

    public IntakeJobItemReader(Session<FTPFile> ftpSession,
                               CustomerOrdersUnmarshaller customerOrdersUnmarshaller) {
        this.ftpSession = ftpSession;
        this.customerOrdersUnmarshaller = customerOrdersUnmarshaller;
    }

    @Override
    public CustomersOrders read() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        ftpSession.read("customerOrders", outputStream);
        return customerOrdersUnmarshaller.unmarshal(outputStream.toString());
    }

}
