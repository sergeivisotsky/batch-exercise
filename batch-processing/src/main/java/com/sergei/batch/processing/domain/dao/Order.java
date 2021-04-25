package com.sergei.batch.processing.domain.dao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Entity
public class Order extends BaseEntity {
    private static final long serialVersionUID = -6680477256894734485L;

    private String customerId;
    private String employeeId;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    @OneToOne
    private ShipInfo shipInfo;
    @ManyToMany(mappedBy = "customerOrders")
    private Set<Customer> customers;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public ShipInfo getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(ShipInfo shipInfo) {
        this.shipInfo = shipInfo;
    }

    public Set<Customer> getCustomers() {
        if (customers == null) {
            return new HashSet<>();
        }
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
