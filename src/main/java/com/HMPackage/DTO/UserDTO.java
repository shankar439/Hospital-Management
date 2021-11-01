package com.HMPackage.DTO;

import com.HMPackage.entity.Role;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
	private long id;
	private String name;
	private String password;
	private int isActive;
	private int isDelete;
	private String roleName;
	private List<Role>roles;
}
