package intelligent_taxi.taxiservice.domain;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.converter.RegionConverter;
import intelligent_taxi.taxiservice.converter.TaxiGradeConverter;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = RegionConverter.class)
    @Column(nullable = false)
    private Region region;

    @Convert(converter = TaxiGradeConverter.class)
    @Column(nullable = false)
    private TaxiGrade taxiGrade;

    @Column(nullable = false, unique = true)
    private String licenseNum;

    @Column(nullable = false, unique = true)
    private String phoneNum;

    @Column(nullable = false, unique = true)
    private String username;

    private Taxi(Region region, TaxiGrade taxiGrade, String licenseNum, String phoneNum, String username) {
        this.region = region;
        this.taxiGrade = taxiGrade;
        this.licenseNum = licenseNum;
        this.phoneNum = phoneNum;
        this.username = username;
    }

    public static Taxi create(TaxiRequest requestDto, String username) {
        return new Taxi(
                Region.matchRegion(requestDto.getRegion()),
                TaxiGrade.matchGrade(requestDto.getTaxiGrade()),
                requestDto.getLicenseNum(),
                requestDto.getPhoneNum(),
                username
        );
    }

    public void updateRegion(String region, String inputUsername) {
        if (!inputUsername.equals(username)) {
            throw new TaxiCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }

        this.region = Region.matchRegion(region);
    }

    public void updateGrade(String grade, String inputUsername) {
        if (!inputUsername.equals(username)) {
            throw new TaxiCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }

        this.taxiGrade = TaxiGrade.matchGrade(grade);
    }
}
