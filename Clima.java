import java.util.Random;

public class Clima {

    public String randomizarClima() {
        String[] climas = {"Sol", "Chuva", "Ambiente"};
        Random random = new Random();
        int index = random.nextInt(climas.length);
        return climas[index];
    }
}
