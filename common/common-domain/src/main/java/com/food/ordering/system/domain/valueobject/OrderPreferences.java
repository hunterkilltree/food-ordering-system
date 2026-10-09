
public record OrderPreferences(
    List<String> removeIngredients,
    List<String> addIngredients,
    SpiceLevel spiceLevel,
    String specialInstructions,
    String deliveryInstructions,
) {}