package com.unitedremote.shops.Web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationForm implements Serializable {

    String email;
    String name;
    String password;
    String repassword;

}
