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
            if(!transaction.isActive()){
                transaction.begin();
            }


            transaction.commit();
            log.info("Get feedback by Id successful!");
        }catch (Exception e){
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
                log.error("Failed to get feedback by Id!");
            }
        }
        return null;
    }

    @Override
    public FeedbackDto save(FeedbackDto dto) {
        return null;
    }

    @Override
    public FeedbackDto update(FeedbackDto dto) {
        return null;
    }

    @Override
    public void delete(FeedbackDto dto) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<FeedbackDto> getAll() {
        return null;
    }
}
