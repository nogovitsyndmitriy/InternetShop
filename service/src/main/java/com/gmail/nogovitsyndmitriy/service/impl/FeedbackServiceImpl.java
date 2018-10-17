package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.FeedbackDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.service.FeedbackService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.FeedbackDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.FeedbackConverter;
import com.gmail.nogovitsyndmitriy.service.exceptions.ServiceException;
import com.gmail.nogovitsyndmitriy.service.model.FeedbackDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    @Transactional(readOnly = true)
    public FeedbackDto get(Long id) {
        FeedbackDto feedbackDto;
        try {
            Feedback feedback = feedbackDao.get(id);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            log.info("Get feedback by Id successful!");
        } catch (Exception e) {
            log.error("Failed to get feedback by Id!");
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
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
            throw new ServiceException("Service Exception!");
        }
        return feedbackDto;
    }

    @Override
    @Transactional
    public FeedbackDto update(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.update(feedback);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            log.info("Feedback update successful!");
        } catch (Exception e) {
            log.error("Failed to update feedback!");
            throw new ServiceException("Service Exception!");
        }
        return feedbackDto;
    }

    @Override
    @Transactional
    public void delete(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.delete(feedback);
            log.info("Delete feedback successful!");
        } catch (Exception e) {
            log.error("Failed to delete feedback!");
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Feedback feedback = feedbackDao.get(id);
            feedbackDao.delete(feedback);
            log.info("Delete by Id successful!");
        } catch (Exception e) {
            log.error("Failed to delete feedback by Id!");
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
        }
    }

    @Override
    @Transactional
    public List<FeedbackDto> getAll() {
        throw new UnsupportedOperationException();
    }
}
