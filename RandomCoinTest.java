/**
 * @author Martin Guo
 */
public class RandomCoinTest {

    public static void main(String[] args) {
        RandomCoin server = new RandomCoin();
        RandomCoin server3 = new RandomCoin();
        RandomCoin server4 = new RandomCoin();
        RandomCoin server5 = new RandomCoin();
        System.out.println(server.HandleGuess("Fun", 5));
        server3.StartGuessing("Joe");
        server4.StartGuessing("Martin");
        server5.StartGuessing("Berkeley");
        System.out.println("Fun: "  + server.GetCoins("Fun"));
        System.out.println("Joe: " + server3.GetCoins("Joe"));
        System.out.println("Martin: " + server4.GetCoins("Martin"));
        System.out.println("Berkeley: " + server5.GetCoins("Berkeley"));
    }

}
