
package uni.isw.repository;

import java.sql.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uni.isw.model.Persona;
import uni.isw.model.TipoDocumento;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class PersonaRepositoryTest {
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    private TipoDocumento tipoDocumento;
    
    @BeforeEach
    public void setUp() {        
        tipoDocumento=TipoDocumento.builder()
                .descripcion("DNI").build();
        
        tipoDocumentoRepository.save(tipoDocumento);
        
    }
    
    @Test
    public void PersonaRepository_Listar(){
        Integer id_tipo_documento=tipoDocumento.getId_tipo_documento();
        
        Persona persona1=Persona.builder()
                .apellido_paterno("Chirinos")
                .apellido_materno("Vargas")
                .nombres("Patricia")
                .fecha_nacimiento(new Date(1992-04-05))
                .id_tipo_documento(id_tipo_documento)
                .ndocumento("55556667")
                .direccion("Av. Guardia Chalaca 565")
                .idubigeo("070104").build();
        
        Persona persona2=Persona.builder()
                .apellido_paterno("Cavero")
                .apellido_materno("Alva")
                .nombres("Alejandro")
                .fecha_nacimiento(new Date(2000-04-05))
                .id_tipo_documento(id_tipo_documento)
                .ndocumento("33356667")
                .direccion("Av. Los Fresnos 865")
                .idubigeo("150114").build();
        
        personaRepository.save(persona1);
        personaRepository.save(persona2);
        
        //se esta poniendo a prueba el metodo obtener todas las personas
        List<Persona> personaList = (List<Persona>)personaRepository.findAll();
        
        Assertions.assertThat(personaList).isNotNull();
        Assertions.assertThat(personaList.size()).isEqualTo(2);        
                
    }
    @Test
    public void PersonaRepository_Insert(){
        Integer id_tipo_documento=tipoDocumento.getId_tipo_documento();
        Persona persona1=Persona.builder()
                .apellido_paterno("Chirinos")
                .apellido_materno("Vargas")
                .nombres("Patricia")
                .fecha_nacimiento(new Date(1992-04-05))
                .id_tipo_documento(id_tipo_documento)
                .ndocumento("55556667")
                .direccion("Av. Guardia Chalaca 565")
                .idubigeo("070104").build();                
        
        Persona newPersona=personaRepository.save(persona1);  
        
        Assertions.assertThat(newPersona).isNotNull();
        Assertions.assertThat(newPersona.getId_persona()).isGreaterThan(0);
                
    }
    
    
}
