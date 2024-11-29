import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adubacao extends Parcela {
    private List<String> datas; // Lista para armazenar múltiplas datas

    public Adubacao(Parcela parcela) {
        super(parcela.getNome(), parcela.getTempoCrescimento(), parcela.getClima());
        this.datas = new ArrayList<>(); // Inicializa a lista de datas
    }

    public void registrarAdubo(Banco banco, String data) {
        datas.add(data);
        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", getNome());
        valores.put("data", data);
        banco.insert("adubos", valores);
        System.out.println("Adubo registrado para a parcela '" + getNome() + "' na data " + data);
    }

    public static void consultarAdubacao(Banco banco, String nomeParcela) {
        List<Map<String, String>> registros = banco.select("adubo");
        boolean encontrado = false;

        System.out.println("Adubações registradas para a parcela '" + nomeParcela + "':");
        for (Map<String, String> registro : registros) {
            if (registro.get("parcela").equals(nomeParcela)) {
                System.out.println("- Data: " + registro.get("data"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhuma adubação registrada para a parcela '" + nomeParcela + "'.");
        }
    }

    public List<String> getDatas() {
        return datas;
    }
}
