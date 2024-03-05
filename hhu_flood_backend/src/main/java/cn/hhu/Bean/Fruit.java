package cn.hhu.Bean;/*
author:tlj
*/

 public class Fruit {
    private int id;
    private String apple;
    private String orange;
    private String banana;
    private String watermelon;
    private String pineapple;

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", apple='" + apple + '\'' +
                ", orange='" + orange + '\'' +
                ", banana='" + banana + '\'' +
                ", watermelon='" + watermelon + '\'' +
                ", pineapple='" + pineapple + '\'' +
                '}';
    }

    public Fruit() {
    }

    public Fruit(int id, String apple, String orange, String banana, String watermelon, String pineapple) {
        this.id = id;
        this.apple = apple;
        this.orange = orange;
        this.banana = banana;
        this.watermelon = watermelon;
        this.pineapple = pineapple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getOrange() {
        return orange;
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }

    public String getBanana() {
        return banana;
    }

    public void setBanana(String banana) {
        this.banana = banana;
    }

    public String getWatermelon() {
        return watermelon;
    }

    public void setWatermelon(String watermelon) {
        this.watermelon = watermelon;
    }

    public String getPineapple() {
        return pineapple;
    }

    public void setPineapple(String pineapple) {
        this.pineapple = pineapple;
    }
}
