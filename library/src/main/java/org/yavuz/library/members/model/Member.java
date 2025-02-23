package org.yavuz.library.members.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "member_firstname")
    private String memberFirstName;
    @Column(name = "member_lastname")
    private String memberLastName;
    @Column(name = "member_phonenumber")
    private String memberPhoneNumber;
    @Column(name = "member_email")
    private String memberEmail;
    @Column(name = "member_address")
    private String memberAddress;
    @Column(name = "member_type")
    private String memberType;
}
