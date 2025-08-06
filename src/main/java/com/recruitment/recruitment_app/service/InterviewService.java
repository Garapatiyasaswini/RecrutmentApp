package com.recruitment.recruitment_app.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recruitment.recruitment_app.model.Interview;
import com.recruitment.recruitment_app.repository.InterviewRepository;
@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
private NotificationService notificationService;

public Interview scheduleInterview(Interview interview) {
    interview.setStatus("Scheduled");
    Interview saved = interviewRepository.save(interview);
    notificationService.sendEmail("candidate@email.com", "Interview Scheduled", "Your interview is on " + interview.getInterviewDateTime());
    notificationService.sendSMS("+919999999999", "Interview scheduled for job " + interview.getJobId());
    notificationService.sendWhatsApp("+919999999999", "WhatsApp: Your interview is scheduled.");

    return saved;
}

public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

public List<Interview> getByCandidateId(Long candidateId) {
        List<Interview> all = interviewRepository.findAll();
        List<Interview> result = new ArrayList<>();
        for (Interview i : all) {
           if (i.getCandidateId() == candidateId) {
                 result.add(i);
            }
        }
        return result;
    }

    public Interview updateStatus(Long id, String newStatus) {
        Interview interview = interviewRepository.findById(id).orElse(null);
        if (interview != null) {
            interview.setStatus(newStatus);
            return interviewRepository.save(interview);
        }
        return null;
    }
}