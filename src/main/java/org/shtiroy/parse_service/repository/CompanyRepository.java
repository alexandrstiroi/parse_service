package org.shtiroy.parse_service.repository;

import org.shtiroy.parse_service.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query(value = " select t1.* " +
            " from working_data.t_company t1" +
            " where company_type = 'Societate cu răspundere limitată' " +
            " and LENGTH(t1.idno) = 13" +
            " and t1.date_end is null " +
            " and not exists(select 1 from working_data.t_company_json t2 where t2.idno = t1.idno)" +
            " and not exists(select 1 from working_data.t_company_resource t3 where t3.company_id = t1.company_id)" +
            " fetch first ?1 rows only",
            nativeQuery = true)
    List<Company> findAllActiveCompany(Integer limit);

    Company findByIdno(String idno);

    @Query(value = "SELECT count(*) FROM working_data.t_company", nativeQuery = true)
    Integer getCount();
}
