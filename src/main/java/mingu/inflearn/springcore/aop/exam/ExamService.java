package mingu.inflearn.springcore.aop.exam;

import lombok.RequiredArgsConstructor;
import mingu.inflearn.springcore.aop.exam.annotation.Trace;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
