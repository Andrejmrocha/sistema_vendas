package OxePresentes.sistema.Controller;

import OxePresentes.sistema.DTO.IntervaloDatas;
import OxePresentes.sistema.Domain.compra.CompraRepository;
import OxePresentes.sistema.Domain.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("financeiro")
public class FinanceiroController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("/faturamento-ano/{ano}")
    public ResponseEntity faturamentoAno(@PathVariable int ano){
        String totalVendas = vendaRepository.faturamentoAno(ano);

        if (totalVendas == null){
            String mensagem = "Nenhum dado encontrado para o ano "+ ano;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(totalVendas);
    }

    @GetMapping("/faturamento-datas")
    public ResponseEntity faturamentoEntreDatas(@RequestBody IntervaloDatas datas){
        String dataI = datas.ano()+"-"+datas.mes()+"-"+datas.dia();
        String dataF = datas.anoFinal()+"-"+datas.mesFinal()+"-"+datas.diaFinal();

        Date dataInicial = converterData(dataI);
        Date dataFinal = converterData(dataF);

        String totalVendas = vendaRepository.faturamentoDatas(dataInicial, dataFinal);
        String totalCompras = compraRepository.faturamentoDatas(dataInicial, dataFinal);

        if(totalCompras == null || totalVendas == null){
            String mensagem = "Nenhum dado encontrado para o período: " + dataI + " - " + dataF;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

        double vendas = converterValor(totalVendas);
        double compras = converterValor(totalCompras);

        return ResponseEntity.ok(vendas - compras);
    }

    public Date converterData(String data){
        LocalDate localDate = LocalDate.parse(data);
        return Date.valueOf(localDate);
    }

    public double converterValor(String valor){
        return Double.parseDouble(valor);
    }


}
