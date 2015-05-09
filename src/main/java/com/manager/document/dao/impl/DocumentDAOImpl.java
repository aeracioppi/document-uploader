package com.manager.document.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manager.document.dao.DocumentDAO;
import com.manager.document.model.Document;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Transactional
	public void save(Document doc) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(doc);
	}

	@Transactional
    public List<Document> list() {
        @SuppressWarnings("unchecked")
        List<Document> listDocs = (List<Document>) sessionFactory.getCurrentSession()
                .createCriteria(Document.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listDocs;
    }
	
	@Transactional
    public Document get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (Document)session.get(Document.class, id);
    }
 
    @Transactional
    public void remove(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Document document = (Document)session.get(Document.class, id);
        session.delete(document);
    }
    
    @Transactional
    public List<Document> selectCriteria(String name, String owner, Date sql) {
        @SuppressWarnings("unchecked")
        List<Document> listDocs = (List<Document>) sessionFactory.getCurrentSession()
                .createCriteria(Document.class)
                .add(	Restrictions.and(	Restrictions.like("document_name", "%"+name+"%"),
                							Restrictions.like("document_owner", owner),
                							Restrictions.like("document_upload", sql)
                						)
                	)
                .list();
        return listDocs;
    }

}
