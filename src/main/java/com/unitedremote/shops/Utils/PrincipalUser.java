package com.unitedremote.shops.Utils;

import com.unitedremote.shops.DAO.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PrincipalUser {

    public PrincipalUser() {

    }

    public User getAuthUser() {
        if(SecurityContextHolder.getContext() != null)
            return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return null;
    }

}
