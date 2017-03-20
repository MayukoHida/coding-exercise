package com.jpmorgan.trade.data;

import java.util.Date;

import com.jpmorgan.trade.data.enums.Currency;
import com.jpmorgan.trade.data.enums.Direction;
import com.jpmorgan.trade.util.Utils;

/**
 * Represents an entity.
 * 
 * @author Mayuko O'Hare
 *
 */
public class Entity implements Comparable<Entity> {

	private String name;
	private Direction direction;
	private double agreedFx;
	private Currency currency;
	private Date instructionDate;
	private Date settlementDate;
	private Date resetSettlementDate;
	private int units;
	private double pricePerUnit;
	private double amount;

	public Entity() {
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Direction getDirection() {
		return direction;
	}

	public final void setDirection(Direction direction) {
		this.direction = direction;
	}

	public final double getAgreedFx() {
		return agreedFx;
	}

	public final void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public final Currency getCurrency() {
		return currency;
	}

	public final void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public final Date getInstructionDate() {
		return instructionDate;
	}

	public final void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public final Date getSettlementDate() {
		return settlementDate;
	}

	public final void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public final int getUnits() {
		return units;
	}

	public final void setUnits(int units) {
		this.units = units;
	}

	public final double getPricePerUnit() {
		return pricePerUnit;
	}

	public final void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public final double getAmount() {
		return amount;
	}

	public final void setAmount(double amount) {
		this.amount = amount;
	}
	
	public final Date getResetSettlementDate() {
		return resetSettlementDate;
	}

	public final void setResetSettlementDate(Date resetSettlementDate) {
		this.resetSettlementDate = resetSettlementDate;
	}

	@Override
	public String toString() {
		return name + "\t| " + direction
				+ "\t\t| " + agreedFx 
				+ "\t\t| " + currency
				+ "\t\t| " + Utils.dateAsString(instructionDate)
				+ "\t\t| " + Utils.dateAsString(settlementDate)
				+ "\t\t| " + units 
				+ "\t\t| " + pricePerUnit
				+ "\t\t\t| " + Utils.dateAsString(resetSettlementDate)				
				+ "\t\t| " + amount;
	}
	
	@Override
	public int compareTo(Entity o) {
		return (int) (o.getAmount() - this.amount);
	}
	
}
