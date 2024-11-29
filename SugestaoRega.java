import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SugestaoRega {
    private String clima;

    public SugestaoRega(String clima) {
        this.clima = clima;
    }

    public String gerarSugestao(Banco banco, String parcelaNome) {

        List<String> datasRegas = consultarDatas(banco, "regas", parcelaNome);
        List<String> datasAdubos = consultarDatas(banco, "adubos", parcelaNome);

        if (clima.equals("Ambiente")) {
            if (datasRegas.size() == datasAdubos.size()) {
                return "Sem sugestão. A parcela está bem cuidada!";
            } else if (datasRegas.size() > datasAdubos.size()) {
                return precisaDeAdubo();
            } else {
                return precisaDeRegar();
            }
        } else if (clima.equals("Chuva")) {
            if (datasRegas.size() == datasAdubos.size()) {
                return precisaDeAdubo();
            } else if (datasRegas.size() > datasAdubos.size()) {
                return precisaDeAdubo();
            } else {
                return precisaDeRegar();
            }
        } else if (clima.equals("Sol")) {
            if (datasAdubos.size() == datasRegas.size()) {
                return precisaDeRegar();
            } else if (datasAdubos.size() > datasRegas.size()) {
                return precisaDeRegar();
            } else {
                return precisaDeAdubo();
            }
        }

        return "Anormalidade identificada. Sugestão: recolhimento imediato para amortização de possíveis prejuízos.";
    }

    private List<String> consultarDatas(Banco banco, String tabela, String parcelaNome) {
        List<Map<String, String>> registros = banco.select(tabela);
        List<String> datas = new ArrayList<>();
        for (Map<String, String> registro : registros) {
            if (registro.get("parcela").equals(parcelaNome)) {
                datas.add(registro.get("data"));
            }
        }
        return datas;
    }

    private String precisaDeRegar() {
        return "Precisa de rega!";
    }

    private String precisaDeAdubo() {
        return "Precisa de adubo!";
    }
}
