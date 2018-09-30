package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import com.gmail.nogovitsyndmitriy.dao.impl.ProfileDaoImpl;
import com.gmail.nogovitsyndmitriy.service.ProfileService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.ProfileDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.ProfileConverter;
import com.gmail.nogovitsyndmitriy.service.model.ProfileDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto get(long id) {
        try {
            profile = profileDao.get(id);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Get profile successful!");
        } catch (Exception e) {
            log.error("Get profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto save(ProfileDto dto) {
        try {
            profile = profileConverter.toEntity(dto);
            profileDao.save(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Saving profile successful!");
        } catch (Exception e) {
            log.error("Saving profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto update(ProfileDto dto) {
        try {
            profile = profileConverter.toEntity(dto);
            profileDao.update(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Update profile successful!");
        } catch (Exception e) {
            log.error("Update profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(ProfileDto dto) {
        try {
            profile = profileConverter.toEntity(dto);
            profileDao.delete(profile);
            log.info("Delete profile successful!");
        } catch (Exception e) {
            log.error("Delete profile failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            profile = profileDao.get(id);
            profileDao.delete(profile);
            log.info("Delete profile by Id successful!");
        } catch (Exception e) {
            log.error("SDelete profile by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ProfileDto> getAll() {
        return null;
    }
}
