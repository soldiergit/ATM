package banking;

import java.sql.Timestamp;

/**
 * @Author soldier
 * @Date 2020/3/23 18:19
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:收据类
 */
public class Voucher {

    private int watercourse_num; //流水号
    private int atm_id;   //ATM机编号
    private int money;    //金额
    private Timestamp updateTime;    //操作时间
    private int cardNo;  //操作卡号
    private int optionNum;   //业务类型

    public Voucher(int watercourse_num, int atm_id, int money, Timestamp updateTime, int cardNo, int optionNum) {
        this.watercourse_num = watercourse_num;
        this.atm_id = atm_id;
        this.money = money;
        this.updateTime = updateTime;
        this.cardNo = cardNo;
        this.optionNum = optionNum;
    }

    //对应getter和setter方法
    public int getWatercourse_num() {
        return watercourse_num;
    }

    public void setWatercourse_num(int watercourse_num) {
        this.watercourse_num = watercourse_num;
    }

    public int getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(int atm_id) {
        this.atm_id = atm_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getcardNo() {
        return cardNo;
    }

    public void setcardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

}
