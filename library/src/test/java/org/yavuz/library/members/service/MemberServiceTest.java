package org.yavuz.library.members.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.members.repository.MemberRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        System.out.println("Metod test ediliyor");
        }

    @AfterEach
    void tearDown() {
        System.out.println("Test edildi, sıradaki metoda geçiliyor.");
    }

    private static Member getMember1() {
        return new Member(1L, "Hakan", "Haksız", "+905555555555", "hakanhaksiz@gmail.com", "Eskişehir", "Öğrenci");
    }

    private static Member getMember2() {
        return new Member(2L, "Haksız", "Hakan", "+905055055555", "haksizmihaksiz@gmail.com", "Ankara", "Öğretmen");
    }

    @Test
    void getAllMember() {
        Member member1 = getMember1();
        Member member2 = getMember2();
        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));
        List<Member> memberList = memberService.getAllMember();
        assertEquals(2, memberList.size());
        assertEquals("Hakan", memberList.get(0).getMemberFirstName());
        assertEquals("haksizmihaksiz@gmail.com", memberList.get(1).getMemberEmail());
    }

    @Test
    void getMember() {
        Member member1 = getMember1();
        Member member2 = getMember2();
        when(memberRepository.findById(2L)).thenReturn(Optional.of(member1));
        Member result = memberService.getMember(2L);
        assertEquals(member1, result);
    }

    @Test
    void addMember() {
        Member member1 = getMember1();
        Member member2 = getMember2();
        when(memberRepository.save(member1)).thenReturn(member1);
        Member result = memberService.addMember(member1);
        assertEquals(member1, result);
    }

    @Test
    void updateMember() {
        Member member1 = getMember1();
        Member memberDetails = new Member(1L, "Salim", "Sağsalim", "+905455445432", "sagsalim@gmail.com", "Kars", "Öğretmen");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member1));
        when(memberRepository.save(member1)).thenReturn(member1);
        Member newMember = memberService.updateMember(1L, memberDetails);
        assertEquals("Salim", newMember.getMemberFirstName());
        assertEquals("sagsalim@gmail.com", newMember.getMemberEmail());


    }

    @Test
    void deleteMember() {
        Member member1 = getMember1();
        Member member2 = getMember2();
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member1));
        memberService.deleteMember(1L);

    }
}