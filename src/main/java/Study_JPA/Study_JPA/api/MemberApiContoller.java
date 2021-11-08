package Study_JPA.Study_JPA.api;

import Study_JPA.Study_JPA.domain.Member;
import Study_JPA.Study_JPA.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiContoller {

    private final MemberService memberSersvice;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMembeerV1(@RequestBody @Valid Member member){
        Long id = memberSersvice.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberSersvice.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * DTO
     * 엔티티에서 값을 받아오면 유지보수하기 힘들기 때문에
     * DTO 클래스를 생성하여 DTO 객체를 수정함으로써 api스팩을 변경 하지 않도록 함
     */
    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
