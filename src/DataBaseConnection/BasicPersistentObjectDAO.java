package DataBaseConnection;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BasicPersistentObjectDAO {
	private Session sesion;
	private Transaction tx;	

	protected abstract String getFromTable();
	
	protected abstract Object getDaoClass();
	
	public Serializable saveBasicPersistentObject(IBasicPersistentObject bpo) throws HibernateException {
		Serializable id = "";

		try {		
			if (getBasicPersistentObject(bpo.getId()) == null){
				beginOperation();
				id = sesion.save(bpo);
				tx.commit();
			}
		} catch (HibernateException he) {			
			handleException(he);
			throw he;
		} finally {
			sesion.close();			
		}

		return id;
	}

	public void updateBasicPersistentObject(IBasicPersistentObject bpo) throws HibernateException {
		try {
			beginOperation();
			sesion.update(bpo);
			tx.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public void deleteBasicPersistentObject(IBasicPersistentObject bpo) throws HibernateException {
		try {
			beginOperation();
			sesion.delete(bpo);
			tx.commit();
		} catch (HibernateException he) {
			handleException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public IBasicPersistentObject getBasicPersistentObject(Serializable idBpo) throws HibernateException {
		IBasicPersistentObject bpo = null;
		try {
			beginOperation();
			bpo = (IBasicPersistentObject) sesion.get((Class)getDaoClass(), idBpo);
		} finally {
			sesion.close();
		}

		return bpo;
	}

	public List<IBasicPersistentObject> getListBasicPersistentObject() throws HibernateException {
		List<IBasicPersistentObject> listaBpo = null;

		try {
			beginOperation();
			listaBpo = sesion.createQuery("from "+getFromTable()).list();
		} finally {
			sesion.close();
		}

		return listaBpo;
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
