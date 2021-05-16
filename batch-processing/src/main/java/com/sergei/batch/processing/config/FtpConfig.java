package com.sergei.batch.processing.config;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Configuration
@ConditionalOnProperty(value = "file.storage", havingValue = "ftp")
public class FtpConfig {

    @Value("${file.ftp.host}")
    private String host;

    @Value("${file.ftp.port}")
    private Integer port;

    @Value("${file.ftp.username}")
    private String username;

    @Value("${file.ftp.password}")
    private String password;

    @Bean
    public SessionFactory<FTPFile> ftpSessionFactory() {
        DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
        sf.setHost(host);
        sf.setPort(port);
        sf.setUsername(username);
        sf.setPassword(password);
        return new CachingSessionFactory<>(sf);
    }

    @Bean
    public Session<FTPFile> ftpSession(SessionFactory<FTPFile> ftpSessionFactory) {
        return ftpSessionFactory.getSession();
    }
}
