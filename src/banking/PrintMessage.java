package banking;

/**
 * @Author soldier
 * @Date 2020/3/23 18:16
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:该类用于封装凭条固定表单信息
 */
public class PrintMessage {

    private String title;
    private String printNum;
    private String ATMNum;
    private String operaMoney;
    private String operaTime;
    private String operaCard;
    private String operaNum;

    public PrintMessage(String title, String printNum, String ATMNum, String operaMoney, String operaTime,
                        String operaCard, String operaNum) {
        super();
        this.title = title;
        this.printNum = printNum;
        this.ATMNum = ATMNum;
        this.operaMoney = operaMoney;
        this.operaTime = operaTime;
        this.operaCard = operaCard;
        this.operaNum = operaNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getATMNum() {
        return ATMNum;
    }

    public void setATMNum(String aTMNum) {
        ATMNum = aTMNum;
    }

    public String getOperaMoney() {
        return operaMoney;
    }

    public void setOperaMoney(String operaMoney) {
        this.operaMoney = operaMoney;
    }

    public String getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(String operaTime) {
        this.operaTime = operaTime;
    }

    public String getOperaCard() {
        return operaCard;
    }

    public void setOperaCard(String operaCard) {
        this.operaCard = operaCard;
    }

    public String getOperaNum() {
        return operaNum;
    }

    public void setOperaNum(String operaNum) {
        this.operaNum = operaNum;
    }
}
