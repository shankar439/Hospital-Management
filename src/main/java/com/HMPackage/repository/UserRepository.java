package com.HMPackage.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HMPackage.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User>findById(Long id);
	Page<User> searchAllByNameLike(String s, Pageable paging);
	Optional<User> findByName(String name);
}
