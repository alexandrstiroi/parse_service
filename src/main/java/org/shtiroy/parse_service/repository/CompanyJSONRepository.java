package org.shtiroy.parse_service.repository;

import org.shtiroy.parse_service.entity.CompanyJSON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface CompanyJSONRepository extends JpaRepository<CompanyJSON, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO working_data.t_company_json(idno, create_ts, company_data, resource) " +
            "values(?1, ?2, cast(?3 as json), ?4)",
            nativeQuery = true)
    void saveCompany(String idno, Timestamp create_ts, Object company_data, String resource);

    @Query(value = "SELECT count(*) FROM working_data.t_company_json where resource =?1", nativeQuery = true)
    Integer getCount(String resourceName);

    @Query(value = "SELECT MAX(create_ts) FROM working_data.t_company_json WHERE resource = ?1", nativeQuery = true)
    Timestamp getMaxCreateTs(String resourceName);
}
