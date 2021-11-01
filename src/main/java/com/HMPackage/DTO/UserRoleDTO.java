package com.HMPackage.DTO;

import com.HMPackage.entity.UserRole;
import lombok.Data;
import java.util.List;

@Data
public class UserRoleDTO {
    private Long userRoleId;
    private String token;
    private String name;
    private String password;
    private List<UserRole> userRoles;
}
