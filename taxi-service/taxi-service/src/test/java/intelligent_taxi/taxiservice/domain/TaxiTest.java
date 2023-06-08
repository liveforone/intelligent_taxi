package intelligent_taxi.taxiservice.domain;

import intelligent_taxi.taxiservice.dto.TaxiRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TaxiTest {

    @Test
    void createTest() {
        //given
        String username = "jdfoajeofjaoifeoincojeofjaoefjofja";
        String licenseNum = "00아 0000";
        String phoneNum = "01000000000";
        String region = "서울";
        String grade = "van";
        TaxiRequest request = new TaxiRequest();
        request.setLicenseNum(licenseNum);
        request.setRegion(region);
        request.setPhoneNum(phoneNum);
        request.setTaxiGrade(grade);

        //when
        Taxi taxi = Taxi.create(request, username);

        //then
        assertThat(taxi.getTaxiGrade()).isEqualTo(TaxiGrade.VAN);
        assertThat(taxi.getRegion()).isEqualTo(Region.SEOUL);
    }

    @Test
    void updateRegionTest() {
        //given
        String username = "jdfoajeofjaoifeoincojeofjaoefjofja";
        String licenseNum = "00아 0000";
        String phoneNum = "01000000000";
        String region = "서울";
        String grade = "PREMIUM";
        TaxiRequest request = new TaxiRequest();
        request.setLicenseNum(licenseNum);
        request.setRegion(region);
        request.setPhoneNum(phoneNum);
        request.setTaxiGrade(grade);
        Taxi taxi = Taxi.create(request, username);

        //when
        String updatedRegion = "인천";
        taxi.updateRegion(updatedRegion, username);

        //then
        assertThat(taxi.getRegion()).isEqualTo(Region.INCHEON);
    }

    @Test
    void updateGradeTest() {
        //given
        String username = "jdfoajeofjaoifeoincojeofjaoefjofja";
        String licenseNum = "00아 0000";
        String phoneNum = "01000000000";
        String region = "서울";
        String grade = "PREMIUM";
        TaxiRequest request = new TaxiRequest();
        request.setLicenseNum(licenseNum);
        request.setRegion(region);
        request.setPhoneNum(phoneNum);
        request.setTaxiGrade(grade);
        Taxi taxi = Taxi.create(request, username);

        //when
        String updatedGrade = "normal";
        taxi.updateGrade(updatedGrade, username);

        //then
        assertThat(taxi.getTaxiGrade()).isEqualTo(TaxiGrade.NORMAL);
    }
}