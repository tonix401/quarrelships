package masters;

/**
 * A simple functional interface for lambda expressions and callback functions.
 * This interface provides a generic way to define functions that take no parameters
 * and return void, useful for event handling and callback operations in the game.
 * 
 * @param <T> The type parameter (currently not used in the function signature)
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public interface ILambdaFunction<T> {
    
    /**
     * Executes the lambda function.
     * This method should be implemented to define the specific action to be performed.
     */
    void function();
}
