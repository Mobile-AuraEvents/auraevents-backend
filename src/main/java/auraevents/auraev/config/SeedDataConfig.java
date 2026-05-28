package auraevents.auraev.config;

import auraevents.auraev.model.Artista;
import auraevents.auraev.model.CasaDeShow;
import auraevents.auraev.model.Convidado;
import auraevents.auraev.model.FrequenciaRadio;
import auraevents.auraev.model.Patrocinador;
import auraevents.auraev.model.PatrocinioShow;
import auraevents.auraev.model.Show;
import auraevents.auraev.model.TipoVeiculoImprensa;
import auraevents.auraev.model.VeiculoImprensa;
import auraevents.auraev.repository.ArtistaRepository;
import auraevents.auraev.repository.CasaDeShowRepository;
import auraevents.auraev.repository.ConvidadoRepository;
import auraevents.auraev.repository.PatrocinadorRepository;
import auraevents.auraev.repository.PatrocinioShowRepository;
import auraevents.auraev.repository.ShowRepository;
import auraevents.auraev.repository.VeiculoImprensaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDataConfig {

    @Bean
    CommandLineRunner seedData(
            ArtistaRepository artistaRepository,
            CasaDeShowRepository casaDeShowRepository,
            PatrocinadorRepository patrocinadorRepository,
            VeiculoImprensaRepository veiculoImprensaRepository,
            ShowRepository showRepository,
            PatrocinioShowRepository patrocinioShowRepository,
            ConvidadoRepository convidadoRepository
    ) {
        return args -> {
            if (artistaRepository.count() > 0
                    || casaDeShowRepository.count() > 0
                    || patrocinadorRepository.count() > 0
                    || veiculoImprensaRepository.count() > 0
                    || showRepository.count() > 0
                    || patrocinioShowRepository.count() > 0
                    || convidadoRepository.count() > 0) {
                return;
            }

            Artista artista = artistaRepository.save(
                    Artista.builder()
                            .nome("Banda Horizonte Sul")
                            .assessorResponsavel("Marina Alves")
                            .telefones(List.of("(11) 98888-1111", "(11) 97777-2222"))
                            .build()
            );

            CasaDeShow casa = casaDeShowRepository.save(
                    CasaDeShow.builder()
                            .nome("Arena Aurora")
                            .rua("Rua das Luzes")
                            .numero("145")
                            .bairro("Centro")
                            .cidade("Sao Paulo")
                            .uf("SP")
                            .capacidadeMaxima(5000)
                            .telefone("(11) 3333-4444")
                            .build()
            );

            Patrocinador patrocinador = patrocinadorRepository.save(
                    Patrocinador.builder()
                            .nome("Energia Max")
                            .cnpj("12.345.678/0001-90")
                            .telefone("(11) 4002-8922")
                            .build()
            );

            VeiculoImprensa radio = veiculoImprensaRepository.save(
                    VeiculoImprensa.builder()
                            .cnpj("23.456.789/0001-10")
                            .razaoSocial("Radio Cidade Viva")
                            .telefone("(11) 3555-7000")
                            .nomeResponsavel("Ricardo Lima")
                            .tipo(TipoVeiculoImprensa.RADIO)
                            .numero("101.3")
                            .frequencia(FrequenciaRadio.FM)
                            .build()
            );

            Show show = showRepository.save(
                    Show.builder()
                            .data(LocalDate.now().plusDays(20))
                            .artista(artista)
                            .casaDeShow(casa)
                            .build()
            );
            show.getVeiculosImprensa().add(radio);
            showRepository.save(show);

            patrocinioShowRepository.save(
                    PatrocinioShow.builder()
                            .show(show)
                            .patrocinador(patrocinador)
                            .valorPatrocinado(new BigDecimal("150000.00"))
                            .build()
            );

            convidadoRepository.save(
                    Convidado.builder()
                            .cpf("123.456.789-09")
                            .nome("Camila Rocha")
                            .telefone("(11) 96666-5555")
                            .patrocinador(patrocinador)
                            .build()
            );
        };
    }
}
