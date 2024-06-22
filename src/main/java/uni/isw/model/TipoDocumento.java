package uni.isw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name="tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_tipo_documento;
    private String descripcion;

    public TipoDocumento() {
    }

    
    public TipoDocumento(Integer id_tipo_documento, String descripcion) {
        this.id_tipo_documento = id_tipo_documento;
        this.descripcion = descripcion;
    }
    
    
}
