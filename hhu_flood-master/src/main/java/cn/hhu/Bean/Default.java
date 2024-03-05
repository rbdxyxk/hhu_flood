package cn.hhu.Bean;


public class Default<TextBox> {
    private ModCalClass modSour = new ModCalClass();
    private slhPublicClass datSour = new slhPublicClass();
    private float loginTimer;
    private int loginCouter;   //登陆次数计数器

    @Override
    public String toString() {
        return "Default{" +
                "modSour=" + modSour +
                ", datSour=" + datSour +
                ", loginTimer=" + loginTimer +
                ", loginCouter=" + loginCouter +
                ", textBox2=" + textBox2 +
                '}';
    }

    public ModCalClass getModSour() {
        return modSour;
    }

    public void setModSour(ModCalClass modSour) {
        this.modSour = modSour;
    }

    public slhPublicClass getDatSour() {
        return datSour;
    }

    public void setDatSour(slhPublicClass datSour) {
        this.datSour = datSour;
    }

    public float getLoginTimer() {
        return loginTimer;
    }

    public void setLoginTimer(float loginTimer) {
        this.loginTimer = loginTimer;
    }

    public int getLoginCouter() {
        return loginCouter;
    }

    public void setLoginCouter(int loginCouter) {
        this.loginCouter = loginCouter;
    }

    public TextBox getTextBox2() {
        return textBox2;
    }

    public void setTextBox2(TextBox textBox2) {
        this.textBox2 = textBox2;
    }

    private TextBox textBox2;
//    HttpCookie cookieCalNumTest = new HttpCookie("cookieCalNumTest");

    private class ModCalClass {
    }

    private class slhPublicClass {
    }
}
