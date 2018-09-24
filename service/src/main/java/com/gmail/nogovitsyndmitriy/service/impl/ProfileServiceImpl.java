package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.impl.ProfileDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.ProfileService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.ProfileDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.ProfileConverter;
import com.gmail.nogovitsyndmitriy.service.model.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileServiceImpl implements ProfileService {
    private final static Logger log = LogManager.getLogger(ProfileServiceImpl.class);
    private final ProfileDtoConverter profileDtoConverter;
    private final ProfileConverter profileConverter;
    private ProfileDao profileDao = new ProfileDaoImpl();
    private ProfileDto profileDto = new ProfileDto();
    private Profile profile = new Profile();

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileDtoConverter") ProfileDtoConverter profileDtoConverter, @Qualifier("profileConverter") ProfileConverter profileConverter) {
        this.profileDtoConverter = profileDtoConverter;
        this.profileConverter = profileConverter;
    }

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
