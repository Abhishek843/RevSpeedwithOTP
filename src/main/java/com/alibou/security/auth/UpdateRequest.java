
package com.alibou.security.auth;

import com.alibou.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {


    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;

}
