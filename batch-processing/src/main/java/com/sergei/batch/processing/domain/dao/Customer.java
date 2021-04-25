package com.sergei.batch.processing.domain.dao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Entity
public class Customer extends BaseEntity {
    private static final long serialVersionUID = 5347569995152174269L;

    private String companyName;
    private String contactName;
    private String contactTitle;
    private String phone;
    private String fax;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CustomerOrders",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Order> customerOrders;

    @OneToOne
    private Address address;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Set<Order> getCustomerOrders() {
        if (customerOrders == null) {
            return new HashSet<>();
        }
        return customerOrders;
    }

    public void setCustomerOrders(Set<Order> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
