package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.domain.Region;
import intelligent_taxi.taxiservice.domain.TaxiGrade;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.UpdateGradeRequest;
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
        String region = "서울";
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
        String region = "인천";
        String taxiGrade = "NORMAL";
        String licenseNum = "00바0000";
        String phoneNum = "01000000000";
        Long taxiId = createTaxi(username, region, taxiGrade, licenseNum, phoneNum);
        em.flush();
        em.clear();

        //when
        String updatedRegion = "경기";
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
        //given
        String username = "dflsjfoefneowfoaefoijf";
        String region = "강원";
        String taxiGrade = "NORMAL";
        String licenseNum = "00사0000";
        String phoneNum = "01000000000";
        Long taxiId = createTaxi(username, region, taxiGrade, licenseNum, phoneNum);
        em.flush();
        em.clear();

        //when
        String updatedGrade = "premium";
        UpdateGradeRequest request = new UpdateGradeRequest();
        request.setTaxiGrade(updatedGrade);
        taxiCommandService.updateGrade(request, username, taxiId);
        em.flush();
        em.clear();

        //then
        assertThat(taxiQueryService.getTaxiById(taxiId).getTaxiGrade())
                .isEqualTo(TaxiGrade.PREMIUM);
    }

    @Test
    @Transactional
    void deleteOneByUsernameTest() {
        //given
        String username = "dflsjfoefneowfoaefoijf";
        String region = "경북";
        String taxiGrade = "NORMAL";
        String licenseNum = "00자0000";
        String phoneNum = "01000000000";
        Long taxiId = createTaxi(username, region, taxiGrade, licenseNum, phoneNum);
        em.flush();
        em.clear();

        //when
        taxiCommandService.deleteOneByUsername(username);
        em.flush();
        em.clear();

        //then
        assertThat(taxiQueryService.getTaxiById(taxiId).getId())
                .isNull();
    }

    @Test
    @Transactional
    void deleteOneByIdTest() {
        //given
        String username = "dflsjfoefneowfoaefoijf";
        String region = "전남";
        String taxiGrade = "NORMAL";
        String licenseNum = "00자0000";
        String phoneNum = "01000000000";
        Long taxiId = createTaxi(username, region, taxiGrade, licenseNum, phoneNum);
        em.flush();
        em.clear();

        //when
        taxiCommandService.deleteOneById(taxiId, username);
        em.flush();
        em.clear();

        //then
        assertThat(taxiQueryService.getTaxiById(taxiId).getId())
                .isNull();
    }
}