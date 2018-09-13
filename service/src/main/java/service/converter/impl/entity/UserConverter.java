package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.*;
import service.converter.Converter;
import service.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter implements Converter<User, UserDto> {
    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        //  Add Profile
        ProfileConverter converter = new ProfileConverter();
        if (dto.getProfileDto() != null) {
            Profile profile = converter.toEntity(dto.getProfileDto());
            user.setProfile(profile);
            profile.setUser(user);
        }
        //  Add News
        NewsConverter newsConverter = new NewsConverter();
        Set<News> news = new HashSet<>();
        for (NewsDto newsDto : dto.getNewsDtoSet()) {
            news.add(newsConverter.toEntity(newsDto));
        }
        user.setNews(news);
        //  Add Orders
        OrderConverter orderConverter = new OrderConverter();
        Set<Order> orders = new HashSet<>();
        for (OrderDto orderDto : dto.getOrderDtoSet()) {
            orders.add(orderConverter.toEntity(orderDto));
        }
        user.setOrders(orders);
        //  Add Audits
        AuditConverter auditConverter = new AuditConverter();
        Set<Audit> audits = new HashSet<>();
        for (AuditDto auditDto : dto.getAuditDtoSet()) {
            audits.add(auditConverter.toEntity(auditDto));
        }
        user.setAudits(audits);
        //  Role
        RoleConverter roleConverter = new RoleConverter();
        if (dto.getRoleDto() != null) {
            Role role = roleConverter.toEntity(dto.getRoleDto());
            user.setRole(role);
        }

        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDto> list) {
        return null;
    }
}
