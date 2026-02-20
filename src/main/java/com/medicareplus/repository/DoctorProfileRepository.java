package com.medicareplus.repository;

import com.medicareplus.entity.DoctorProfile;
import com.medicareplus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
    Optional<DoctorProfile> findByUser(User user);
    boolean existsByUser(User user);
}
