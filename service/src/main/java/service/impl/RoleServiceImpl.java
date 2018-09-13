package service.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.impl.RoleDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.RoleService;
import service.converter.impl.dto.RoleDtoConverter;
import service.converter.impl.entity.RoleConverter;
import service.model.RoleDto;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final static Logger log = LogManager.getLogger(RoleServiceImpl.class);
    private RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
    private RoleConverter roleConverter = new RoleConverter();
    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private Role role = new Role();

    @Override
    public RoleDto get(long id) {
        Session session = roleDao.getCurrentSession();
        RoleDto roleDto = new RoleDto();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            role = roleDao.get(id);
            roleDto = roleDtoConverter.toDTO(role);
            transaction.commit();
            log.info("Get role successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get role failed!", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            role = roleConverter.toEntity(roleDto);
            roleDao.save(role);
            roleDto = roleDtoConverter.toDTO(role);
            transaction.commit();
            log.info("Saving role successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving role failed!", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            role = roleConverter.toEntity(roleDto);
            roleDao.update(role);
            roleDto = roleDtoConverter.toDTO(role);
            transaction.commit();
            log.info("Update role successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update role failed!", e);
        }
        return roleDto;
    }

    @Override
    public void delete(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            role = roleConverter.toEntity(roleDto);
            roleDao.delete(role);
            transaction.commit();
            log.info("Delete role successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete role failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            role = roleDao.get(id);
            roleDao.delete(role);
            transaction.commit();
            log.info("Delete role by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete role by Id failed!", e);
        }
    }

    @Override
    public List<RoleDto> getAll() {
        return null;
    }
}
