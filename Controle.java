public class Controle {
  private Banco banco;
  private Horta horta;
  private Visao visao;

  public Controle(Banco banco, Horta horta, Visao visao) {
      this.banco = banco;
      this.horta = horta;
      this.visao = visao;
      this.horta.inicializarTabelas();
  }

  public void executar() {
      int resposta = -1;
      while (resposta != 0) {
          resposta = visao.menu();
          switch (resposta) {
              case 1:
                  adicionarParcela();
                  break;
              case 2:
                  regarParcela();
                  break;
              case 3:
                  adubarParcela();
                  break;
              case 4:
                  marcarTratamentoEspecial();
                  break;
              case 5:
                  sugerirAcoes();
                  break;
              case 6:
                  gerarRelatorio();
                  break;
              case 0:
                  visao.exibirMensagem("Encerrando o programa. Até logo!");
                  break;
              default:
                  visao.exibirMensagem("Opção inválida. Tente novamente.");
          }
      }
  }

  private void adicionarParcela() {
      String nomeParcela = visao.solicitarString("Nome da parcela: ");
      int tempoCrescimento = visao.solicitarInt("Tempo de crescimento (em dias): ");
      int climaIdeal = visao.solicitarInt("Qual o clima ideal? (1-Sol, 2-Chuva, 3-Ambiente): ");
      int plantas = visao.solicitarInt("Quantidade de plantas: ");

      horta.adicionarParcela(new Parcela(nomeParcela, tempoCrescimento, climaIdeal == 1 ? "Sol" : climaIdeal == 2 ? "Chuva" : "Ambiente"));

      for (int i = 0; i < plantas; i++) {
          String planta = visao.solicitarString("Nome da planta " + (i + 1) + ": ");
          horta.registrarPlanta(planta, nomeParcela);
      }
  }

  private void regarParcela() {
      String nomeParcela = visao.solicitarString("Nome da parcela para regar: ");
      String dataRega = visao.solicitarString("Data da rega (dd/mm/yyyy): ");
      horta.regarParcela(nomeParcela, dataRega);
  }

  private void adubarParcela() {
      String nomeParcela = visao.solicitarString("Nome da parcela para adubar: ");
      String dataAdubo = visao.solicitarString("Data do adubo (dd/mm/yyyy): ");
      horta.adubarParcela(nomeParcela, dataAdubo);
  }

  private void marcarTratamentoEspecial() {
      String nomeParcela = visao.solicitarString("Nome da parcela para tratamento especial: ");
      horta.getParcelas().stream()
          .filter(p -> p.getNome().equals(nomeParcela))
          .findFirst()
          .ifPresentOrElse(
              parcela -> parcela.marcarTratamentoEspecial(banco),
              () -> visao.exibirMensagem("Parcela não encontrada.")
          );
  }

  private void sugerirAcoes() {
      horta.sugerirAcoes();
  }

  private void gerarRelatorio() {
      horta.gerarRelatorio();
  }
}