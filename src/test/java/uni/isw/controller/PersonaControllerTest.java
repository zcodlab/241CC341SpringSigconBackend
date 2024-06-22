package uni.isw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uni.isw.model.Persona;
import uni.isw.service.PersonaService;

@WebMvcTest(controllers = PersonaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;

    @Autowired
    private ObjectMapper objectMapper;   

    private Persona persona1, persona2;    
    
    @BeforeEach
    public void init() {
        persona1 = Persona.builder()                
                .apellido_paterno("Chirinos")
                .apellido_materno("Soto")
                .nombres("Patricia")
                .fecha_nacimiento(new Date(1992-04-05))
                .id_tipo_documento(1)
                .ndocumento("998887766")
                .direccion("Av. Guardia Chalaca 526")
                .idubigeo("070104").build();          
        
        persona2 = Persona.builder()                
                .apellido_paterno("Chirinos")
                .apellido_materno("Soto")
                .nombres("Patricia")
                .fecha_nacimiento(new Date(1992-04-05))
                .id_tipo_documento(1)
                .ndocumento("998887766")
                .direccion("Av. Guardia Chalaca 526")
                .idubigeo("070104").build();          
    }
    @Test
    public void PersonaController_Insert() throws Exception {                
        given(personaService.saveOrUpdatePersona(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        
        ResultActions response = mockMvc.perform(post("/api/v1/persona/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(persona1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombres", CoreMatchers.is(persona2.getNombres())));
                
    }   
    
    @Test
    public void PersonaController_Listar() throws Exception {
        List<Persona> personaList=new ArrayList<>();
        personaList.add(persona1);
        personaList.add(persona2);
        
        when(personaService.getPersonas()).thenReturn(personaList);
        
        ResultActions response = mockMvc.perform(get("/api/v1/persona/listar")
                .contentType(MediaType.APPLICATION_JSON)
                );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(personaList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
        
    }
}