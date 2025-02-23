package org.yavuz.library.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.members.repository.MemberRepository;
import org.yavuz.library.members.service.MemberService;
import java.util.List;
import java.util.Map;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/member-api/")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/list-members")
    public List<Member> getAllMembers() {
        return memberService.getAllMember();
    }
    @GetMapping("/get-member/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return ResponseEntity.ok(member);
    }
    @PostMapping("/add-member")
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }

    @PutMapping("/update-member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(id, memberDetails);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteMember (@PathVariable Long id) {
        Map<String,Boolean> response = memberService.deleteMember(id);
        return ResponseEntity.ok(response);
    }

}
