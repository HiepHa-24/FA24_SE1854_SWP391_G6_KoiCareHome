package com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reminderID;

    private String title;

    private LocalDateTime dateTime;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    private String frequency; // daily, weekly, monthly

    @Column(name = "PondID")
    private Integer pondId;

    @Column(name = "FishID")
    private Integer fishId;

    @Column(name = "MemberID") // Thêm cột MemberID để liên kết với Member
    private Integer memberID; // Kiểu Integer cho MemberID

    private String content; // Nội dung nhắc nhở được chọn (có thể là nhiều mục)


}
