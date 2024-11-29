import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rega extends Parcela {
    private List<String> datas; 

    public Rega(Parcela parcela) {
        super(parcela.getNome(), parcela.getTempoCrescimento(), parcela.getClima());
        this.datas = new ArrayList<>(); 
    }

    public void registrarRega(Banco banco, String data) {
        datas.add(data);

        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", getNome());
        valores.put("data", data);
        banco.insert("regas", valores);

        System.out.println("Rega registrada para a parcela '" + getNome() + "' na data " + data);
    }
    
    public static void consultarRega(Banco banco, String nomeParcela) {
        List<Map<String, String>> registros = banco.select("regas");
        boolean encontrado = false;

        System.out.println("Regas registradas para a parcela '" + nomeParcela + "':");
        for (Map<String, String> registro : registros) {
            if (registro.get("parcela").equals(nomeParcela)) {
                System.out.println("- Data: " + registro.get("data"));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma rega registrada para a parcela '" + nomeParcela + "'.");
        }
    }

    public List<String> getDatas() {
        return datas;
    }
}
