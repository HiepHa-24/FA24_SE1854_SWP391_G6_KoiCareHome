package com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberID;

    @Column(name = "MemberName", nullable = false)
    private String memberName;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "RoleID", nullable = false)
    private Integer roleID;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "CreateDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "CreateBy", nullable = false)
    private String createBy;

    @Column(name = "UpdateDate")
    private LocalDateTime updateDate;

    @Column(name = "UpdateBy")
    private String updateBy;

    @Column(name = "FishID")
    private Integer fishID;

    @Column(name = "PondID")
    private Integer pondID;


}
