import masters.QuarrelShips;

public class Main extends QuarrelShips {

    static QuarrelShips qs = new QuarrelShips();

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Main" };
        if (passedArgs != null) {
            qs.main(qs.concat(appletArgs, passedArgs));
        } else {
            qs.main(appletArgs);
        }
    }
}
