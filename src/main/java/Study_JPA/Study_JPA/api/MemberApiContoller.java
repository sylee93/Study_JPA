package Study_JPA.Study_JPA.api;

import Study_JPA.Study_JPA.domain.Member;
import Study_JPA.Study_JPA.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiContoller {

    private final MemberService memberSersvice;

    /*  엔티티를 직접 노출하게됨
        jsonIgnore 등의 어노테이션 등을 사용할 수 있으나
        필요에 따라 엔티티의 스펙이 변경될 수 있음
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersv1(){
        return memberSersvice.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberSersvice.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());      /* stream 문법 : 리스트나 배열등의 원소를 가공할때 람다함수형식으로 간결하게 요소들을 처리
                                                        .map : 요소들을 특정조건에 해당하는 값으로 반환
                                                            .collect : 요소들의 가공이 끝난후 리턴해줄 결과를 collect를 통해 생성
                                                       해석 >> memberService객체의 findMembers를 통하여 가져온 member타입의 리스트를 
                                                              요소 순서대로 각각 m객체에 적재하고 m객체를 파라미터로써 MemberDto 클래스를 선언 */
        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMembeerV1(@RequestBody @Valid Member member){
        Long id = memberSersvice.join(member);
        return new CreateMemberResponse(id);
    }

    // api는 절대 Entity를 사용하지 않아야 하며, 항상 DTO를 사용하여 값을 받자
    // Entity를 노출하지말자
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberSersvice.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberSersvice.update(id, request.getName());
        Member findMember = memberSersvice.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
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
