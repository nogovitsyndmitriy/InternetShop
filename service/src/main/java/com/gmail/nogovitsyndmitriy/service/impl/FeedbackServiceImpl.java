package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.FeedbackDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.service.FeedbackService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.FeedbackDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.FeedbackConverter;
import com.gmail.nogovitsyndmitriy.service.model.FeedbackDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final static Logger log = LogManager.getLogger(FeedbackServiceImpl.class);
    private FeedbackDao feedbackDao;
    private final FeedbackConverter feedbackConverter;
    private final FeedbackDtoConverter feedbackDtoConverter;

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao,
                               @Qualifier("feedbackConverter") FeedbackConverter feedbackConverter,
                               @Qualifier("feedbackDtoConverter") FeedbackDtoConverter feedbackDtoConverter) {
        this.feedbackDao = feedbackDao;
        this.feedbackConverter = feedbackConverter;
        this.feedbackDtoConverter = feedbackDtoConverter;
    }


    @Override
    @Transactional
    public FeedbackDto get(Long id) {
        FeedbackDto feedbackDto = new FeedbackDto();
        try {
            Feedback feedback = feedbackDao.get(id);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            log.info("Get feedback by Id successful!");
        } catch (Exception e) {
            log.error("Failed to get feedback by Id!");
        }
        return feedbackDto;
    }

    @Override
    @Transactional
    public FeedbackDto save(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.save(feedback);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            log.info("Feedback save successful!");
        } catch (Exception e) {
            log.error("Failed to save feedback!");
        }
        return feedbackDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public FeedbackDto update(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.update(feedback);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            log.info("Feedback update successful!");
        } catch (Exception e) {
            log.error("Failed to update feedback!");
        }
        return feedbackDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.delete(feedback);
            log.info("Delete feedback successful!");
        } catch (Exception e) {
            log.error("Failed to delete feedback!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            Feedback feedback = feedbackDao.get(id);
            feedbackDao.delete(feedback);
            log.info("Delete by Id successful!");
        } catch (Exception e) {
            log.error("Failed to delete feedback by Id!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<FeedbackDto> getAll() {
        return new ArrayList<>();
    }
}
