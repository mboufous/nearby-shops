package com.unitedremote.shops.Utils;

import com.unitedremote.shops.DAO.Entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sun.plugin.liveconnect.SecurityContextHelper;

@Component
public class PrincipaleUser {
    private static PrincipaleUser instance = new PrincipaleUser();
    private static Object principal;

    public static PrincipaleUser getInstance() {
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return instance;
    }

    private PrincipaleUser() {
    }

    public User getAuthUser() {
        return ((User) principal);
    }

}
