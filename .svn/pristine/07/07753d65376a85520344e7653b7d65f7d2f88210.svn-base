package com.thiscc.tools.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO extends HibernateDaoSupport {

	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory paramSessionFactory) {
		super.setSessionFactory(paramSessionFactory);
	}

	public Session getHibSession() {
		Session session = (Session) threadLocal.get();
		if ((session == null) || (!(session.isOpen()))) {
			session = getSession();
			threadLocal.set(session);
		}
		return session;
	}

	public void closeSession() {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if ((session == null) || (!(session.isOpen())))
			return;
		session.close();
	}

	public void saveObject(Object paramObject) {
		Session localSession = getHibSession();
		localSession.save(paramObject);
	}

	public void updateObject(Object paramObject) {
		Session localSession = getHibSession();
		localSession.update(paramObject);
	}

	public void delObject(Object paramObject) {
		Session localSession = getHibSession();
		localSession.delete(paramObject);
	}

	private boolean isCanDel(Object paramObject) {
		try {
			BeanInfo localBeanInfo = Introspector.getBeanInfo(paramObject
					.getClass());
			PropertyDescriptor[] arrayOfPropertyDescriptor = localBeanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < arrayOfPropertyDescriptor.length; ++i) {
				if (!(arrayOfPropertyDescriptor[i].getName().equals("isLocked")))
					continue;
				Object localObject = arrayOfPropertyDescriptor[i]
						.getReadMethod().invoke(paramObject, new Object[0]);
				if ((localObject != null)
						&& (localObject.equals(Integer.valueOf(1))))
					return false;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return true;
	}

	public List<? extends Object> findByHqlCondition(String paramString,
			String[] paramArrayOfString, Object[] paramArrayOfObject) {
		StringBuffer localStringBuffer = new StringBuffer("from " + paramString);
		for (int i = 0; i < paramArrayOfString.length; ++i) {
			if (i == 0)
				localStringBuffer.append(" where ");
			localStringBuffer.append(paramArrayOfString[i] + "=:"
					+ paramArrayOfString[i]);
			if (i == paramArrayOfString.length - 1)
				continue;
			localStringBuffer.append(" and ");
		}
		return findByHql(localStringBuffer.toString(), paramArrayOfString,
				paramArrayOfObject);
	}

	public List<? extends Object> findByHql(String paramString,
			Object[] paramArrayOfObject) {
		Session localSession = getHibSession();
		Query localQuery = localSession.createQuery(paramString);
		if (paramArrayOfObject != null)
			for (int i = 0; i < paramArrayOfObject.length; ++i)
				localQuery.setParameter(i, paramArrayOfObject[i]);
		List localList = localQuery.list();
		return localList;
	}

	public List<? extends Object> findByHql(String paramString,
			String[] paramArrayOfString, Object[] paramArrayOfObject) {
		Session localSession = getHibSession();
		Query localQuery = localSession.createQuery(paramString);
		if (paramArrayOfString != null)
			for (int i = 0; i < paramArrayOfString.length; ++i) {
				String str = paramArrayOfString[i];
				Object localObject = paramArrayOfObject[i];
				if (localObject instanceof List)
					localQuery.setParameterList(str, (List) localObject);
				else if (localObject instanceof Object[])
					localQuery.setParameterList(str,
							(Object[]) (Object[]) localObject);
				else
					localQuery.setParameter(str, paramArrayOfObject[i]);
			}
		List localList = localQuery.list();
		return localList;
	}

	/**
	 * 
	 * @param strHSql
	 * @param strFirstResult
	 * @param strMaxResult
	 * @param params
	 * @return
	 */
	public List<Object[]> findByhql(String strHSql, String strFirstResult,
			String strMaxResult, Map<String, Object> params) {
		if (StringUtils.isBlank(strHSql))
			return null;
		Session session = getHibSession();
		Query query = session.createQuery(strHSql);
		if (params != null) {
			query.setProperties(params);
		}
		if (StringUtils.isNotBlank(strFirstResult)) {
			query.setFirstResult(NumberUtils.toInt(strFirstResult));
		}
		if (StringUtils.isNotBlank(strMaxResult) && 
				!StringUtils.equalsIgnoreCase(strMaxResult, "0")) {
			query.setMaxResults(NumberUtils.toInt(strMaxResult));
		}
		List<Object[]> localList = query.list();
		/*if(strHSql.indexOf("UNION ALL") > 0){
			String param = strHSql.substring((strHSql.lastIndexOf("=")+2), (strHSql.length()-2));
			if(localList == null || localList.size() == 0){
				localList = (List<Object[]>) findByHql(
			            //"select b.employeeId as orgId,b.employeeName as orgName,0 as Amount from PtEmployee b where b.orgId=?", 
						"select b.employeeId as orgId,b.employeeName as orgName,0 as Amount from PtEmployee b,PtEmployeeOrg a where a.ptOrg.orgId=? and a.ptEmployee.employeeId = b.employeeId",
			            new String[] { param });
			}
		}*/
		return localList;
	}

	public Object findByHqlMapOnly(String paramString,
			HashMap<String, Object> paramHashMap) {
		if (StringUtils.isEmpty(paramString))
			return null;
		Session localSession = getHibSession();
		Query localQuery = localSession.createQuery(paramString);
		if (paramHashMap != null)
			localQuery.setProperties(paramHashMap);
		Object localObject = localQuery.uniqueResult();
		return localObject;
	}

	public Object findByHqlOnly(String paramString, Object[] paramArrayOfObject) {
		if (StringUtils.isEmpty(paramString))
			return null;
		Session localSession = getHibSession();
		Query localQuery = localSession.createQuery(paramString);
		if (paramArrayOfObject != null)
			for (int i = 0; i < paramArrayOfObject.length; ++i)
				localQuery.setParameter(i, paramArrayOfObject[i]);
		Object localObject = localQuery.uniqueResult();
		return localObject;
	}

	public Long getRcordCount(String paramString, Object[] paramArrayOfObject) {
		if (StringUtils.isEmpty(paramString))
			return null;
		List localList = findByHql(paramString, paramArrayOfObject);
		if (localList.size() == 1)
			return ((Long) localList.get(0));
		return new Long(localList.size());
	}
	/**
	 * 
	 * @param hsql
	 * @param paramNames
	 * @param paramValues
	 */
	public void executeHql(String hsql, String[] paramNames,
			Object[] paramValues) {
		Session session = getHibSession();
		Query query = session.createQuery(hsql);
		if (paramNames != null)
			for (int i = 0; i < paramNames.length; ++i) {
				String strName = paramNames[i];
				Object objValue = paramValues[i];
				if (objValue instanceof List)
					query.setParameterList(strName, (List) objValue);
				else if (objValue instanceof Object[])
					query.setParameterList(strName,
							(Object[]) (Object[]) objValue);
				else
					query.setParameter(strName, paramValues[i]);
			}
		query.executeUpdate();
	}
}