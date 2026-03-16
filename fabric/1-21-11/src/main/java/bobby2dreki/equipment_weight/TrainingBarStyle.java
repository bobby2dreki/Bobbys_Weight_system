package bobby2dreki.equipment_weight;

public enum TrainingBarStyle {
    DISABLED(0),
    DETAILED(1),
    CLASSIC(2),
    BAR(3),
    COMPACT(4),
    MINIMAL(5);

    public final int value;

    TrainingBarStyle(int value) {
        this.value = value;
    }

    public static TrainingBarStyle fromValue(int value) {
        for (TrainingBarStyle style : values()) {
            if (style.value == value) return style;
        }
        return DISABLED;
    }
}