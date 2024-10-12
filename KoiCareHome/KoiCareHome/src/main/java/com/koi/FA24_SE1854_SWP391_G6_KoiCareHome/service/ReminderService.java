package com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.service;

import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.model.Reminder;
import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.model.Member;
import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.repository.ReminderRepository;
import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Tạo Reminder mới
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    // Lấy danh sách tất cả reminders
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    // Lấy Reminder theo ID
    public Reminder getReminderById(Integer id) {
        return reminderRepository.findById(id).orElse(null);
    }

    // Cập nhật Reminder
    public Reminder updateReminder(Integer id, Reminder reminderDetails) {
        Reminder reminder = reminderRepository.findById(id).orElse(null);
        if (reminder != null) {
            reminder.setTitle(reminderDetails.getTitle());
            reminder.setDateTime(reminderDetails.getDateTime());
            reminder.setFrequency(reminderDetails.getFrequency());
            reminder.setPondId(reminderDetails.getPondId());
            reminder.setFishId(reminderDetails.getFishId());
            reminder.setMemberID(reminderDetails.getMemberID()); // Cập nhật MemberID
            reminder.setContent(reminderDetails.getContent());
            return reminderRepository.save(reminder);
        }
        return null;
    }

    // Xóa Reminder
    public void deleteReminder(Integer id) {
        reminderRepository.deleteById(id);
    }

    // Scheduler: Kiểm tra nhắc nhở và gửi email theo tần suất đã đặt
    @Scheduled(fixedRate = 60000) // Kiểm tra mỗi phút
    public void sendReminderNotifications() {
        List<Reminder> reminders = reminderRepository.findByIsActiveTrue();
        for (Reminder reminder : reminders) {
            if (reminder.getDateTime().isBefore(LocalDateTime.now())) {
                sendEmailReminder(reminder);
                reminder.setActive(false); // Đánh dấu nhắc nhở đã được gửi
                reminderRepository.save(reminder);
            }
        }
    }

    // Gửi email nhắc nhở với email lấy từ Member
    public void sendEmailReminder(Reminder reminder) {
        Optional<Member> optionalMember = memberRepository.findById(reminder.getMemberID()); // Trả về Optional<Member>
        if (optionalMember.isPresent()) { // Kiểm tra sự tồn tại của Member
            Member member = optionalMember.get(); // Lấy đối tượng Member từ Optional
            if (member.getEmail() != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(member.getEmail()); // Lấy email từ người dùng
                message.setSubject("Reminder: " + reminder.getTitle());
                message.setText("Content: " + reminder.getContent());
                mailSender.send(message);
            }
        }
    }
}
