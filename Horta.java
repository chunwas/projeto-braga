import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Horta extends Banco {
    private List<Parcela> parcelas;
    private Clima clima;
    private String climaAtual;
    private SugestaoRega sugestaoRega;

    // Construtor
    public Horta() {
        this.parcelas = new ArrayList<>();
        this.clima = new Clima();
    }

    // Inicializar tabelas no banco
    @Override
    protected void inicializarTabelas() {
        super.inicializarTabelas();
        createTable("parcelas");
        createTable("plantas");
        createTable("regas");
        createTable("adubos");
    }

    public void adicionarParcela(Parcela parcela) {
        if (parcelas.stream().anyMatch(p -> p.getNome().equals(parcela.getNome()))) {
            System.out.println("Parcela com o nome '" + parcela.getNome() + "' já existe na horta.");
            return;
        }
        parcelas.add(parcela);
        System.out.println("Parcela '" + parcela.getNome() + "' adicionada à horta.");
        parcela.adicionarAoBanco(this);
    }

    public void registrarPlanta(String planta, String nomeParcela) {
        Parcela parcela = buscarParcela(nomeParcela);
        if (parcela != null) {
            Map<String, String> valores = new HashMap<>();
            valores.put("parcela", nomeParcela);
            valores.put("planta", planta);
    
            insert("plantas", valores);
    
            System.out.println("Planta '" + planta + "' registrada na parcela '" + nomeParcela + "'.");
        } else {
            System.out.println("Parcela '" + nomeParcela + "' não encontrada. Planta '" + planta + "' não registrada.");
        }
    }

    public void regarParcela(String nomeParcela, String data) {
        Parcela parcela = buscarParcela(nomeParcela);
        if (parcela != null) {
            Rega rega = new Rega(parcela);
            rega.registrarRega(this, data);
        } else {
            System.out.println("Parcela '" + nomeParcela + "' não encontrada.");
        }
    }

    public void adubarParcela(String nomeParcela, String data) {
        Parcela parcela = buscarParcela(nomeParcela);
        if (parcela != null) {
            Adubacao adubacao = new Adubacao(parcela);
            adubacao.registrarAdubo(this, data);
        } else {
            System.out.println("Parcela '" + nomeParcela + "' não encontrada.");
        }
    }
    
    public void sugerirAcoes() {
        climaAtual = clima.randomizarClima();
        sugestaoRega = new SugestaoRega(climaAtual);
        System.out.println("Clima atual: " + climaAtual);

        for (Parcela parcela : parcelas) {
            String sugestao = sugestaoRega.gerarSugestao(this, parcela.getNome());
            System.out.println("Sugestão para a parcela '" + parcela.getNome() + "': " + sugestao);
        }
    }

    public void gerarRelatorio() {
        System.out.println("Relatório da Horta:");
        List<Map<String, String>> registrosParcelas = select("parcelas");

        for (Map<String, String> registro : registrosParcelas) {
            System.out.println("\n--------------------------\n");
            System.out.println("Parcela: " + registro.get("nome"));
            System.out.println("Tempo de crescimento esperado: " + registro.get("tempoCrescimento") + " dias");
            System.out.println("Precisa de tratamento especial: " + registro.get("tratamentoEspecial"));
            System.out.printf("Clima ideal: %s\n", registro.get("clima"));
            System.out.println("\n--------------------------\n");
        }
    }

    private Parcela buscarParcela(String nomeParcela) {
        return parcelas.stream()
                .filter(parcela -> parcela.getNome().equals(nomeParcela))
                .findFirst()
                .orElse(null);
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
