package com.example.SWP.service;

import com.example.SWP.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String loginPerRole(Role role) {
        if(role.equals(Role.WLASCICIEL)){
            return "owner/view-orders";
        }
        if(role.equals(Role.SPRZEDAWCA)){
            return "seller/create-order";
        }
        if(role.equals(Role.ADMINISTRATOR)){
            return "view-orders";
        }
        if(role.equals(Role.PAKOWACZ)){
            return "view-orders";
        }
        if(role.equals(Role.KURIER)){
            return "view-orders";
        }
        return "error-page";
    }
}
