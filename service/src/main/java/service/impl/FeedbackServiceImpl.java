package service.impl;

import com.gmail.nogovitsyndmitriy.dao.FeedbackDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.dao.impl.FeedbackDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.FeedbackService;
import service.converter.impl.dto.FeedbackDtoConverter;
import service.converter.impl.entity.FeedbackConverter;
import service.model.FeedbackDto;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private final static Logger log = LogManager.getLogger(FeedbackServiceImpl.class);
    private FeedbackDao feedbackDao = new FeedbackDaoImpl(Feedback.class);
    private FeedbackDtoConverter feedbackDtoConverter = new FeedbackDtoConverter();
    private FeedbackConverter feedbackConverter = new FeedbackConverter();
    private FeedbackDto feedbackDto = new FeedbackDto();
    private Feedback feedback = new Feedback();


    @Override
    public FeedbackDto get(long id) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            feedback = feedbackDao.get(id);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            transaction.commit();
            log.info("Get feedback by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                log.error("Failed to get feedback by Id!");
            }
        }
        return feedbackDto;
    }

    @Override
    public FeedbackDto save(FeedbackDto feedbackDto) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            feedback = feedbackConverter.toEntity(this.feedbackDto);
            feedbackDao.save(feedback);
            this.feedbackDto = feedbackDtoConverter.toDTO(feedback);
            transaction.commit();
            log.info("Feedback save successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                log.error("Failed to save feedback!");
            }
        }
        return this.feedbackDto;
    }

    @Override
    public FeedbackDto update(FeedbackDto feedbackDto) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.update(feedback);
            feedbackDto = feedbackDtoConverter.toDTO(feedback);
            transaction.commit();
            log.info("Feedback update successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                log.error("Failed to update feedback!");
            }
        }
        return feedbackDto;
    }

    @Override
    public void delete(FeedbackDto feedbackDto) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            feedback = feedbackConverter.toEntity(feedbackDto);
            feedbackDao.delete(feedback);
            transaction.commit();
            log.info("Delete feedback successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                log.error("Failed to delete feedback!");
            }
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            feedback = feedbackDao.get(id);
            feedbackDao.delete(feedback);
            transaction.commit();
            log.info("Delete by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                log.error("Failed to delete feedback by Id!");
            }
        }

    }

    @Override
    public List<FeedbackDto> getAll() {
        return null;
    }
}
