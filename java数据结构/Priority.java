
interface Priority {

    /**
     * 返回对象的优先级, 约定数字越大, 优先级越高
     * 
     * @return 优先级
     */
    int priority();
}

class MyPriority implements Priority {
    private int priority;
    private Object value;

    public MyPriority(int priority, Object value) {
        this.priority = priority;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int priority() {
        return priority;
    }
}