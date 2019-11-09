package com.techelevator;

import java.math.BigDecimal;

public interface Auditable {
	
	public abstract void auditFeed(String insertMoney, BigDecimal balance);
	public abstract void auditTransaction(Product product, BigDecimal balance);
	public abstract void auditChange(BigDecimal balance);
	public abstract void auditClose();

}
