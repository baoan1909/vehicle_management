package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.AccountDTO;
import com.example.vehicle_management.models.Account;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.Role;
import com.example.vehicle_management.services.ICustomerService;
import com.example.vehicle_management.services.IRoleService;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static AccountDTO toDTO(Account account, IRoleService roleService, ICustomerService customerService) {
        Role role = roleService.getRoleById(account.getRoleId());
        String roleName = (role != null) ? role.getRoleName() : "Không xác định";
        Customer customer = customerService.getCustomerById(account.getCustomerId());
        String fullName = (customer != null) ? customer.getFullName() : "Không xác định";

        return new AccountDTO(
                account.getAccountId(),
                account.getUserName(),
                fullName,
                account.getEmail(),
                roleName,
                account.getStatus()
        );
    }

    public static List<AccountDTO> toDTOList(List<Account> accounts, IRoleService roleService, ICustomerService customerService) {
        return accounts.stream()
                .map(account -> toDTO(account, roleService, customerService))
                .collect(Collectors.toList());
    }
}
