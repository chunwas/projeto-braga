import java.util.HashMap;
import java.util.Map;

public class Parcela {
    private String nome;
    private String clima;
    private int tempoCrescimento;
    private boolean precisaTratamentoEspecial;

    // Construtor
    public Parcela(String nome, int tempoCrescimento, String clima) {
        this.nome = nome;
        this.clima = clima;
        this.tempoCrescimento = tempoCrescimento;
        this.precisaTratamentoEspecial = false;
    }

    public void adicionarAoBanco(Banco banco) {
        // Cria um mapa de valores para representar a parcela no banco
        Map<String, String> valores = new HashMap<>();
        valores.put("nome", nome);
        valores.put("tempoCrescimento", String.valueOf(tempoCrescimento));
        valores.put("clima", clima);
        valores.put("tratamentoEspecial", precisaTratamentoEspecial ? "true" : "false");
    
        // Insere os dados no banco
        banco.insert("parcelas", valores);
    
        System.out.println("Parcela '" + nome + "' adicionada ao banco.");
    }

    public void marcarTratamentoEspecial(Banco banco) {
        if (!precisaTratamentoEspecial) {
            this.precisaTratamentoEspecial = true;
    
            // Atualiza a tabela de parcelas no banco
            Map<String, String> valoresAtualizados = new HashMap<>();
            valoresAtualizados.put("tratamentoEspecial", "true");
    
            Map<String, String> condicoes = new HashMap<>();
            condicoes.put("nome", nome);
    
            banco.update("parcelas", valoresAtualizados, condicoes);
    
            System.out.println("Parcela '" + nome + "' marcada para tratamento especial.");
        } else {
            System.out.println("Parcela '" + nome + "' já está marcada para tratamento especial.");
        }
    }
    
    public void removerTratamentoEspecial(Banco banco) {
        if (precisaTratamentoEspecial) {
            this.precisaTratamentoEspecial = false;
    
            // Atualiza a tabela de parcelas no banco
            Map<String, String> valoresAtualizados = new HashMap<>();
            valoresAtualizados.put("tratamentoEspecial", "false");
    
            Map<String, String> condicoes = new HashMap<>();
            condicoes.put("nome", nome);
    
            banco.update("parcelas", valoresAtualizados, condicoes);
    
            System.out.println("Tratamento especial removido da parcela '" + nome + "'.");
        } else {
            System.out.println("Parcela '" + nome + "' não possui tratamento especial.");
        }
    }
    

    // Getters
    public String getNome() {
        return nome;
    }

    public String getClima() {
        return clima;
    }

    public int getTempoCrescimento() {
        return tempoCrescimento;
    }

    public boolean isPrecisaTratamentoEspecial() {
        return precisaTratamentoEspecial;
    }
}
