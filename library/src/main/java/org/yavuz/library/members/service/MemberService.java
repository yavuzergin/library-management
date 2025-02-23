package org.yavuz.library.members.service;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.library.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMember(){
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
    }
    public Member addMember(Member member){
        return memberRepository.save(member);
    }
    public Member updateMember(Long id, Member memberDetails){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
        member.setMemberFirstName(memberDetails.getMemberFirstName());
        member.setMemberLastName(memberDetails.getMemberLastName());
        member.setMemberPhoneNumber(memberDetails.getMemberPhoneNumber());
        member.setMemberEmail(memberDetails.getMemberEmail());
        member.setMemberAddress(memberDetails.getMemberAddress());
        member.setMemberType(memberDetails.getMemberType());
        return memberRepository.save(member);
    }

    public Map<String,Boolean> deleteMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
