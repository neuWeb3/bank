package com.neusoft.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.neusoft.dao.BillDetailDAO;
import com.neusoft.dao.CreditCardDAO;
import com.neusoft.dao.CreditcardApplyedDAO;
import com.neusoft.dao.UserDAO;
import com.neusoft.po.BillDetail;
import com.neusoft.po.CreditCard;
import com.neusoft.po.CreditcardApplyed;
import com.neusoft.po.User;

public class CreditCardManager {
	private UserDAO userDao;
	private CreditCardDAO creditCardDao;
	private CreditcardApplyedDAO applyDao;

	public boolean checkQueryPassword(Integer cardID, String password) {
		CreditCard card = creditCardDao.findById(cardID);
		if (card != null) {
			if (card.getQueryPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkTransactionPassword(Integer cardID, String password) {
		CreditCard card = creditCardDao.findById(cardID);
		if (card != null) {
			if (card.getTransactionPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public Set getCardInfo(String userName) {
		List users = userDao.findByProperty("userName", userName);
		if (users.isEmpty()) {
			return null;
		}
		return ((User) users.get(0)).getCreditCards();
	}

	public void applyCreditCard(String userName) {
		int min = 100000000;
		int max = 999999999;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		String cardNumber = "2913007" + s;
		List users = userDao.findByProperty("userName", userName);
		if (!users.isEmpty()) {
			User user = (User) users.get(0);
			CreditcardApplyed item = new CreditcardApplyed(user, cardNumber);
			// user.getApplycreditcards().add(item);
			applyDao.save(item);
		}
	}

	public Set getApplicationProgress(String userName) {
		List users = userDao.findByProperty("userName", userName);
		if (!users.isEmpty()) {
			User user = (User) users.get(0);
			return user.getCreditcardApplyeds();
		}
		return null;
	}

	public void reportLoss(Integer cardID) {
		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			creditCard.setStatus("lock");
			creditCardDao.attachDirty(creditCard);
		}
	}

	public void activeCard(Integer cardID) {
		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			creditCard.setStatus("normal");
			creditCardDao.attachDirty(creditCard);
		}
	}

	public void changeTPasssword(Integer cardID, String password) {
		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			creditCard.setTransactionPassword(password);
			creditCardDao.attachDirty(creditCard);
		}
	}

	public void changeSPassword(Integer cardID, String password) {
		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			creditCard.setQueryPassword(password);
			creditCardDao.attachDirty(creditCard);
		}
	}

	public void getCheckedBill(Integer cardID) {
		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			Set<BillDetail> billDetails = creditCard.getBillDetails();
		}
	}

	public Set<BillDetail> getUncheckedBill(Integer cardID) {

		CreditCard creditCard = creditCardDao.findById(cardID);
		if (creditCard != null) {
			Set<BillDetail> billDetails = creditCard.getBillDetails();
			Set<BillDetail> uncheckedBill = new HashSet<BillDetail>();
			Calendar a = Calendar.getInstance();
			
			Date date = new Date(a.get(Calendar.YEAR) - 1900,
					a.get(Calendar.MONTH), creditCard.getStatementDate());
			GregorianCalendar gc=new GregorianCalendar(); 
			gc.setTime(date);
			if(date.after(a.getTime())){//本月未到账单日，月份减一
				gc.add(2,-1);
				date = gc.getTime();
			}
			
			System.out.println(date);
			for (BillDetail detail : billDetails) {
				if (detail.getTransactionTime().after(date)) {
					uncheckedBill.add((BillDetail) detail);
				}
			}

			return uncheckedBill;
		}
		return null;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public CreditCardDAO getCreditCardDao() {
		return creditCardDao;
	}

	public void setCreditCardDao(CreditCardDAO creditCardDao) {
		this.creditCardDao = creditCardDao;
	}

	public CreditcardApplyedDAO getApplyDao() {
		return applyDao;
	}

	public void setApplyDao(CreditcardApplyedDAO applyDao) {
		this.applyDao = applyDao;
	}
}
