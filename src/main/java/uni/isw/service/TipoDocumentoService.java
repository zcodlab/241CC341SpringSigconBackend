package uni.isw.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.TipoDocumento;
import uni.isw.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
    public List<TipoDocumento> getTipoDocumentos(){
        return tipoDocumentoRepository.findAll();
    }
    public Optional<TipoDocumento> getTipoDocumento(Long id){
        return tipoDocumentoRepository.findById(id);
    }
    
    public void saveOrUpdateTipoDocumento(TipoDocumento tipoDocumento){
        tipoDocumentoRepository.save(tipoDocumento);
    }   
    
    public void deleteTipoDocumento(Long id){
        tipoDocumentoRepository.deleteById(id);
    }
}
