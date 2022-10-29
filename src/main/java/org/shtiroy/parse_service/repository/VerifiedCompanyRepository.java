package org.shtiroy.parse_service.repository;

import org.shtiroy.parse_service.entity.VerifiedCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedCompanyRepository extends JpaRepository<VerifiedCompany, Integer> {

}
