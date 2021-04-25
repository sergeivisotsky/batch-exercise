package com.sergei.batch.processing.job.intake;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sergei.batch.processing.domain.dao.Address;
import com.sergei.batch.processing.domain.dao.Customer;
import com.sergei.batch.processing.domain.dao.Order;
import com.sergei.batch.processing.domain.dao.ShipInfo;
import com.sergei.batch.xsd.dto.AddressType;
import com.sergei.batch.xsd.dto.CustomerType;
import com.sergei.batch.xsd.dto.CustomersOrders;
import com.sergei.batch.xsd.dto.OrderType;
import com.sergei.batch.xsd.dto.ShipInfoType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component(value = "intakeProcessor")
public class IntakeJobItemProcessor implements ItemProcessor<CustomersOrders, List<Customer>> {

    @Override
    public List<Customer> process(CustomersOrders customer) {
        return customer.getCustomers()
                .stream()
                .map(this::mapCustomer).collect(Collectors.toList());
    }

    private Customer mapCustomer(CustomerType customerType) {
        Customer customerEntity = new Customer();
        customerEntity.setCompanyName(customerType.getCompanyName());
        customerEntity.setContactName(customerType.getContactName());
        customerEntity.setFax(customerType.getFax());
        customerEntity.setContactTitle(customerType.getContactTitle());
        customerEntity.setPhone(customerType.getPhone());

        Address address = mapAddress(customerType.getFullAddress());
        customerEntity.setAddress(address);
        Set<Order> orderSet = customerType.getOrders().stream()
                .map(this::mapOrder)
                .collect(Collectors.toSet());
        customerEntity.setCustomerOrders(orderSet);
        return customerEntity;
    }

    private Order mapOrder(OrderType orderType) {
        Order order = new Order();
        order.setOrderDate(orderType.getOrderDate());
        order.setEmployeeId(order.getEmployeeId());
        order.setCustomerId(order.getCustomerId());
        order.setRequiredDate(order.getRequiredDate());

        ShipInfoType shipInfoType = orderType.getShipInfo();
        order.setShipInfo(mapShipInfo(shipInfoType));
        return order;
    }

    private ShipInfo mapShipInfo(ShipInfoType shipInfoType) {
        ShipInfo shipInfo = new ShipInfo();
        shipInfo.setShipAddress(shipInfoType.getShipAddress());
        shipInfo.setShipCity(shipInfoType.getShipCity());
        shipInfo.setShipCountry(shipInfoType.getShipCountry());
        shipInfo.setShipName(shipInfoType.getShipName());
        shipInfo.setFreight(shipInfoType.getFreight());
        shipInfo.setShippedDate(shipInfoType.getShippedDate());
        shipInfo.setShipPostalCode(shipInfoType.getShipPostalCode());
        shipInfo.setShipVia(shipInfoType.getShipVia());
        return shipInfo;
    }

    private Address mapAddress(AddressType fullAddress) {
        Address address = new Address();
        address.setAddress(fullAddress.getAddress());
        address.setCity(fullAddress.getCity());
        address.setCountry(address.getCountry());
        address.setRegion(address.getRegion());
        address.setPostalCode(address.getPostalCode());
        return address;
    }
}
