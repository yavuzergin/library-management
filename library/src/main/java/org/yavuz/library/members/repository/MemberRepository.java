package org.yavuz.library.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.library.members.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
}
