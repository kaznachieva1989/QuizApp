package kg.nurzhamal.quizapp.model;

public class ThemeQuiz {
    private int icon;
    private boolean isChange;

    public ThemeQuiz(int icon, boolean isChange) {
        this.icon = icon;
        this.isChange = isChange;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
