package poc.inetum.flowable.services;

import poc.inetum.flowable.domain.Message;
import poc.inetum.flowable.domain.Rdv;
import poc.inetum.flowable.exception.FunctionalException;
import poc.inetum.flowable.repository.RdvRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class RdvService {

    private RdvRepository rdvRepository;

    public RdvService(RdvRepository rdvRepository) {
        this.rdvRepository = rdvRepository;
    }

    public void getLocalDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
    public Rdv getRDV(long id) throws FunctionalException {
        return rdvRepository.findOne(id);
    }

    public Message Refuse(){
        return new Message("RDV Refused !");
    }

    public Message Accepted(Rdv rdv){
        return new Message("Hello, your RDV on " + rdv.getDateRDV()+" will be treated soon :) ");
    }
}
