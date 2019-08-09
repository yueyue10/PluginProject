package com.zyj.plugin.common.data.bean;

/**
 * @作者 zhouchao
 * @日期 2019/7/11
 * @描述
 */
public class ParkPriiceBean {


    /**
     * commissionPrice : 0.0
     * parkPrice : 0.0
     * reservationPrice : 0.0
     * totalPrice : 0.0
     */

    private double commissionPrice;
    private double parkPrice;
    private double reservationPrice;
    private double totalPrice;

    public double getCommissionPrice() {
        return commissionPrice;
    }

    public void setCommissionPrice(double commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    public double getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(double parkPrice) {
        this.parkPrice = parkPrice;
    }

    public double getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(double reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
