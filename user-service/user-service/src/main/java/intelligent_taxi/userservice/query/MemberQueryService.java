package intelligent_taxi.userservice.query;

import intelligent_taxi.userservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.userservice.dto.response.MemberResponse;
import intelligent_taxi.userservice.exception.MemberCustomException;
import intelligent_taxi.userservice.query.util.MemberMapper;
import intelligent_taxi.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MemberResponse getMemberByUsername(String username) {
        return MemberMapper.entityToDto(memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL)));
    }

    public String getBankbookNumByUsername(String username) {
        return memberRepository.findBankbookNumByUsername(username);
    }
}
