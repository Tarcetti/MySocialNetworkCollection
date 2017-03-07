package model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserInfoDAO {
	private Session sesion;
	private Transaction tx;

	public long saveUserInfo(UserInfo interest) throws HibernateException {
		long id = 0;

//		try {
			beginOperation();
			id = (Long) sesion.save(interest);
			tx.commit();
//		} catch (HibernateException he) {			
//			handleException(he);
//			throw he;
//		} finally {
			sesion.close();			
//		}

		return id;
	}

	public void updateInterest(Interest interest) throws HibernateException {
		try {
			beginOperation();
			sesion.update(interest);
			tx.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public void deleteInterest(Interest interest) throws HibernateException {
		try {
			beginOperation();
			sesion.delete(interest);
			tx.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public Interest getInterest(long idInterest) throws HibernateException {
		Interest interest = null;
		try {
			beginOperation();
			interest = (Interest) sesion.get(Interest.class, idInterest);
		} finally {
			sesion.close();
		}

		return interest;
	}

	public List<Interest> getListInterest() throws HibernateException {
		List<Interest> listaInterests = null;

		try {
			beginOperation();
			listaInterests = sesion.createQuery("from Interest").list();
		} finally {
			sesion.close();
		}

		return listaInterests;
	}

	private void beginOperation() throws HibernateException {		
		sesion = HibernateUtil.getSessionFactory().openSession();
		tx = sesion.beginTransaction();
	}

	private void handleException(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}

	private void handleExceptionE(Exception he) throws Exception {
		tx.rollback();
		throw new Exception("Ocurrió un error en la capa de acceso a datos", he);
	}

}
