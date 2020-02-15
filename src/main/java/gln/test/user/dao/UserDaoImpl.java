package gln.test.user.dao;

import gln.test.config.AppConfig;
import gln.test.config.WebConfig;
import gln.test.user.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import gln.test.config.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends ApplicationContext implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertUser(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void deleteUser(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        UserModel mod = session.get(UserModel.class, user.getId());
        if(null != mod){
            try{
                session.delete(mod);
            } finally {

            }
        }
    }

    @Override
    public void updateUser(UserModel user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public UserModel selectUser(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserModel.class, id);
    }

    @Override
    public Map<String,Object> selectUserList(Integer page) {
        Map<String,Object> res = new LinkedHashMap<>();
        int pageSize = RESULT_PER_PAGE;
        Session session = sessionFactory.getCurrentSession();
        String countQ = "Select count(u.id) from UserModel u";
        Query countQuery = session.createQuery(countQ);
        Long countResults = (Long) countQuery.getSingleResult();
        int lastPageNumber = (int) (Math.ceil(countResults / (double) pageSize));

        Query query = session.createQuery("from UserModel order by id");
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<UserModel> list = query.getResultList();
        res.put("page", page);
        res.put("per_page", pageSize);
        res.put("total", countResults);
        res.put("total_pages", lastPageNumber);
        res.put("data", list);
        return res;
    }
}
