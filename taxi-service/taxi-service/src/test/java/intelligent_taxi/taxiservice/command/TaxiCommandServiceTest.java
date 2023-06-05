package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.domain.Region;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.UpdateRegionRequest;
import intelligent_taxi.taxiservice.query.TaxiQueryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TaxiCommandServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    TaxiCommandService taxiCommandService;

    @Autowired
    TaxiQueryService taxiQueryService;

    Long createTaxi(String username, String region, String taxiGrade, String licenseNum, String phoneNum) {
        TaxiRequest request = new TaxiRequest();
        request.setRegion(region);
        request.setTaxiGrade(taxiGrade);
        request.setLicenseNum(licenseNum);
        request.setPhoneNum(phoneNum);
        return taxiCommandService.createTaxi(request, username);
    }

    @Test
    @Transactional
    void createTaxiTest() {
        //given
        String username = "dflsjfoefneowfoaefoijf";
        String region = "seoul";
        String taxiGrade = "NORMAL";
        String licenseNum = "00아0000";
        String phoneNum = "01000000000";
        TaxiRequest request = new TaxiRequest();
        request.setRegion(region);
        request.setTaxiGrade(taxiGrade);
        request.setLicenseNum(licenseNum);
        request.setPhoneNum(phoneNum);

        //when
        Long taxiId = taxiCommandService.createTaxi(request, username);
        em.flush();
        em.clear();

        //then
        assertThat(taxiQueryService.getTaxiById(taxiId).getLicenseNum())
                .isEqualTo(licenseNum);
    }

    @Test
    @Transactional
    void updateRegionTest() {
        //given
        String username = "dflsjfoefneowfoaefoijf";
        String region = "seoul";
        String taxiGrade = "NORMAL";
        String licenseNum = "00아0000";
        String phoneNum = "01000000000";
        Long taxiId = createTaxi(username, region, taxiGrade, licenseNum, phoneNum);
        em.flush();
        em.clear();

        //when
        String updatedRegion = "GYEONGGI";
        UpdateRegionRequest request = new UpdateRegionRequest();
        request.setRegion(updatedRegion);
        taxiCommandService.updateRegion(request, username, taxiId);
        em.flush();
        em.clear();

        //then
        assertThat(taxiQueryService.getTaxiById(taxiId).getRegion())
                .isEqualTo(Region.GYEONGGI.getRegion());
    }

    @Test
    @Transactional
    void updateGradeTest() {
    }

    @Test
    @Transactional
    void deleteOneByUsernameTest() {
    }

    @Test
    @Transactional
    void deleteOneByIdTest() {
    }
}