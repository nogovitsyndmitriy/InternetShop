package service.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.impl.ProfileDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.ProfileService;
import service.converter.impl.dto.ProfileDtoConverter;
import service.converter.impl.entity.ProfileConverter;
import service.model.ProfileDto;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {
    private final static Logger log = LogManager.getLogger(ProfileServiceImpl.class);
    private ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
    private ProfileConverter profileConverter = new ProfileConverter();
    private ProfileDao profileDao = new ProfileDaoImpl(Profile.class);
    private ProfileDto profileDto = new ProfileDto();
    private Profile profile = new Profile();

    @Override
    public ProfileDto get(long id) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            profile = profileDao.get(id);
            profileDto = profileDtoConverter.toDTO(profile);
            transaction.commit();
            log.info("Get profile successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get profile failed!", e);
        }
        return profileDto;
    }

    @Override
    public ProfileDto save(ProfileDto dto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            profile = profileConverter.toEntity(dto);
            profileDao.save(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            transaction.commit();
            log.info("Saving profile successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving profile failed!", e);
        }
        return profileDto;
    }

    @Override
    public ProfileDto update(ProfileDto dto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            profile = profileConverter.toEntity(dto);
            profileDao.update(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            transaction.commit();
            log.info("Update profile successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update profile failed!", e);
        }
        return profileDto;
    }

    @Override
    public void delete(ProfileDto dto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            profile = profileConverter.toEntity(dto);
            profileDao.delete(profile);
            transaction.commit();
            log.info("Delete profile successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete profile failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            profile = profileDao.get(id);
            profileDao.delete(profile);
            transaction.commit();
            log.info("Delete profile by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("SDelete profile by Id failed!", e);
        }
    }

    @Override
    public List<ProfileDto> getAll() {
        return null;
    }
}
