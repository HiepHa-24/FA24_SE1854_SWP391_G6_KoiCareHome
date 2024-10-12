package com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.controller;

import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.model.Reminder;
import com.koi.FA24_SE1854_SWP391_G6_KoiCareHome.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        return ResponseEntity.ok(reminderService.createReminder(reminder));
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Integer id) {
        Reminder reminder = reminderService.getReminderById(id);
        if (reminder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reminder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Integer id, @RequestBody Reminder reminderDetails) {
        Reminder updatedReminder = reminderService.updateReminder(id, reminderDetails);
        if (updatedReminder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Integer id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
