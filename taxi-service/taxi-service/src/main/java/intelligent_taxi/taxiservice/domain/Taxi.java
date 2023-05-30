package intelligent_taxi.taxiservice.domain;

import intelligent_taxi.taxiservice.dto.TaxiRequest;
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

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String taxiGrade;

    @Column(nullable = false, unique = true)
    private String licenseNum;

    @Column(nullable = false, unique = true)
    private String phoneNum;

    @Column(nullable = false, unique = true)
    private String username;

    private Taxi(String region, String taxiGrade, String licenseNum, String phoneNum, String username) {
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

    public void updateRegion(String region) {
        this.region = Region.matchRegion(region);
    }

    public void updateGrade(String grade) {
        this.taxiGrade = TaxiGrade.matchGrade(grade);
    }
}
