package org.shtiroy.parse_service.resource.data2b;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shtiroy.parse_service.entity.Company;
import org.shtiroy.parse_service.entity.CompanyJSON;
import org.shtiroy.parse_service.entity.CompanyResource;
import org.shtiroy.parse_service.entity.Resource;
import org.shtiroy.parse_service.repository.CompanyJSONRepository;
import org.shtiroy.parse_service.repository.CompanyRepository;
import org.shtiroy.parse_service.repository.CompanyResourceRepository;
import org.shtiroy.parse_service.resource.ResourceParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class Data2bResource extends ResourceParser {
    private static final Logger LOGGER = LogManager.getLogger(Data2bResource.class.getName());
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyJSONRepository companyJSONRepository;
    @Autowired
    private CompanyResourceRepository companyResourceRepository;
    /**
     * Метод отправляет get запрос для скачивания json.
     * @param resource - аддрес запроса.
     * @return - boolean, если запрос отработал.
     */
    @Override
    public boolean getJSONFromUrl(Resource resource) {
        try {
            List<Company> companyList = companyRepository.findAllActiveCompany(1);
            LOGGER.info("try to get " + companyList.get(0).getIdno());
            String strJson = (String) methodGet(resource.getResourceUrl() + companyList.get(0).getIdno());
            CompanyResource companyResource = new CompanyResource(companyList.get(0), resource, new Timestamp(System.currentTimeMillis()));
            companyResourceRepository.save(companyResource);
            CompanyJSON companyJSON = new CompanyJSON();
            companyJSON.setIdno(companyList.get(0).getIdno());
            companyJSON.setCompanyData(strJson);
            companyJSON.setCreateTs(new Timestamp(System.currentTimeMillis()));
            companyJSON.setResource("data2b.md");
            companyJSONRepository.saveCompany(companyJSON.getIdno(), companyJSON.getCreateTs(),
                    companyJSON.getCompanyData().toString(), companyJSON.getResource());
            LOGGER.info(companyList.get(0).getCompanyName());
        } catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return false;
        }
        return true;
    }
}
