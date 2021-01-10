package kg.nurzhamal.quizapp.data;

public class Item_for_type_questions {
    private int type;
    private Object object;

    public Item_for_type_questions(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
