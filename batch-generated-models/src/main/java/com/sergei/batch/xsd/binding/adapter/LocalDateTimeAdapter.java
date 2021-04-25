package com.sergei.batch.xsd.binding.adapter;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    public LocalDateTime unmarshal(String value) {
        return LocalDateTime.parse(value);
    }

    public String marshal(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
