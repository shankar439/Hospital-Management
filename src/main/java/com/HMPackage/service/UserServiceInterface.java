package com.HMPackage.service;

import java.util.Optional;
import com.HMPackage.DTO.UserDTO;
import com.HMPackage.DTO.UserRoleDTO;
import com.HMPackage.baseResponse.PageResponse;
import com.HMPackage.entity.User;

public interface UserServiceInterface {

	User addUser(UserDTO userDTO);
	Optional<User> getUserbyId(Long id);
	Optional<User> updatebyId(UserDTO userDTO);
	Optional<User> deletesoft(UserDTO userDTO);
	PageResponse<User> pageByUserName(int offset, int pageSize, String name);
	Object login(UserRoleDTO userRoleDTO);
}
