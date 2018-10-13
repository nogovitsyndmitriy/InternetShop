package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import com.gmail.nogovitsyndmitriy.service.ProfileService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.ProfileDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.ProfileConverter;
import com.gmail.nogovitsyndmitriy.service.model.ProfileDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final static Logger log = LogManager.getLogger(ProfileServiceImpl.class);
    private final ProfileDtoConverter profileDtoConverter;
    private final ProfileConverter profileConverter;
    private final ProfileDao profileDao;

    @Autowired
    public ProfileServiceImpl(@Qualifier("profileDtoConverter") ProfileDtoConverter profileDtoConverter,
                              @Qualifier("profileConverter") ProfileConverter profileConverter,
                              ProfileDao profileDao) {
        this.profileDtoConverter = profileDtoConverter;
        this.profileConverter = profileConverter;
        this.profileDao = profileDao;
    }

    @Override
    @Transactional
    public ProfileDto get(Long id) {
        ProfileDto profileDto = new ProfileDto();
        try {
            Profile profile = profileDao.get(id);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Get profile successful!");
        } catch (Exception e) {
            log.error("Get profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional
    public ProfileDto save(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.save(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Saving profile successful!");
        } catch (Exception e) {
            log.error("Saving profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional
    public ProfileDto update(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.update(profile);
            profileDto = profileDtoConverter.toDTO(profile);
            log.info("Update profile successful!");
        } catch (Exception e) {
            log.error("Update profile failed!", e);
        }
        return profileDto;
    }

    @Override
    @Transactional
    public void delete(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.delete(profile);
            log.info("Delete profile successful!");
        } catch (Exception e) {
            log.error("Delete profile failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Profile profile = profileDao.get(id);
            profileDao.delete(profile);
            log.info("Delete profile by Id successful!");
        } catch (Exception e) {
            log.error("Delete profile by Id failed!", e);
        }
    }

    @Override
    @Transactional
    public List<ProfileDto> getAll() {
        return new ArrayList<>();
    }
}
